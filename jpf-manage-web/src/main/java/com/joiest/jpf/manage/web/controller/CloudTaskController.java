package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudCompanyStaffInfo;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.util.*;

@Controller
@RequestMapping("/cloudTask")
public class CloudTaskController {

    @Autowired
    private CloudTaskServiceFacade cloudTaskServiceFacade;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    /**
     * 批量打款页
     */
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("cloudTask/index");
    }

    /**
     * 任务列表页
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(CloudTaskRequest request){
        CloudTaskResponse response = cloudTaskServiceFacade.getTasks(request);
        Map<String,Object> map = new HashMap<>();
        map.put("total",response.getCount());
        map.put("rows",response.getList());

        return map;
    }

    /**
     * 新建任务列表页
     */
    @RequestMapping("/addTask")
    @ResponseBody
    public ModelAndView addTask(){
        return new ModelAndView("cloudTask/addTask");
    }

    /**
     * 新任务提交操作
     * pay_cloud_company_staff 企业的自由职业者信息表
     * pay_cloud_staff_banks 自由职业者的银行卡信息表
     * pay_cloud_df_money 云账户充值记录表
     */
    @RequestMapping(value = "/submitTask", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String submitTask(String company_id, String company_name, @RequestParam("uploadfile") MultipartFile uploadfile) throws Exception{
        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(company_id);

        // 保存excel文件

        // 判断excel数据正确完整性


        // 解析xml
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());
        // 组装自由职业者信息数组
        List<CloudCompanyStaffInfo> staffInfosSuccess = new ArrayList<>();
        List<CloudCompanyStaffInfo> staffInfosFailed = new ArrayList<>();
        // 检查每条自由职业者信息的完整性
        CloudCompanyStaffInfo staffInfo = new CloudCompanyStaffInfo();
        for ( int i=4; i<list.size(); i++){
            // 外循环为一行excel记录
            Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
            staffInfo.setUid(company_id);
            staffInfo.setNickname(singlePerson.get(5));
            staffInfo.setMobile(singlePerson.get(7));
            staffInfo.setMerchNo(companyInfo.getMerchNo());
            staffInfo.setIdcard(singlePerson.get(6));
            staffInfo.setCreated(new Date());
            staffInfo.setUpdated(new Date());
            for ( int j=0; j<singlePerson.size()-1; j++){
                // 内循环为该记录的列值
                // 检查列数据的正确性
                // 第一列类型必须是0或者1
                if ( !singlePerson.get(0).equals("0") && !singlePerson.get(0).equals("1") ){
                    staffInfosFailed.add(staffInfo);
                }else{
                    // 前9列数据必填
                    if ( StringUtils.isBlank(singlePerson.get(j)) ){
                        staffInfosFailed.add(staffInfo);
                    }
                }
            }
            staffInfosSuccess.add(staffInfo);
        }
        UUID uuid = UUID.randomUUID();
        if ( staffInfosFailed.size() > 0 ){
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code","10001");
            responseMap.put("info","表格存在以下错误数据，请更改后重新上传");
            responseMap.put("data",staffInfosFailed);

            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),"E:\\"+uuid+".txt",false);
        }else{
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code","10000");
            responseMap.put("info","数据检测无误");
            responseMap.put("data",staffInfosSuccess);

            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),"E:\\"+uuid+".txt",false);
        }

        return uuid.toString();
    }

    /**
     * 列出解析成功或失败的自由职业者信息
     */
    @RequestMapping("/persons")
    @ResponseBody
    public ModelAndView persons(String data){
        return new ModelAndView("cloudTask/persons");
    }

    @RequestMapping(value = "/personsData", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String personsData(String data){

        return "";
    }
}
