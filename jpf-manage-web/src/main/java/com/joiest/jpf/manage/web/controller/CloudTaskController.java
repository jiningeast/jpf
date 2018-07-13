package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.CloudCompanyMoneyRequest;
import com.joiest.jpf.dto.CloudTaskRequest;
import com.joiest.jpf.dto.CloudTaskResponse;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
import java.math.BigInteger;
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

    @Autowired
    private CloudCompanyStaffServiceFacade cloudCompanyStaffServiceFacade;

    @Autowired
    private CloudStaffBanksServiceFacade cloudStaffBanksServiceFacade;

    @Autowired
    private BankServiceFacade bankServiceFacade;

    private static final Logger logger = LogManager.getLogger(CloudTaskController.class);

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
                CloudRemitExcelInfo staffInfo = new CloudRemitExcelInfo();
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
        if(!fileName.equals(Batchno)){
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
            responseMap.put("batchNo",Batchno);
            responseMap.put("money",""+companyMoney);
            responseMap.put("persons",""+count);
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

    /**
     * 列出解析成功或失败的自由职业者信息的具体读取数据操作
     */
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
    @RequestMapping(value = "/confirmPersons", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    @Transactional
    public JpfResponseDto confirmPersons(String companyId, String data, HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        // OSS上传excel文件
        // 先上传到本地
        /*UploadUtils uploadUtils = new UploadUtils();
        try {
            uploadUtils.doPost(httpRequest,httpResponse);
        }catch (Exception e){
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code","10002");
            responseMap.put("info","上传文件失败");

            return responseMap;
        }*/

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+data+".txt","GB2312");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        String batchNo = jsonMap.get("batchNo");
        String persons = ""+jsonMap.get("persons");
        String money = jsonMap.get("money");

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
        cloudTaskInfo.setAgentNo("CY1530862557288877");     // 待修改
        cloudTaskInfo.setMerchNo(companyInfo.getMerchNo());
        cloudTaskInfo.setCompanyType(companyInfo.getType());
        cloudTaskInfo.setBatchno(batchNo);
        cloudTaskInfo.setFilePath(ConfigUtil.getValue("TASK_PERSONS_FILE_PATH")+data+".txt");
        cloudTaskInfo.setStatus((byte)2);
        cloudTaskInfo.setCreated(new Date());
        int taskRes = cloudTaskServiceFacade.insTask(cloudTaskInfo);
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if ( taskRes <= 0 ){
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("新建任务失败");

            return jpfResponseDto;
        }

        // 新建一个待锁定的代付款批次订单
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        cloudCompanyMoneyInfo.setAgentNo("CY1530862557288877");   // 待修改
        cloudCompanyMoneyInfo.setMerchNo(companyInfo.getMerchNo());
        cloudCompanyMoneyInfo.setCommoney(new BigDecimal(jsonMap.get("money")));
        cloudCompanyMoneyInfo.setAddtime(new Date());
        cloudCompanyMoneyInfo.setUid(""+userInfo.getId());
        cloudCompanyMoneyInfo.setFid(""+new Date().getTime());   // 待修改
        cloudCompanyMoneyInfo.setVid((byte)1);       // 待修改
        cloudCompanyMoneyInfo.setIntro("");       //待修改
        cloudCompanyMoneyInfo.setMontype((byte)0);
        cloudCompanyMoneyInfo.setBatchstatus((byte)0);
        cloudCompanyMoneyInfo.setBatchno(batchNo);
        cloudCompanyMoneyInfo.setBatchitems(persons);
        cloudCompanyMoneyInfo.setBatchallmoney(new BigDecimal(money));
        cloudCompanyMoneyInfo.setBatchdealitems("0");
        cloudCompanyMoneyInfo.setBatchdealmoney(new BigDecimal("0"));
        cloudCompanyMoneyInfo.setBatchfailitems("0");
        cloudCompanyMoneyInfo.setBatchfailmoney(new BigDecimal("0"));
        cloudCompanyMoneyInfo.setFeemoney(new BigDecimal("0"));
        cloudCompanyMoneyInfo.setTaxmoney(new BigDecimal("0"));
        cloudCompanyMoneyInfo.setTaxmoremoney(new BigDecimal("0"));
        cloudCompanyMoneyInfo.setProfitmoney(new BigDecimal("0"));
        int companyMoneyRes = CloudCompanyMoneyServiceFacade.addRec(cloudCompanyMoneyInfo);
        if ( companyMoneyRes <= 0 ){
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("新建批次订单失败");

            return jpfResponseDto;
        }

        // 鉴权信息数据入库，此代码段暂存在此处
        Map<String,List< LinkedHashMap<String,String> >> jsonMapData = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > personsList = jsonMapData.get("data");
        CloudCompanyStaffInfo cloudCompanyStaffInfo = new CloudCompanyStaffInfo();

        for ( LinkedHashMap<String,String> singlePerson:personsList ){
            // 通过身份证号先判断企业有没有这个员工
            CloudCompanyStaffInfo info = new CloudCompanyStaffInfo();
            info.setIdcard(singlePerson.get("idno"));
            info.setMobile(singlePerson.get("phone"));
            info.setStatus((byte)1);
            CloudCompanyStaffInfo existStaff = cloudCompanyStaffServiceFacade.getStaffByInfo(info);
            int staffId;
            if ( existStaff.getId() == null ){
                // 插入员工信息
                cloudCompanyStaffInfo.setUid(""+userInfo.getId());
                cloudCompanyStaffInfo.setNickname(singlePerson.get("name"));
                cloudCompanyStaffInfo.setMobile(singlePerson.get("phone"));
                cloudCompanyStaffInfo.setMerchNo(companyInfo.getMerchNo());
                cloudCompanyStaffInfo.setStatus((byte)1);
                cloudCompanyStaffInfo.setIsActive((byte)1);     // 待修改
                cloudCompanyStaffInfo.setEmail("");       // 待修改
                cloudCompanyStaffInfo.setCode("");        // 待修改
                cloudCompanyStaffInfo.setIdcard(singlePerson.get("idno"));
                cloudCompanyStaffInfo.setUcardid(""+0);
                cloudCompanyStaffInfo.setCreated(new Date());
                cloudCompanyStaffInfo.setUpdated(new Date());
                staffId = cloudCompanyStaffServiceFacade.addStaff(cloudCompanyStaffInfo);
                if ( staffId > 0 ){
                    // 获取自增id
                    CloudCompanyStaffInfo info1 = new CloudCompanyStaffInfo();
                    info1.setIdcard(singlePerson.get("idno"));
                    info1.setMobile(singlePerson.get("phone"));
                    info1.setStatus((byte)1);
                    CloudCompanyStaffInfo info2 = cloudCompanyStaffServiceFacade.getStaffByInfo(info1);
                    staffId = Integer.parseInt(info2.getId());
                    logger.info(singlePerson.get("name")+"插入员工信息表成功");
                }
            }else{
                logger.info(singlePerson.get("name")+"员工已经存在，无需再插入");
                staffId = Integer.parseInt(existStaff.getId());
            }

            // 通过银行卡号和员工id先判断这个员工是否添加过这个银行卡
            CloudStaffBanksInfo existBank = cloudStaffBanksServiceFacade.getStaffBankByNumSid(singlePerson.get("bankNo"), BigInteger.valueOf(staffId));
            if ( existBank == null ){
                // 插入员工银行卡信息
                CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
                cloudStaffBanksInfo.setStaffid(Long.parseLong(""+staffId));
                BankInfo bankInfo = bankServiceFacade.getBankByName(singlePerson.get("bankName"));
                cloudStaffBanksInfo.setBankid(bankInfo.getId());
                cloudStaffBanksInfo.setBankno(singlePerson.get("bankNo"));
                cloudStaffBanksInfo.setBanknickname(singlePerson.get("name"));
                cloudStaffBanksInfo.setBankphone(singlePerson.get("phone"));
                cloudStaffBanksInfo.setBankname(singlePerson.get("bankName"));
                cloudStaffBanksInfo.setBankprovince("");      // 待修改
                cloudStaffBanksInfo.setBankcity("");          // 待修改
                cloudStaffBanksInfo.setBankActive("1");
                cloudStaffBanksInfo.setBankacctattr(String.valueOf(singlePerson.get("type")));
                int staffBanksRes = cloudStaffBanksServiceFacade.addStaffBank(cloudStaffBanksInfo);
                if ( staffBanksRes > 0 ){
                    logger.info(singlePerson.get("name")+"插入员工银行表成功");
                }
            }else{
                logger.info(singlePerson.get("name")+"的银行卡信息已经存在，无需再插入");
            }
        }

        return new JpfResponseDto();
    }

    /**
     * 任务详情页
     */
    @RequestMapping("/taskDetail")
    public ModelAndView taskDetail(){
        return new ModelAndView("cloudTask/detail");
    }

    /**
     * 任务详情页中的鉴权数据
     */
    @RequestMapping("/banksData")
    @ResponseBody
    public Map<String,Object> banksData(String taskId){

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total","");
        responseMap.put("rows","");

        return responseMap;
    }

    /**
     * 任务处理完成后点击锁定
     */
    @RequestMapping("/lockOrder")
    public void lockOrder(String taskId){
        // 新建批次订单
    }
}
