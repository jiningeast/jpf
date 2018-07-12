package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudRemitExcelInfo;
import com.joiest.jpf.entity.CloudTaskInfo;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.CloudCompanyMoneyServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudTaskServiceFacade;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/cloudTask")
public class CloudTaskController {

    @Autowired
    private CloudTaskServiceFacade cloudTaskServiceFacade;

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private CloudCompanyMoneyServiceFacade CloudCompanyMoneyServiceFacade;

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
        //获取当前的文件名
        String fileNameAll = uploadfile.getOriginalFilename();
        String fileName=fileNameAll.substring(0,fileNameAll.lastIndexOf("."));
        // 解析xml
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        //循环判断每一条数据

        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());


        // 组装自由职业者信息数组
        List<CloudRemitExcelInfo> staffInfosSuccess = new ArrayList<>();
        List<CloudRemitExcelInfo> staffInfosFailed = new ArrayList<>();
        // 检查每条自由职业者信息的完整性
        CloudRemitExcelInfo staffInfo = new CloudRemitExcelInfo();
        UUID uuid = UUID.randomUUID();
        String Batchno = "";
        String companyMoney_Str;
        String companyMoney_StrSame;
        double companyMoney = 0;
        double companyMoneySame = 0;
        int count=0;
        int samecount=0;
        Map<String,Object> responseMap = new HashMap<>();
        for ( int i=0; i<list.size(); i++){
            // 外循环为一行excel记录
            if(i >= 4){
                Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
                staffInfo.setType(Byte.parseByte(singlePerson.get(0)));
                staffInfo.setBankName(singlePerson.get(1));
                staffInfo.setProvince(singlePerson.get(2));
                staffInfo.setCity(singlePerson.get(3));
                staffInfo.setBankNo(singlePerson.get(4));
                staffInfo.setName(singlePerson.get(5));
                staffInfo.setIDNo(singlePerson.get(6));
                staffInfo.setPhone(singlePerson.get(7));
                staffInfo.setMoney(new BigDecimal(singlePerson.get(8)));
                staffInfo.setMemo(singlePerson.get(9));
                byte flag = 1;
                for ( int j=0; j<singlePerson.size()-1; j++){
                    // 内循环为该记录的列值
                    // 检查列数据的正确性
                    // 第一列类型必须是0或者1
                    if ( !singlePerson.get(0).equals("0") && !singlePerson.get(0).equals("1") ){
                        flag = 0;
                    }else{
                        // 前9列数据必填
                        if ( StringUtils.isBlank(singlePerson.get(j)) ){
                            flag = 0;
                        }
                    }
                }
                if ( flag == 0 ){
                    staffInfosFailed.add(staffInfo);
                }else{
                    staffInfosSuccess.add(staffInfo);
                }
                //总数
                count++;
                //总钱数==获取decimal方式
                companyMoney_Str = new DecimalFormat("#.00").format(Double.parseDouble(singlePerson.get(8))) ;
                companyMoney = BigDecimalCalculateUtils.add(companyMoney,Double.parseDouble(companyMoney_Str));
            }else{

                Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
                for (int j=0; j<singlePerson.size()-1; j++){

                    if(i==2){
                       Batchno=singlePerson.get(0);
                       samecount=Integer.parseInt(singlePerson.get(1));
                        companyMoney_Str = new DecimalFormat("#.00").format(Double.parseDouble(singlePerson.get(2))) ;
                        companyMoneySame=Double.parseDouble(companyMoney_Str);
                   }

                }
            }

        }
        if(fileName.equals(Batchno)==false){
            responseMap.put("code","10004");
            responseMap.put("info","批次号与文件名不一致请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(count !=samecount){
            responseMap.put("code","10004");
            responseMap.put("info","总笔数与实际笔数不符请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(count>1000){
            responseMap.put("code","10004");
            responseMap.put("info","最大支持1000条数据请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(companyMoney!=companyMoneySame){
            responseMap.put("code","10004");
            responseMap.put("info","总金额与实际金额不符请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if ( staffInfosFailed.size() > 0 ){
            responseMap.put("code","10001");
            responseMap.put("info","表格存在以下错误数据，请更改后重新上传");
            responseMap.put("data",staffInfosFailed);

            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else{
            responseMap.put("code","10000");
            responseMap.put("info","数据检测无误");
            responseMap.put("data",staffInfosSuccess);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();

        }

    }

    /**
     * 列出解析成功或失败的自由职业者信息
     */
    @RequestMapping("/persons")
    @ResponseBody
    public ModelAndView persons(String data,ModelMap modelMap){
        String up=data;
        up = StringUtils.strip(up,"\"");
        up = StringUtils.stripEnd(up,"\"");
        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+up+".txt","GB2312");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        String code=jsonMap.get("code");
        String info=jsonMap.get("info");
        Map<String,List< LinkedHashMap<String,String> >> jsonMaps = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > list = jsonMaps.get("data");
        modelMap.addAttribute("data",data);
        modelMap.addAttribute("code",code);
        modelMap.addAttribute("info",info);
        modelMap.addAttribute("total",list.size());
        return new ModelAndView("cloudTask/persons",modelMap);

    }

    @RequestMapping(value = "/personsData", produces = "application/json;charset=utf-8")
    @ResponseBody
    public Map<String, Object> personsData(String data){
        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+data+".txt","GB2312");
        Map<String,List< LinkedHashMap<String,String> >> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > list = jsonMap.get("data");

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",list.size());
        responseMap.put("rows",jsonMap.get("data"));

        return responseMap;
    }

    /**
     * 运营确认excel文件内容无误后点击确认的操作
     */
    @RequestMapping(value = "/confirmPersons")
    @ResponseBody
    public Map<String,Object> confirmPersons(String companyId, String batchNo, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        // OSS上传excel文件
        // 先上传到本地
        UploadUtils uploadUtils = new UploadUtils();
        try {
            uploadUtils.doPost(httpRequest,httpResponse);
        }catch (Exception e){
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code",10002);
            responseMap.put("info","上传文件失败");

            return responseMap;
        }

        // 查询商户信息
        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(companyId);

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 新建任务记录
        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
        cloudTaskInfo.setOpratorId(""+userInfo.getId());
        cloudTaskInfo.setOpratorName(userInfo.getUserName());
        cloudTaskInfo.setCompanyId(companyInfo.getId());
        cloudTaskInfo.setCompanyName(companyInfo.getName());
        cloudTaskInfo.setMerchNo(companyInfo.getMerchNo());
        cloudTaskInfo.setBatchno(batchNo);
        cloudTaskInfo.setStatus((byte)0);
        cloudTaskInfo.setCreated(new Date());
        int taskRes = cloudTaskServiceFacade.insTask(cloudTaskInfo);
        if ( taskRes > 0 ){
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code",10000);
            responseMap.put("info","新建任务成功");

            return responseMap;
        }else{
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code",10001);
            responseMap.put("info","新建任务失败");

            return responseMap;
        }

        // 新建一个待锁定的代付款批次订单

        /*CloudCompanyMoneyRequest request = new CloudCompanyMoneyRequest();
        request.setAgentNo("CY1530862557288877");   // 待修改
        request.setMerchNo(companyInfo.getMerchNo());
        request.setCommoney();
        request.setAddtime();
        request.setUid();
        request.setFid();
        request.setVid();
        request.setIntro();
        request.setMontype();
        request.setBatchstatus();
        request.setBatchno();
        request.setBatchitems();
        request.setBatchallmoney();
        request.setBatchdealitems();
        request.setBatchdealmoney();
        request.setBatchfailitems();
        request.setBatchfailmoney();
        request.setUpdatetime();
        request.setFeemoney();
        request.setTaxmoney();
        request.setTaxmoremoney();
        request.setProfitmoney();
        CloudCompanyMoneyServiceFacade.addRec(request);*/
    }

    /**
     * 任务处理完成后点击锁定
     */
    @RequestMapping("/lockOrder")
    public void lockOrder(String taskId){
        // 新建批次订单
    }
}
