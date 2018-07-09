package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.ExcelDealUtils;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cloudTask")
public class CloudTaskController {

    @Autowired
    private CloudTaskServiceFacade cloudTaskServiceFacade;

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
    @RequestMapping("/submitTask")
    @ResponseBody
    public Map<String, Object> submitTask(String company_id, String company_name, @RequestParam("uploadfile") MultipartFile uploadfile) throws Exception{
        // 保存excel文件

        // 判断excel数据正确完整性


        // 解析xml
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());

        // 展示给前台
        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",map.size()-4);
        responseMap.put("rows",list);

        return responseMap;

        // 上传
    }

    /**
     * 获取上传的excel文件数据
     */
    @RequestMapping("/getExcelData")
    @ResponseBody
    public Map<String,Object> getExcelData(MultipartFile uploadfile) throws Exception{
        // 解析xml
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());

        /*for (Object obj:list){

        }*/

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",map.size()-4);
        responseMap.put("rows",list);

        return responseMap;
    }
}
