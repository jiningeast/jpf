package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.*;
import com.joiest.jpf.facade.*;
import com.joiest.jpf.market.api.util.SmsUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: admin
 * @Date: 2018/11/28 14:58
 * @Description:
 */
@Controller
@RequestMapping("/market-manager/shopBatchController")
public class ShopBatchController {
    private static final Logger logger = LogManager.getLogger(ShopBatchController.class);

    @Autowired
    private ShopCompanyChargeServiceFacade shopCompanyChargeServiceFacade;
    @Autowired
    private ShopCompanyServiceFacade shopCompanyServiceFacade;
    @Autowired
    private PayShopCouponOrderServiceFacade payShopCouponOrderServiceFacade;
    @Autowired
    private ShopBatchServiceFacade shopBatchServiceFacade;
    @Autowired
    private ShopCustomerServiceFacade shopCustomerServiceFacade;
    @Autowired
    private ShopBatchCouponServiceFacade shopBatchCouponServiceFacade;
    @Autowired
    private ShopInterfaceStreamServiceFacade shopInterfaceStreamServiceFacade;

    /**
     * 查询可用的合同号
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUsableBatchNo",method = RequestMethod.POST)
    @ResponseBody
    public String getUsableBatchNo(HttpServletRequest request){

        String companyId = request.getParameter("companyId");
        if(StringUtils.isBlank(companyId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            List<PayShopCompanyCharge> payShopCompanyCharges = shopCompanyChargeServiceFacade.getListByCompanyId(Base64CustomUtils.base64Decoder(companyId));
            setParam(list, payShopCompanyCharges);
        } catch (Exception e) {
            logger.error("getUsableBatchNo："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"获取失败",null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"查询成功",list);
    }

    private void setParam(List<Map<String, Object>> list, List<PayShopCompanyCharge> payShopCompanyCharges) {
        for (PayShopCompanyCharge payShopCompanyCharge : payShopCompanyCharges) {
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("id", payShopCompanyCharge.getId());
            map.put("contractNo", payShopCompanyCharge.getContractNo());
            map.put("balance", payShopCompanyCharge.getBalance());
            map.put("totalMoney", payShopCompanyCharge.getMoney());
            map.put("date", payShopCompanyCharge.getAddtime());
            list.add(map);
        }
    }

    /**
     * 查询可用的合同号
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUsableBatchNoPage",method = RequestMethod.POST)
    @ResponseBody
    public String getUsableBatchNoPage(HttpServletRequest request){

        String companyId = request.getParameter("companyId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if(StringUtils.isBlank(companyId)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        PayShopCompanyChargeInfo payShopCompanyChargeInfo = new PayShopCompanyChargeInfo();
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("companyId",Base64CustomUtils.base64Decoder(companyId));
            map.put("pageNo",Base64CustomUtils.base64Decoder(pageNo));
            map.put("pageSize",Base64CustomUtils.base64Decoder(pageSize));
            List<PayShopCompanyCharge> payShopCompanyCharges = shopCompanyChargeServiceFacade.getListByCompanyIdByPage(map);
            Integer total = shopCompanyChargeServiceFacade.getTotal(map);
            setParam(list, payShopCompanyCharges);
            payShopCompanyChargeInfo.setList(list);
            payShopCompanyChargeInfo.setTotal(total);
        } catch (Exception e) {
            logger.error("getUsableBatchNo："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"获取失败",null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"查询成功",payShopCompanyChargeInfo);
    }


    /**
     * 欣享爱生活下单接口
     * @return
     */
    @RequestMapping(value = "/payCouponOrder",method = RequestMethod.POST)
    @ResponseBody
    public String payCouponOrder(HttpServletRequest request){
        //返回的参数
        String couponOrder = request.getParameter("couponOrderInfo");
        if(StringUtils.isBlank(couponOrder)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"参数不能为空",null);
        }
        //转化成实体信息 判断参数是否合法
        CouponOrderList couponOrderList = JsonUtils.toObject(Base64CustomUtils.base64Decoder(couponOrder), CouponOrderList.class);
        if(StringUtils.isBlank(couponOrderList.getCompanyId())||StringUtils.isBlank(couponOrderList.getContractNo())
                ||StringUtils.isBlank(couponOrderList.getTotalMoney())||StringUtils.isBlank(couponOrderList.getTotalNum())
                ||(couponOrderList.getCouponList()==null||couponOrderList.getCouponList().size()==0)||StringUtils.isBlank(couponOrderList.getContractId())){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ABNORMAL_STATUS.getCode(),"参数不能为空",null);
        }

        //查询合同的余额够不够
        PayShopCompanyCharge payShopCompanyCharge = shopCompanyChargeServiceFacade.getById(couponOrderList.getContractId());
        if(payShopCompanyCharge==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),"商户合同不存在",null);
        }

        if(payShopCompanyCharge.getBalance().compareTo(new BigDecimal(couponOrderList.getTotalMoney()))<0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.CONTRACT_SUFFICIENT.getCode(),JpfInterfaceErrorInfo.CONTRACT_SUFFICIENT.getDesc(),null);
        }
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(couponOrderList.getCompanyId());
        if ( shopCompanyInfo.getStatus() != 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ABNORMAL_STATUS.getCode(),JpfInterfaceErrorInfo.ABNORMAL_STATUS.getDesc(),null);
        }

        if(shopCompanyInfo.getMoney().compareTo(new BigDecimal(couponOrderList.getTotalMoney()))<0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getCode(),JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getDesc(),null);
        }

        try {
            payShopCouponOrderServiceFacade.saveCouponOrder(couponOrderList);
        } catch (Exception e) {
            logger.error("payCouponOrder："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"下单成功",null);
    }

    /**
     * 获取订单的接口
     * @return
     */
    @RequestMapping(value = "/getOrderList",method = RequestMethod.POST)
    @ResponseBody
    public String getOrderList(HttpServletRequest request){
        String companyId = request.getParameter("companyId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String status = request.getParameter("status");
        Map<String,Object> map =new ConcurrentHashMap<>();
        if(StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
            map.put("startTime",Base64CustomUtils.base64Decoder(startTime));
            map.put("endTime",Base64CustomUtils.base64Decoder(endTime));
        }
        if(StringUtils.isNotBlank(status)){
            map.put("status",Base64CustomUtils.base64Decoder(status));
        }
        if(StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)&&StringUtils.isBlank(companyId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        map.put("companyId",Base64CustomUtils.base64Decoder(companyId));
        map.put("pageNo",Base64CustomUtils.base64Decoder(pageNo));
        map.put("pageSize",Base64CustomUtils.base64Decoder(pageSize));
        PayShopCouponOrderResultInfo payShopCouponOrderResultInfo = null;
        try {
            payShopCouponOrderResultInfo = payShopCouponOrderServiceFacade.getOrderList(map);
        } catch (Exception e) {
            logger.error("getOrderList："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),payShopCouponOrderResultInfo);
    }

    /**
     * 根据订单号查询订单详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOrderInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getOrderInfo(HttpServletRequest request){
        String orderNo = request.getParameter("orderNo");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if(StringUtils.isBlank(orderNo)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        Map<String,Object> map =new ConcurrentHashMap<>();
        List<PayShopCouponOrderInfo> payShopCouponOrderInfos = null;
        map.put("pageNo",Base64CustomUtils.base64Decoder(pageNo));
        map.put("pageSize",Base64CustomUtils.base64Decoder(pageSize));
        map.put("orderNo",Base64CustomUtils.base64Decoder(orderNo));
        PayShopCouponOrderInfoResultInfo payShopCouponOrderInfoResultInfo = null;
        try {
            payShopCouponOrderInfoResultInfo = payShopCouponOrderServiceFacade.getOrderInfo(map);
        } catch (Exception e) {
            logger.error("getOrderInfo："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),payShopCouponOrderInfoResultInfo);
    }

    /**
     * 取消订单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancalOrder",method = RequestMethod.POST)
    public String cancalOrder(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(orderId)) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(), JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(), null);
        }
        //查询订单。查看订单状态是不是0  0只是已经申请，但是还没有匹配券。可以做取消操作
        PayShopCouponOrder payShopCouponOrder = payShopCouponOrderServiceFacade.getByOrderId(Base64CustomUtils.base64Decoder(orderId));
        if (payShopCouponOrder != null && payShopCouponOrder.getStatus() != 0) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ORDER_STATUS_ERROR.getCode(), JpfInterfaceErrorInfo.ORDER_STATUS_ERROR.getDesc(), null);
        } else {
            try {
                payShopCouponOrderServiceFacade.cancalOrder(payShopCouponOrder);
            } catch (Exception e) {
                logger.error("cancalOrder："+e.getMessage());
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"取消失败", null);
            }
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"取消成功", null);
    }


    @RequestMapping(value = "/deleteOrder",method = RequestMethod.POST)
    @ResponseBody
    public String deleteOrder(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(orderId)) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(), JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(), null);
        }
        //查询订单。查看订单状态是不是2  2是已经取消的订单能够做删除操作
        PayShopCouponOrder payShopCouponOrder = payShopCouponOrderServiceFacade.getByOrderId(Base64CustomUtils.base64Decoder(orderId));
        if (payShopCouponOrder != null && payShopCouponOrder.getStatus() != 2) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ORDER_STATUS_CANCAL.getCode(), JpfInterfaceErrorInfo.ORDER_STATUS_CANCAL.getDesc(), null);
        } else {
            try {
                payShopCouponOrderServiceFacade.deleteOrder(payShopCouponOrder);
            } catch (Exception e) {
                logger.error("deleteOrder："+e.getMessage());
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"删除失败", null);
            }
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"删除成功", null);
    }

    /**
     * 查看所有批次所有的欣券
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getCouponInfo",method = RequestMethod.POST)
    public String getCouponInfo(HttpServletRequest request){
        //查询的订单号
        String sendType=request.getParameter("sendType");
        String isActive=request.getParameter("isActive");
        String orderNo = request.getParameter("orderNo");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        if(StringUtils.isBlank(orderNo)||StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        Map<String,Object> map =new ConcurrentHashMap<>();
        map.put("pageNo",Base64CustomUtils.base64Decoder(pageNo));
        map.put("pageSize",Base64CustomUtils.base64Decoder(pageSize));
        map.put("orderNo",Base64CustomUtils.base64Decoder(orderNo));
        if(StringUtils.isNotBlank(sendType)){
            map.put("sendType",Base64CustomUtils.base64Decoder(sendType));
        }
        if(StringUtils.isNotBlank(isActive)){
            map.put("isActive",Base64CustomUtils.base64Decoder(isActive));
        }
        PayShopBatchCouponResultInfo payShopBatchCouponResultInfo = null;
        try {
            payShopBatchCouponResultInfo = shopBatchCouponServiceFacade.getCouponsByOrderId(map);
        } catch (Exception e) {
            logger.error("getCouponInfo "+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"删除失败", null);
        }
         return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"查询成功", payShopBatchCouponResultInfo);
     }

    /**
     * 上传excel 解析是否完整
     */
    @RequestMapping(value = "/uploadExcel",method = RequestMethod.POST)
    @ResponseBody
    public String uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile,String companyId,String companyName,String orderId,String totalMoney) throws Exception{

        if(StringUtils.isBlank(companyId)||StringUtils.isBlank(companyName)||StringUtils.isBlank(orderId)||StringUtils.isBlank(totalMoney)){
            return setReslt("10008","数据参数不全",null);
        }
        companyId=Base64CustomUtils.base64Decoder(companyId);
        companyName=Base64CustomUtils.base64Decoder(companyName);
        orderId=Base64CustomUtils.base64Decoder(orderId);
        totalMoney=Base64CustomUtils.base64Decoder(totalMoney);
        // 获取当前的文件名
        String fileNameAll = uploadfile.getOriginalFilename();
        String fileName = fileNameAll.substring(0,fileNameAll.lastIndexOf("."));
        // 获取企业信息
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
        // 判断企业有没有停用
        if (shopCompanyInfo.getStatus()== 0){
            return setReslt("10001",JpfErrorInfo.COMPANY_NOT_AVAILABLE.desc(),null);
        }
        // 将excel暂存到硬盘上
        String savePre = ConfigUtil.getValue("EXCEL_PATH");
        String fileFinalName="XQ"+System.currentTimeMillis()+ToolUtils.getRandomInt(100000,999999);
        String path = PhotoUtil.saveFile2(uploadfile, savePre,fileFinalName);

        // 解析excel
        ExcelDealUtils excelDealUtils = new ExcelDealUtils();
        Map<Object,Object> map = excelDealUtils.getImportExcel(uploadfile.getInputStream(), uploadfile.getOriginalFilename());
        List<Object> list = new ArrayList<>(map.values());
        int maxAmount = Integer.parseInt(ConfigUtil.getValue("MAX_SEND_AMOUNT"));

        // 最多数量限制
        if ( list.size() > maxAmount ){
            return setReslt("10002",JpfErrorInfo.MAX_LIMIT.desc(),null);
        }
        if(StringUtils.isBlank(orderId)){
            return setReslt("10003",JpfErrorInfo.EMPTY_BATCH_NO.desc(),null);
        }
        //根据订单号查询批次
        PayShopBatch payShopBatch = shopBatchServiceFacade.getBatchByOrderId(orderId);
        if (payShopBatch == null ){
           return setReslt("10004",JpfErrorInfo.BATCH_NOT_EXIST.desc(),null);
        }
        // 获取总面值,判断其不为空
        if (!StringUtils.isNotBlank(totalMoney) ){
            return setReslt("10005",JpfErrorInfo.EMPTY_TOTAL_MONEY.desc(),null);
        }
        // 验证该企业有没有excel上描述的券
        // 获取excel各面值券的数量
        List<ShopCustomerInfo> personsList = new ArrayList<>();    // 新建校验人数组
        Map<String,Integer> valueCount = new LinkedHashMap<>();
        BigDecimal totalValue =new BigDecimal(0) ;
        for ( int i=2; i<list.size(); i++){
            // 获取每条信息详情
            Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
            // 判断手机号合法性
            if (!ToolUtils.checkPhone(singlePerson.get(1))){
                return setReslt("10006","用户"+singlePerson.get(0)+"的手机号有误，请检查后重新上传",null);
            }
            // 姓名、手机号和面值必填
            String name = singlePerson.get(0);
            String phone = singlePerson.get(1);
            String value = singlePerson.get(2);
            String idno = StringUtils.isNotBlank(singlePerson.get(3)) ? singlePerson.get(3) : null;
            if (!StringUtils.isNotBlank(name) ||!StringUtils.isNotBlank(phone)||!StringUtils.isNotBlank(value) ){
                return setReslt("10007","Excel第"+i+"行的数据不完整，请修改后重新上传",null);
            }
            // 判断所有人的状态是不是已冻结
            PayShopCustomer existCustomer = shopCustomerServiceFacade.getCustomerByPhone(singlePerson.get(1));
            ShopCustomerInfo failCustomer = new ShopCustomerInfo();
            failCustomer.setName(singlePerson.get(0));
            failCustomer.setPhone(singlePerson.get(1));
            failCustomer.setDou(new BigDecimal(value));
            if ( StringUtils.isNotBlank(idno) ){
                failCustomer.setIdno(idno);
            }
            if(existCustomer!=null){
                if (existCustomer.getStatus()== 0){
                    failCustomer.setStatus((byte)0); // 账户已冻结
                }
                // 检查校验码
                String checkCode = ToolUtils.CreateCode(existCustomer.getDou().toString(),existCustomer.getId());
                if ( !existCustomer.getCode().equals(checkCode) ){
                    failCustomer.setIsVerify((byte)0); // 校验码错误
                }
            }
            // 将校验过的人添加到数组中
            personsList.add(failCustomer);
            // 统计总金额
            totalValue =ArithmeticUtils.add(totalValue.toString(),value);
            // 统计各面值的数量
            if ( valueCount.get(value) == null ){
                valueCount.put( value,1);
            }else{
                valueCount.put( value,valueCount.get(value) + 1);
            }
        }
        // 判断总面值和各人员面值的总和是不是统一
        if (new BigDecimal(totalMoney).compareTo(totalValue)!=0){
            return setReslt("10008",JpfErrorInfo.ERROR_TOTAL_MONEY.desc(),null);
        }
        // 判断数据库中有没有对应的这些个券
        String couponDesc="商家发送请求共"+personsList.size()+"条记录;-";
        for (Map.Entry<String, Integer> entry : valueCount.entrySet()) {
            int valueNum = shopBatchCouponServiceFacade.getCouponNumByValue(companyId,entry.getKey(), payShopBatch.getBatchNo());
            couponDesc+="面值"+entry.getKey()+","+valueNum+"笔-";
            if ( valueNum < entry.getValue() ){
                return setReslt("10009","面值"+entry.getKey()+"的库存数量少于Excel中的数量",null);
            }
        }
        // 输出校验的人集合
        if (!personsList.isEmpty() ){
            UUID uuid = UUID.randomUUID();
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code","100000");
            responseMap.put("info","人员校验情况如下：");
            responseMap.put("companyId",companyId);
            responseMap.put("companyName",companyName);
            responseMap.put("path",path);
            responseMap.put("orderId",orderId);
            responseMap.put("data",personsList);
            responseMap.put("totalMoney",totalMoney);
            responseMap.put("couponDesc",couponDesc);
            responseMap.put("excleTitle",fileNameAll);
            responseMap.put("excleName","XQ"+uuid.toString());
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH_XQ")+"XQ"+uuid.toString()+".txt",false);
            return ToolUtils.mapToJsonBase64(responseMap);
        }
        return setReslt("10010","上传数据为空",null);
    }

    private String setReslt(String s, String desc,Object jsonData) {
        return  ToolUtils.toJsonBase64(s,desc,jsonData);
    }

    /**
     * 下载excel模板
     */
    @RequestMapping("/downloadFile")
    @ResponseBody
    public HttpServletResponse download(HttpServletRequest request, HttpServletResponse response){
        //下载文件路径
        OutputStream toClient=null;
        InputStream fis = null;
        try{
            String path = ConfigUtil.getValue("EXCEL_PATH") + "/module/";
            String filename = "前台网站欣券群发模板.xlsx";
            // path是指欲下载的文件的路径。
            File file = new File(path+filename);
            // 以流的形式下载文件。
            fis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String fileName = new String(filename.getBytes(),"ISO-8859-1");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(toClient!=null){
                    toClient.close();
                }
                if(fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    /**
     * 群发券操作
     */
    @RequestMapping("/sendCoupons")
    @ResponseBody
    public String sendCoupons(String fileName){
        if(StringUtils.isBlank(fileName)){
            return setReslt("80010","数据参数不全",null);
        }
        fileName=Base64CustomUtils.base64Decoder(fileName);
        // 读取暂存文件
        String fileContent = ToolUtils.readFromFile(ConfigUtil.getValue("CACHE_PATH_XQ")+fileName+".txt","UTF-8");
        Map<String,Object> jsonMap = JsonUtils.toObject(fileContent,HashMap.class);
        List<LinkedHashMap<String,Object>> list = (List<LinkedHashMap<String, Object>>) jsonMap.get("data");
        String companyId = (String) jsonMap.get("companyId");
        String companyName = (String) jsonMap.get("companyName");
        String orderId = (String) jsonMap.get("orderId");
        String excelLocalUrl = (String) jsonMap.get("path");
        String totalMoney = (String) jsonMap.get("totalMoney");
        // 检查商户是否已禁用
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
        if(shopCompanyInfo==null){
            return setReslt("10001","该商户系统中不存在",null);
        }
        if (shopCompanyInfo.getStatus() == 0){
            return setReslt("10001","该商户已禁用",null);
        }
        // 检验金额验证码的正确性
        if (!shopCompanyServiceFacade.checkMoneyCode(companyId)){
            return setReslt("10002","金额校验码错误",null);
        }
        //根据订单号查询批次
        PayShopBatch payShopBatch = shopBatchServiceFacade.getBatchByOrderId(orderId);
        if(payShopBatch==null){
            return setReslt("10002","当前订单对应的批次不存在",null);
        }
        List<ShopBatchCouponInfo> couponsList  = shopBatchCouponServiceFacade.getCouponsWeb(list,companyId,payShopBatch.getId(),excelLocalUrl);
        // 扣款
        shopCompanyServiceFacade.chargeSub(companyId,totalMoney);
        // 开始发短信
        String content;
        for ( ShopBatchCouponInfo shopBatchCouponInfo:couponsList ){
            content = "您收到一个激活码："+shopBatchCouponInfo.getActiveCode()+"，请微信搜索“欣享爱生活”公众号登录使用。";
            //sendToPersonsSms(shopBatchCouponInfo.getActivePhone(),content,payShopBatch.getBatchNo());
        }
        return setReslt("10000","发放成功",null);
    }

    // 群发的短信
    public void sendToPersonsSms(String phone,String content,String batchNo){
        Map<String,String> smsResMap = SmsUtils.send(phone,content,"xinxiang");
        Map<String,String> responseMap = JsonUtils.toObject(smsResMap.get("response"),Map.class);
        if ( responseMap.get("code").equals("10000") ){
            // 添加短信流水
            ShopInterfaceStreamInfo shopInterfaceStreamInfo = new ShopInterfaceStreamInfo();
            shopInterfaceStreamInfo.setType((byte)1);
            shopInterfaceStreamInfo.setRequestUrl(smsResMap.get("requestUrl"));
            shopInterfaceStreamInfo.setRequestContent(smsResMap.get("requestParam"));
            shopInterfaceStreamInfo.setResponseContent(smsResMap.get("response"));
            shopInterfaceStreamInfo.setBatchNo(batchNo);
            shopInterfaceStreamInfo.setAddtime(new Date());
            shopInterfaceStreamServiceFacade.addStream(shopInterfaceStreamInfo);
        }
    }



    public static void main(String[] args) {
        System.out.println(Md5Encrypt.md5("merchNo=MC1541126548324168863&money=200.00imyHcZOzMmhukCqB").toUpperCase());
        Map<String,Object> map = new HashMap<>();
        map.put("merchNo","MC1541126548324168863");
        map.put("money","20.00");

        //排序转换
        Map<String,Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        String respos = ToolUtils.mapToUrl(treeMap);
        System.out.println(Md5Encrypt.md5(respos+"imyHcZOzMmhukCqB").toUpperCase());

        BigDecimal bigDecimal = new BigDecimal(200);
        System.out.println(new BigDecimal(200).compareTo(bigDecimal));
        System.out.println(Base64CustomUtils.base64Encoder("测试天天向上"));
        System.out.println(Base64CustomUtils.base64Decoder(Base64CustomUtils.base64Encoder("测试天天向上")));
    }


}
