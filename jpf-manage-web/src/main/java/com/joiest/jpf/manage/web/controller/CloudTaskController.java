package com.joiest.jpf.manage.web.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.manage.web.constant.ManageConstants;
import com.joiest.jpf.manage.web.util.SmsUtils;
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

    @Autowired
    private CloudDfMoneyServiceFacade cloudDfMoneyServiceFacade;

    @Autowired
    private CloudInterfaceStreamServiceFacade cloudInterfaceStreamServiceFacade;

    @Autowired
    private CloudCompanyAgentServiceFacade cloudCompanyAgentServiceFacade;

    @Autowired
    private CloudCompanySalesServiceFacade cloudCompanySalesServiceFacade;

    @Autowired
    private BankCardServiceFacade bankCardServiceFacade;

    @Autowired
    private CloudCompactServiceFacade cloudCompactServiceFacade;

    @Autowired
    private CloudRechargeServiceFacade cloudRechargeServiceFacade;

    @Autowired
    private MerTypeServiceFacade merTypeServiceFacade;

    @Autowired
    private CloudCompactStaffServiceFacade cloudCompactStaffServiceFacade;

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
    public String submitTask(String company_id, String company_name, @RequestParam("uploadfile") MultipartFile uploadfile, HttpServletRequest httpRequest, HttpServletResponse httpResponse) throws Exception{
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
        String contractNo = "";
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
                        contractNo = singlePerson.get(3);
                   }
                }
            }
        }
        if(!fileName.equals(Batchno)){
            responseMap.put("code","10004");
            responseMap.put("info","批次号与文件名不一致请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(count !=samecount){
            responseMap.put("code","10004");
            responseMap.put("info","总笔数与实际笔数不符请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(count>1000){
            responseMap.put("code","10004");
            responseMap.put("info","最大支持1000条数据请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if(companyMoney!=companyMoneySame){
            responseMap.put("code","10004");
            responseMap.put("info","总金额与实际金额不符请修改后上传！");
            responseMap.put("data",staffInfosFailed);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else if ( staffInfosFailed.size() > 0 ){
            responseMap.put("code","10001");
            responseMap.put("info","表格存在以下错误数据，请更改后重新上传");
            responseMap.put("data",staffInfosFailed);

            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);
            return uuid.toString();
        }else{
            // OSS上传excel文件
            String savePre = ConfigUtil.getValue("EXCEL_PATH");
            String path = PhotoUtil.saveFile(uploadfile, httpRequest, savePre);
            Map<String,Object> requestMap = new HashMap<>();
            requestMap.put("path",path);
            String url = ConfigUtil.getValue("OSS_URL");
            String response = OkHttpUtils.postForm(url,requestMap);

            // 增加==OSS接口流水==
            CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
            cloudInterfaceStreamInfo.setType((byte)0);
            cloudInterfaceStreamInfo.setRequestUrl(url);
            cloudInterfaceStreamInfo.setRequestContent(path);
            cloudInterfaceStreamInfo.setResponseContent(response);
            cloudInterfaceStreamInfo.setAddtime(new Date());
            cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

            // 写入缓存文件
            responseMap.put("code","10000");
            responseMap.put("info","数据检测无误");
            responseMap.put("batchNo",Batchno);
            responseMap.put("money",""+companyMoney);
            responseMap.put("persons",""+count);
            responseMap.put("contractNo",contractNo);
            responseMap.put("data",staffInfosSuccess);
            responseMap.put("ossUrl",response);
            LogsCustomUtils.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);

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
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+up+".txt","GB2312");
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
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+data+".txt","GB2312");
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
    public JpfResponseDto confirmPersons(String companyId, String data, HttpServletRequest httpRequest) {

        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH")+data+".txt","GB2312");
        Map<String,String> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        String batchNo = jsonMap.get("batchNo");
        String persons = ""+jsonMap.get("persons");
        String money = jsonMap.get("money");
        String contractNo = jsonMap.get("contractNo");

        // 判断任务记录中批次号的唯一性
        CloudTaskInfo existTask = cloudTaskServiceFacade.getOneTaskByBatchNo(batchNo);
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        if ( existTask.getId() != null ){
            jpfResponseDto.setRetCode("10001");
            jpfResponseDto.setRetMsg("该批次号已经存在，请不要重复上传");

            return jpfResponseDto;
        }

        // 查询商户信息
        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getRecById(companyId);

        // 查询操作人id和姓名
        HttpSession session = httpRequest.getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(ManageConstants.USERINFO_SESSION);

        // 新建任务记录
        String agentNo;
        if ( httpRequest.getRequestURI().indexOf("xinxiangfuwu.com") > 0 ){
            agentNo = ConfigUtil.getValue("XINXIANG_AGENT_NO");
        }else{
            agentNo = ConfigUtil.getValue("ZHENXIANG_AGENT_NO");
        }
        CloudTaskInfo cloudTaskInfo = new CloudTaskInfo();
        cloudTaskInfo.setOpratorId(""+userInfo.getId());
        cloudTaskInfo.setOpratorName(userInfo.getUserName());
        cloudTaskInfo.setCompanyId(companyInfo.getId());
        cloudTaskInfo.setCompanyName(companyInfo.getName());
        cloudTaskInfo.setAgentNo(agentNo);
        cloudTaskInfo.setMerchNo(companyInfo.getMerchNo());
        cloudTaskInfo.setCompanyType(companyInfo.getType());
        cloudTaskInfo.setBatchno(batchNo);
        cloudTaskInfo.setPersons(Integer.parseInt(persons));    // 人数总计
        cloudTaskInfo.setMoney(new BigDecimal(money));
        cloudTaskInfo.setContractNo(contractNo);
        cloudTaskInfo.setFilePath(ConfigUtil.getValue("CACHE_PATH")+data+".txt");   // 待修改
        cloudTaskInfo.setStatus((byte)1);   // 任务状态 0=未处理 1=处理中 2=完成 3=失败
        cloudTaskInfo.setIsLock((byte)0);   // 任务锁定 0=未锁 1=锁定
        cloudTaskInfo.setCreated(new Date());
        String taskRes = cloudTaskServiceFacade.insTask(cloudTaskInfo);
        if ( Integer.parseInt(taskRes) <= 0 ){
            jpfResponseDto.setRetCode("10002");
            jpfResponseDto.setRetMsg("新建任务失败");

            return jpfResponseDto;
        }

        // 新建一个待锁定的代付款批次订单
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        String companyMoneyId;
        cloudCompanyMoneyInfo.setAgentNo(agentNo);   // 待修改
        cloudCompanyMoneyInfo.setMerchNo(companyInfo.getMerchNo());
        cloudCompanyMoneyInfo.setCommoney(new BigDecimal(jsonMap.get("money")));
        cloudCompanyMoneyInfo.setAddtime(new Date());
        cloudCompanyMoneyInfo.setUid(""+companyInfo.getId());   // 待修改
        cloudCompanyMoneyInfo.setFid(contractNo);   // 合同编号
        cloudCompanyMoneyInfo.setVid((byte)8);
        cloudCompanyMoneyInfo.setIntro("");
        cloudCompanyMoneyInfo.setMontype((byte)0);      // 发放状态 -1删除 0=待锁定 1=待付款 2=处理完成,3=处理完成(部分失败),4=处理失败
        cloudCompanyMoneyInfo.setBatchstatus((byte)0);  // 打款状态 0=未处理 1=部分失败 2=全部失败 3=全部成功
        cloudCompanyMoneyInfo.setBatchno(batchNo);                          // 用户批次号
        cloudCompanyMoneyInfo.setBatchitems(persons);                       // 批次总笔数
        cloudCompanyMoneyInfo.setBatchallmoney(new BigDecimal(money));      // 批次总金额
        cloudCompanyMoneyInfo.setBatchdealitems("0");                        // 批次成功笔数
        cloudCompanyMoneyInfo.setBatchdealmoney(new BigDecimal("0"));   // 批次成功总金额
        cloudCompanyMoneyInfo.setBatchfailitems("0");                        // 批次失败笔数
        cloudCompanyMoneyInfo.setBatchfailmoney(new BigDecimal("0"));   // 批次失败总金额
        // 查询代理商户和业务商户的费率，计算总费率
        CloudCompanyAgentInfo agentInfo = cloudCompanyAgentServiceFacade.getAgentByAgentNo(agentNo);
        CloudCompanySalesInfo salesInfo = cloudCompanySalesServiceFacade.getSalesBySalesNo(companyInfo.getMerchNo());
        Double totalRate = Double.parseDouble(agentInfo.getAgentRate().toString()) + Double.parseDouble(salesInfo.getSalesRate().toString());
        Double moneyDouble = new Double(money);
        Double feeMoney = totalRate * moneyDouble;
        cloudCompanyMoneyInfo.setFeemoney(new BigDecimal(feeMoney));   // 服务费金额：实发金额*服务费率
        // 增值税金额
        Double addedValueTax = new Double(ConfigUtil.getValue("ADDED_VALUE_TAX"));
        Double taxMoney = ( moneyDouble + feeMoney ) / ( 1 + addedValueTax ) * addedValueTax;
        cloudCompanyMoneyInfo.setTaxmoney(new BigDecimal(taxMoney));
        // 增值税附加金额
        Double addedValueTaxAddtion = new Double(ConfigUtil.getValue("ADDED_VALUE_TAX_ADDITION"));
        Double addedValueTaxAddtionMoney = taxMoney*addedValueTaxAddtion;
        cloudCompanyMoneyInfo.setTaxmoremoney(new BigDecimal(addedValueTaxAddtionMoney));
        // 毛利金额
        Double individualTax = new Double(ConfigUtil.getValue("INDIVIDUAL_TAX"));
        Double supposePay = moneyDouble / (1-individualTax);   // 应发金额
        Double profit = (moneyDouble + feeMoney) - (supposePay + taxMoney + addedValueTaxAddtionMoney);
        cloudCompanyMoneyInfo.setProfitmoney(new BigDecimal(profit));      // 毛利金额
        // 判断有没有已经存在的合同编号
        CloudCompanyMoneyInfo existCompanyMoneyInfo = CloudCompanyMoneyServiceFacade.getRecByFid(contractNo);
        if ( existCompanyMoneyInfo.getId() != null ){
            jpfResponseDto.setRetCode("10003");
            jpfResponseDto.setRetMsg("该合同编号已经存在，请修改后上传");

            return jpfResponseDto;
        }
        int companyMoneyRes = CloudCompanyMoneyServiceFacade.addRec(cloudCompanyMoneyInfo);
        if ( companyMoneyRes <= 0 ){
            jpfResponseDto.setRetCode("10004");
            jpfResponseDto.setRetMsg("新建批次订单失败");

            return jpfResponseDto;
        }else{
            CloudCompanyMoneyInfo cloudCompanyMoneyInfo1 = CloudCompanyMoneyServiceFacade.getRecByBatchNo(batchNo);
            companyMoneyId = cloudCompanyMoneyInfo1.getId();
        }

        // 鉴权信息数据入库，此代码段暂存在此处
        Map<String,List< LinkedHashMap<String,String> >> jsonMapData = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > personsList = jsonMapData.get("data");
        CloudCompanyStaffInfo cloudCompanyStaffInfo = new CloudCompanyStaffInfo();

        for ( LinkedHashMap<String,String> singlePerson:personsList ){
            // 通过身份证号和手机号先判断企业有没有这个员工
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
                cloudCompanyStaffInfo.setIsActive((byte)0);
                cloudCompanyStaffInfo.setEmail("");
                cloudCompanyStaffInfo.setCode("");
                cloudCompanyStaffInfo.setIdcard(singlePerson.get("idno"));
                cloudCompanyStaffInfo.setUcardid(""+0);
                cloudCompanyStaffInfo.setCreated(new Date());
                cloudCompanyStaffInfo.setUpdated(new Date());
                staffId = Integer.parseInt(cloudCompanyStaffServiceFacade.addStaff(cloudCompanyStaffInfo));
                if ( staffId > 0 ){
                    logger.info(singlePerson.get("name")+"插入员工信息表成功");
                }
            }else{
                logger.info(singlePerson.get("name")+"员工已经存在，无需再插入");
                staffId = Integer.parseInt(existStaff.getId());
            }

            // 通过银行卡号、手机号和员工id先判断这个员工是否添加过这个银行卡
            CloudStaffBanksInfo searchBank = new CloudStaffBanksInfo();
            searchBank.setStaffid(Long.parseLong(""+staffId));
            searchBank.setBankno(singlePerson.get("bankNo"));
            searchBank.setBankphone(singlePerson.get("phone"));
            searchBank.setBankActive("1");
            CloudStaffBanksInfo existBank = cloudStaffBanksServiceFacade.getStaffBankByInfo(searchBank);
            if ( existBank.getId() == null ){
                // 插入员工银行卡信息
                CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
                cloudStaffBanksInfo.setStaffid(Long.parseLong(""+staffId));
                BankInfo bankInfo = bankServiceFacade.getBankByName(singlePerson.get("bankName"));
                cloudStaffBanksInfo.setBankid(bankInfo.getId());
                cloudStaffBanksInfo.setBankno(singlePerson.get("bankNo"));
                cloudStaffBanksInfo.setBanknickname(singlePerson.get("name"));
                cloudStaffBanksInfo.setBankphone(singlePerson.get("phone"));
                cloudStaffBanksInfo.setBankname(singlePerson.get("bankName"));
                cloudStaffBanksInfo.setBankprovince(singlePerson.get("province"));
                cloudStaffBanksInfo.setBankcity(singlePerson.get("city"));
                cloudStaffBanksInfo.setBankActive("0");
                cloudStaffBanksInfo.setBankacctattr(String.valueOf(singlePerson.get("type")));
                int staffBanksRes = cloudStaffBanksServiceFacade.addStaffBank(cloudStaffBanksInfo);
                if ( staffBanksRes > 0 ){
                    logger.info(singlePerson.get("name")+"插入员工银行表成功");
                }
            }else{
                logger.info(singlePerson.get("name")+"的银行卡信息已经存在，无需再插入");
            }

            // 根据卡号查询银行编码
            BankCardInfo bankCardInfo = bankCardServiceFacade.getBankCardByCardNO(singlePerson.get("bankNo"));
            if ( bankCardInfo.getCode() == null ){
                logger.info(singlePerson.get("name")+"的银行卡号是"+singlePerson.get("bankNo")+"，未查找到银行编码");
            }

            // 按人员生成代付记录
            CloudDfMoneyInfo cloudDfMoneyInfo = new CloudDfMoneyInfo();
            cloudDfMoneyInfo.setAgentNo(agentNo);
            cloudDfMoneyInfo.setMerchNo(companyInfo.getMerchNo());
            cloudDfMoneyInfo.setUid(Long.parseLong(""+userInfo.getId()));
            cloudDfMoneyInfo.setFid(companyInfo.getId());   // 企业id
            cloudDfMoneyInfo.setBusstaffid(Long.parseLong(""+staffId));
            cloudDfMoneyInfo.setUsername(singlePerson.get("phone"));
            cloudDfMoneyInfo.setCommoney(new BigDecimal(String.valueOf(singlePerson.get("money"))));
            cloudDfMoneyInfo.setBankno(singlePerson.get("bankNo"));
            cloudDfMoneyInfo.setBanknickname(singlePerson.get("name"));
            cloudDfMoneyInfo.setIdno(singlePerson.get("idno"));
            cloudDfMoneyInfo.setBankphone(singlePerson.get("phone"));
            cloudDfMoneyInfo.setBankname(singlePerson.get("bankName"));     // 开户行
            cloudDfMoneyInfo.setBankprovince(singlePerson.get("province"));
            cloudDfMoneyInfo.setBankcity(singlePerson.get("city"));
            cloudDfMoneyInfo.setBanktype(singlePerson.get("bankName"));         // 卡类型 例如：建行 工商
            cloudDfMoneyInfo.setBankcode(bankCardInfo.getCode());
            cloudDfMoneyInfo.setBankacctattr(Integer.parseInt(String.valueOf(singlePerson.get("type"))));
            cloudDfMoneyInfo.setAddtime(new Date());
            cloudDfMoneyInfo.setRealname(singlePerson.get("name"));
            cloudDfMoneyInfo.setMontype(1);
            cloudDfMoneyInfo.setRemark(singlePerson.get("memo"));
            cloudDfMoneyInfo.setVid(1);      // 待修改
            cloudDfMoneyInfo.setIsActive(1);
            cloudDfMoneyInfo.setContent("");    // 待修改
            cloudDfMoneyInfo.setOperastate(0);  // 待修改
            cloudDfMoneyInfo.setTranno("");
            cloudDfMoneyInfo.setOrderid("");
            cloudDfMoneyInfo.setOrderids("");
            // 计算应发金额（税前）
            Double tax = new Double(ConfigUtil.getValue("INDIVIDUAL_TAX"));
            Double num1 = new Double(String.valueOf(singlePerson.get("money")));
            Double num2 = new Double("1")-tax;
            Double beforeTaxMoney = num1 / num2;
            cloudDfMoneyInfo.setPayablemoney(new BigDecimal(beforeTaxMoney));
            Double individualTaxMoney = beforeTaxMoney * tax;
            cloudDfMoneyInfo.setWithholdmoney(new BigDecimal(individualTaxMoney));
            cloudDfMoneyInfo.setInvostatus(1);
            cloudDfMoneyInfo.setCompanyMoneyId(companyMoneyId);
            cloudDfMoneyInfo.setPactno(contractNo);
            long dfMoneyRes = cloudDfMoneyServiceFacade.addDfMoney(cloudDfMoneyInfo);
            if ( dfMoneyRes > 0 ){
                logger.info(singlePerson.get("name")+"插入员工打款记录表成功");
            }else{
                logger.info(singlePerson.get("name")+"插入员工打款记录表失败");
            }

            // 新增个人合同记录
            CloudCompactStaffInfo cloudCompactStaffInfo = new CloudCompactStaffInfo();
            String compactNo = StringUtils.substring(singlePerson.get("idno"),-6) + new Date().getTime();    // 自由职业者合同编号：身份证后六位+时间戳
            cloudCompactStaffInfo.setCompactNo(compactNo);
            cloudCompactStaffInfo.setStaffid(Long.parseLong(""+staffId));
            cloudCompactStaffInfo.setDfid(dfMoneyRes);
            cloudCompactStaffInfo.setPactno(contractNo);
            // 查询合同内容
            CloudCompactInfo cloudCompactInfo = cloudCompactServiceFacade.getRecByType((byte)1);
            cloudCompactStaffInfo.setContent(cloudCompactInfo.getContent());
            cloudCompactStaffInfo.setCompactActive((byte)0);
            cloudCompactStaffInfo.setCompactid(Long.parseLong(cloudCompactInfo.getId()));
            // 获取充值信息中需求字段相关内容
            CloudRechargeRequest cloudRechargeRequest = new CloudRechargeRequest();
            cloudRechargeRequest.setPactno(contractNo);
            CloudRechargeResponse cloudRechargeResponse = cloudRechargeServiceFacade.getRecords(cloudRechargeRequest);
            CloudRechargeInfo cloudRechargeInfo = cloudRechargeResponse.getList().get(0);
            List<MerchantTypeInfo> typesList = merTypeServiceFacade.getTypesByCatpath(cloudRechargeInfo.getNeedcatpath());
            cloudCompactStaffInfo.setTicketid(typesList.get(1).getCatid());
            cloudCompactStaffInfo.setTicketcontent(typesList.get(1).getCat());
            cloudCompactStaffInfo.setEntryid(""+typesList.get(2).getCatid());
            cloudCompactStaffInfo.setEntryname(typesList.get(2).getCat());
            cloudCompactStaffInfo.setCreated(new Date());
            int compactStaffRes = cloudCompactStaffServiceFacade.insRecord(cloudCompactStaffInfo);
            if ( compactStaffRes > 0 ){
                logger.info(singlePerson.get("name")+"个人合同记录插入成功");
            }else{
                logger.info(singlePerson.get("name")+"个人合同记录插入失败");
            }

            // 鉴权功能
            CheckBanksRequest checkBanksRequest = new CheckBanksRequest();
            checkBanksRequest.setAccountNo(singlePerson.get("bankNo"));
            checkBanksRequest.setIdCard(singlePerson.get("idno"));
            checkBanksRequest.setMobile(singlePerson.get("phone"));
            checkBanksRequest.setName(singlePerson.get("name"));
            checkBanksRequest.setDateTime(new Date());
            checkBanks(checkBanksRequest);
        }

        return new JpfResponseDto();
    }

    /**
     * 任务详情页
     */
    @RequestMapping("/taskDetail")
    public ModelAndView taskDetail(String taskId, ModelMap modelMap){
        modelMap.addAttribute("taskId",taskId);

        return new ModelAndView("cloudTask/detail",modelMap);
    }

    /**
     * 任务详情页中的鉴权数据
     */
    @RequestMapping("/banksData")
    @ResponseBody
    public Map<String,Object> banksData(String taskId){
        // 获取任务详情
        CloudTaskInfo cloudTaskInfo = cloudTaskServiceFacade.getOneTask(taskId);
        String filePath = cloudTaskInfo.getFilePath();
        String fileContent = ToolUtils.readFromFile(filePath,"GB2312");
        Map<String,List< LinkedHashMap<String,String> >> jsonMapData = JsonUtils.toObject(fileContent,HashMap.class);
        List< LinkedHashMap<String,String> > personsList = jsonMapData.get("data");

        List<CloudStaffBanksInfo> list = new ArrayList<>();
        for ( LinkedHashMap<String,String> singlePerson:personsList ){
            CloudStaffBanksInfo searchInfo = new CloudStaffBanksInfo();
            searchInfo.setBankno(singlePerson.get("bankNo"));
            searchInfo.setBankActive("1");
            searchInfo.setBankphone(singlePerson.get("phone"));
            CloudStaffBanksInfo cloudStaffBanksInfo = cloudStaffBanksServiceFacade.getStaffBankByInfo(searchInfo);
            list.add(cloudStaffBanksInfo);
        }

        Map<String,Object> responseMap = new HashMap<>();
        responseMap.put("total",list.size());
        responseMap.put("rows",list);

        return responseMap;
    }

    /**
     * 任务处理完成后点击锁定
     */
    @RequestMapping("/lockOrder")
    @ResponseBody
    public JpfResponseDto lockOrder(String taskId){
        // 获取任务信息
        CloudTaskInfo cloudTaskInfo = cloudTaskServiceFacade.getOneTask(taskId);

        // 更新任务为锁定
        CloudTaskInfo upTaskInfo = new CloudTaskInfo();
        upTaskInfo.setId(taskId);
        upTaskInfo.setIsLock((byte)1);
        int taskRes = cloudTaskServiceFacade.updateColumn(upTaskInfo);

        // 获取批次订单信息
        CloudCompanyMoneyInfo cloudCompanyMoneyInfo = CloudCompanyMoneyServiceFacade.getRecByBatchNo(cloudTaskInfo.getBatchno());

        // 更新批次订单为已锁定
        CloudCompanyMoneyInfo upCompanyMoneyInfo = new CloudCompanyMoneyInfo();
        upCompanyMoneyInfo.setId(cloudCompanyMoneyInfo.getId());
        upCompanyMoneyInfo.setMontype((byte)1);
        int companyMoneyRes = CloudCompanyMoneyServiceFacade.updateColumn(upCompanyMoneyInfo);

        //查询用户是否实名签约
        String webName = "欣享科技";
        String heTongUrl = "http://10.10.18.15:8080/#/Identityno";  //实名签约地址
        String shiMingUrl = "http://10.10.18.15:8080/#/Idtesttwo";//合同签约地址

        String company_money_id = cloudCompanyMoneyInfo.getId(); //关联批次订单表的id
        CloudDfMoneyRequest dfRequest = new CloudDfMoneyRequest();
        dfRequest.setCompanyMoneyId(company_money_id);
        List<CloudDfMoneyInfo> dfMoneyInfoList=  cloudDfMoneyServiceFacade.getAllBySective(dfRequest);

        //记录pay_cloud_interface_stream表操作记录
        CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();


        Date date = new Date();
        String dateTime = date.toString();

        //循环发送短信
        for (int i = 0; i < dfMoneyInfoList.size() ; i++) {

            String banknickname = dfMoneyInfoList.get(i).getBanknickname();//收款人
            String mobile = dfMoneyInfoList.get(i).getBankphone(); //手机号
            Long busstaffid = dfMoneyInfoList.get(i).getBusstaffid(); //员工ID

            //短信发送
            String content = "";

            if ( dfMoneyInfoList.get(i).getCompactStaffCompactActive() != "1" ){ //未签合同
                content = "尊敬的"+banknickname+",委托"+webName+"为您进行结算服务,需要您在["+webName+"]平台";//短信内容
                content += "签约合同。点击： "+heTongUrl;//短信内容
            }
            if ( dfMoneyInfoList.get(i).getCompanyStaffIsActice() != "1" ){ //未实名签约
                content = "尊敬的"+banknickname+",委托"+webName+"为您进行结算服务,需要您在["+webName+"]平台";//短信内容
                content += "签约。点击： "+shiMingUrl;//短信内容
            }
            if ( dfMoneyInfoList.get(i).getCompanyStaffIsActice() != "1" && dfMoneyInfoList.get(i).getCompanyStaffIsActice() != "1" ){ //未实名签约
                content = "尊敬的"+banknickname+",委托"+webName+"为您进行结算服务,需要您在["+webName+"]平台";//短信内容
                content += "签约认证并完成合同签约。点击： "+shiMingUrl;//短信内容
            }
            if( content != "" ){
                //发送短信
                Map<String,String> retsult = SmsUtils.send(mobile,content);
                String requestUrl = retsult.get("requestUrl"); //请求Url
                String requestParam = retsult.get("requestParam");//请求参数
                String response =  retsult.get("response");//返回结果json字符串

                //存取短信接口调用记录
                cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
                cloudInterfaceStreamInfo.setRequestContent(requestParam);
                cloudInterfaceStreamInfo.setType((byte)2);
                cloudInterfaceStreamInfo.setResponseContent(response);
                cloudInterfaceStreamInfo.setCompanyMoneyId(company_money_id);
                cloudInterfaceStreamInfo.setTaskId(taskId);
                cloudInterfaceStreamInfo.setStaffId(busstaffid.toString());
                cloudInterfaceStreamInfo.setAddtime(date);
                cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);

                //json---转换代码---
                Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>() {});
                //String result=responseMap.get("code");
                //返回值10000 代表成功
                //短信发送是否成功  之后 如何处理 ？？？？？？？？

            }

        }


        return new JpfResponseDto();
    }

    /**
     * 鉴权操作
     * 返回 1=已经鉴权过 2=流水创建失败 3=鉴权失败 4=鉴权成功
     */
    public int checkBanks(CheckBanksRequest checkBanksRequest){
        // 先查询这个银行卡号和手机号是否已鉴权过
        CloudStaffBanksInfo isCheckedInfo = new CloudStaffBanksInfo();
        isCheckedInfo.setBankno(checkBanksRequest.getAccountNo());
        isCheckedInfo.setBankphone(checkBanksRequest.getMobile());
        CloudStaffBanksInfo queryRecord = cloudStaffBanksServiceFacade.getStaffBankByInfo(isCheckedInfo);
        if ( queryRecord.getBankActive().equals("1") ){
            return 1;
        }

        // 拼接鉴权4要素参数并触发接口
        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("accountNo",checkBanksRequest.getAccountNo());
        requestMap.put("idCard",checkBanksRequest.getIdCard());
        requestMap.put("mobile",checkBanksRequest.getMobile());
        requestMap.put("name",checkBanksRequest.getName());
        requestMap.put("dateTime",checkBanksRequest.getDateTime());
        String sign = Md5Encrypt.md5(ToolUtils.mapToUrl(requestMap) + ConfigUtil.getValue("API_SECRET")).toUpperCase();
        requestMap.put("sign",sign);
        String requestUrl = ConfigUtil.getValue("CLOUD_API")+"/toolcate/bankFourCheck";
        String response = OkHttpUtils.postForm(requestUrl,requestMap);
        Map<String,String> responseMap = JsonUtils.toCollection(response, new TypeReference<Map<String, String>>(){});

        // 添加流水记录
        CloudInterfaceStreamInfo cloudInterfaceStreamInfo = new CloudInterfaceStreamInfo();
        cloudInterfaceStreamInfo.setType((byte)1);
        cloudInterfaceStreamInfo.setRequestUrl(requestUrl);
        cloudInterfaceStreamInfo.setRequestContent(ToolUtils.mapToUrl(requestMap));
        cloudInterfaceStreamInfo.setResponseContent(response);
        cloudInterfaceStreamInfo.setTaskId(checkBanksRequest.getTaskId());
        cloudInterfaceStreamInfo.setStaffId(checkBanksRequest.getStaffId());
        cloudInterfaceStreamInfo.setStaffBanksId(checkBanksRequest.getStaffBanksId());
        cloudInterfaceStreamInfo.setAddtime(new Date());
        int streamRes = cloudInterfaceStreamServiceFacade.insRecord(cloudInterfaceStreamInfo);
        if ( streamRes > 0 ){
            logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的流水记录创建成功");
        }else{
            logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的流水记录创建失败");
            return 2;
        }

        if ( responseMap.get("code").equals("10000") ){
            // 鉴权成功
            CloudStaffBanksInfo cloudStaffBanksInfo = new CloudStaffBanksInfo();
            cloudStaffBanksInfo.setBankno(checkBanksRequest.getAccountNo());
            cloudStaffBanksInfo.setBankphone(checkBanksRequest.getMobile());
            cloudStaffBanksInfo.setBankActive("1");
            int staffBanksRes = cloudStaffBanksServiceFacade.updateColumn(cloudStaffBanksInfo);
            if ( staffBanksRes > 0 ){
                logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的鉴权成功，已更新银行卡为已鉴权");
            }
        }else {
            // 鉴权失败
            logger.info("员工id为 " + checkBanksRequest.getStaffId() + "，银行卡id为 " + checkBanksRequest.getStaffBanksId() + " 的鉴权失败");
            return 3;
        }

        return 4;
    }
}
