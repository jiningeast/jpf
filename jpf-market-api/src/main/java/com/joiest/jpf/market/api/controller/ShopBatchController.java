package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.*;
import com.joiest.jpf.entity.CouponOrderList;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.entity.ShopCustomerInfo;
import com.joiest.jpf.facade.*;
import org.apache.commons.collections.map.HashedMap;
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

    /**
     * 查询可用的订单号
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
            for (PayShopCompanyCharge payShopCompanyCharge:payShopCompanyCharges) {
                Map<String,Object> map =new ConcurrentHashMap<>();
                map.put("id",payShopCompanyCharge.getId());
                map.put("contractNo",payShopCompanyCharge.getContractNo());
                map.put("balance",payShopCompanyCharge.getBalance());
                map.put("rate",payShopCompanyCharge.getRate());
                list.add(map);
            }
        } catch (Exception e) {
            logger.error("getUsableBatchNo："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),"获取失败",null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),"查询成功",list);
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
        Map<String,Object> map =new ConcurrentHashMap<>();
        if(StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
            map.put("startTime",Base64CustomUtils.base64Decoder(startTime));
            map.put("endTime",Base64CustomUtils.base64Decoder(endTime));
        }
        if(StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)&&StringUtils.isBlank(companyId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        map.put("companyId",Base64CustomUtils.base64Decoder(companyId));
        map.put("pageNo",Base64CustomUtils.base64Decoder(pageNo));
        map.put("pageSize",Base64CustomUtils.base64Decoder(pageSize));
        List<PayShopCouponOrder> orderList;
        try {
            orderList = payShopCouponOrderServiceFacade.getOrderList(map);
        } catch (Exception e) {
            logger.error("getOrderList："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),orderList);
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
        if(StringUtils.isBlank(orderNo)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }

        List<PayShopCouponOrderInfo> payShopCouponOrderInfos = null;
        try {
            payShopCouponOrderInfos = payShopCouponOrderServiceFacade.getOrderInfo(Base64CustomUtils.base64Decoder(orderNo));
        } catch (Exception e) {
            logger.error("getOrderInfo："+e.getMessage());
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),payShopCouponOrderInfos);
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
     * 上传excel 解析是否完整
     */
    @RequestMapping("/uploadExcel")
    @ResponseBody
    public JpfResponseDto uploadExcel(@RequestParam("uploadfile") MultipartFile uploadfile,String companyId) throws Exception{
        // 获取当前的文件名
        String fileNameAll = uploadfile.getOriginalFilename();
        String fileName = fileNameAll.substring(0,fileNameAll.lastIndexOf("."));
        // 获取企业信息
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
        // 判断企业有没有停用
        if (shopCompanyInfo.getStatus()== 0){
            return setReslt("10001",JpfErrorInfo.COMPANY_NOT_AVAILABLE.desc());
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
            return setReslt("10001",JpfErrorInfo.MAX_LIMIT.desc());
        }
        Map<Integer,String> excelInfo = (Map<Integer,String>)list.get(2);
        // 获取公司名称，判断其不为空
        String companyName = excelInfo.get(0);
        if ( !StringUtils.isNotBlank(companyName) ){
            return setReslt("10006",JpfErrorInfo.EMPTY_COMPANY_NAME.desc());
        }
        // 判断选择的批次的企业和excel中的企业名称是否一致
        PayShopCompany payShopCompany = shopCompanyServiceFacade.getCompanyByName(companyName);
        if ( !payShopCompany.getId().equals(companyId) ){
            return setReslt("10006",JpfErrorInfo.ERROR_COMPANY_NAME.desc());
        }
        // 获取总面值,判断其不为空
        String totalMoney = excelInfo.get(1);
        if (!StringUtils.isNotBlank(totalMoney) ){
            return setReslt("10001",JpfErrorInfo.EMPTY_TOTAL_MONEY.desc());
        }
        // 验证该企业有没有excel上描述的券
        // 获取excel各面值券的数量
        List<ShopCustomerInfo> personsList = new ArrayList<>();    // 新建校验人数组
        Map<String,Integer> valueCount = new HashedMap();
        int totalValue = 0;
        for ( int i=4; i<list.size(); i++){
            // 获取每条信息详情
            Map<Integer,String> singlePerson = (Map<Integer,String>)list.get(i);
            // 判断手机号合法性
            if ( !ToolUtils.checkPhone( singlePerson.get(1) ) ){
                return setReslt("10005","用户"+singlePerson.get(0)+"的手机号有误，请检查后重新上传");
            }
            // 姓名、手机号和面值必填
            String name = singlePerson.get(0);
            String phone = singlePerson.get(1);
            String value = singlePerson.get(2);
            String idno = StringUtils.isNotBlank(singlePerson.get(3)) ? singlePerson.get(3) : null;
            if ( !StringUtils.isNotBlank(name) || !StringUtils.isNotBlank(phone) || !StringUtils.isNotBlank(value) ){
                return setReslt("10004","Excel第"+i+"行的数据不完整，请修改后重新上传");
            }
            // 判断所有人的状态是不是已冻结
            PayShopCustomer existCustomer = shopCustomerServiceFacade.getCustomerByPhone(singlePerson.get(1));
            ShopCustomerInfo failCustomer = new ShopCustomerInfo();
            failCustomer.setName(singlePerson.get(0));
            failCustomer.setPhone(singlePerson.get(1));
            failCustomer.setDou(Integer.parseInt(singlePerson.get(2)));
            if ( StringUtils.isNotBlank(idno) ){
                failCustomer.setIdno(idno);
            }
            if ( existCustomer.getStatus() == 0 ){
                // 账户已冻结
                failCustomer.setStatus((byte)0);
            }
            // 检查校验码
            String checkCode = ToolUtils.CreateCode(existCustomer.getDou().toString(),existCustomer.getId());
            if ( !existCustomer.getCode().equals(checkCode) ){
                // 校验码错误
                failCustomer.setIsVerify((byte)0);
            }
            // 将校验过的人添加到数组中
            if ( failCustomer.getName() != null ){
                personsList.add(failCustomer);
            }
            // 统计总金额
            totalValue = totalValue + Integer.parseInt(value);

            // 统计各面值的数量
            if ( valueCount.get(value) == null ){
                valueCount.put( value,1);
            }else{
                valueCount.put( value,valueCount.get(value) + 1);
            }
        }

        // 判断总面值和各人员面值的总和是不是统一
        if (Integer.parseInt(totalMoney) != totalValue){
            return setReslt("10001",JpfErrorInfo.ERROR_TOTAL_MONEY.desc());
        }

        // 判断数据库中有没有对应的这些个券
        for (Map.Entry<String, Integer> entry : valueCount.entrySet()) {
            int valueNum = shopBatchCouponServiceFacade.getCouponNumByValue(companyId,entry.getKey(), null );
            if ( valueNum < entry.getValue() ){
                return setReslt("10002","面值"+entry.getKey()+"的库存数量少于Excel中的数量");
            }
        }

        // 输出校验的人集合
        if ( !personsList.isEmpty() ){
            UUID uuid = UUID.randomUUID();
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("code","00001");
            responseMap.put("info","人员校验情况如下：");
            responseMap.put("companyId",companyId);
            responseMap.put("companyName",companyName);
            responseMap.put("path",path);
            responseMap.put("data",JsonUtils.toJson(personsList));
            responseMap.put("totalMoney",totalMoney);
            LogsCustomUtils2.writeIntoFile(JsonUtils.toJson(responseMap),ConfigUtil.getValue("CACHE_PATH")+uuid.toString()+".txt",false);

            JpfResponseDto jpfResponseDto = new JpfResponseDto();
            jpfResponseDto.setRetCode("00001");
            return setReslt("00001",uuid.toString());
        }
        return new JpfResponseDto();
    }

    private JpfResponseDto setReslt(String s, String desc) {
        JpfResponseDto jpfResponseDto = new JpfResponseDto();
        jpfResponseDto.setRetCode(s);
        jpfResponseDto.setRetMsg(desc);
        return  jpfResponseDto;
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
    }
}
