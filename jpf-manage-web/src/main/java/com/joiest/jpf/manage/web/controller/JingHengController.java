package com.joiest.jpf.manage.web.controller;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dto.ModifyBrangainRechargeorderRequest;
import com.joiest.jpf.entity.ModifyBrangainRechargeorderInfo;
import com.joiest.jpf.entity.ShopBargainRechargeViewInfo;
import com.joiest.jpf.facade.ShopBrangainRechargeOrderServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@ResponseBody
@RequestMapping(value = "/jingheng")
public class JingHengController {

    private static final Logger logger = LogManager.getLogger(JingHengController.class);
//    private String privateKey = "c3c8501179fb10863aaed22147274ab12f06d8155140a968fb60dba540a5483f";
    private String privateKey = "fc5c14cf2423e8875036b57e3bce26d73c13043e60cdeb28c70cf75b8927b8d5";

    private String result = "{\"status\":\"success\",\"errorMsg\":null,\"errorCode\":null,\"module\":[{\"id\":218833,\"gmtCreate\":1537439850000,\"gmtModify\":1537439947000,\"status\":2,\"payOrderId\":248509,\"userId\":254,\"amount\":1000,\"amt\":1,\"bizId\":201,\"itemId\":20002,\"itemName\":\"移动话费-测试商品1元\",\"itemPrice\":1000,\"itemFacePrice\":1000,\"itemCategoryId\":null,\"itemUid\":\"13829751360\",\"itemExt1\":null,\"itemExt2\":null,\"itemExt3\":null,\"itemExt4\":null,\"itemExt5\":null,\"itemSupplyId\":null,\"stockId\":null,\"channel\":1,\"upstreamMemo\":\" \",\"downstreamId\":null,\"downstreamName\":null,\"downstreamDate\":1537439850000,\"downstreamSerialno\":\"9283149\",\"downstreamNotes\":null,\"memo\":null,\"lockOperId\":null,\"dealOperId\":null,\"uidAreaInfo\":\"广东广州\",\"lockOperName\":null,\"dealOperName\":null,\"costTime\":97,\"supplyType\":null,\"downstreamSupplyWay\":1,\"itemCostPrice\":1000,\"carrierType\":3,\"provinceCode\":\"440000\",\"cityCode\":\"440100\",\"actualCost\":null,\"combineType\":null,\"supplyCount\":1,\"supplyFilterIndex\":0,\"receivedOrderId\":null,\"forwardOrderId\":null,\"actualCardPrice\":null,\"extendsInfo\":\"{\\\"batchId\\\":\\\"\\\",\\\"beforeSubCode\\\":0,\\\"cardList\\\":[],\\\"cardTakeStock\\\":false,\\\"construcId\\\":\\\"\\\",\\\"forwardId\\\":0,\\\"itemSupplyList\\\":[3602],\\\"jianquan\\\":false,\\\"orderBeginTime\\\":\\\"\\\",\\\"originItemId\\\":0,\\\"originItemName\\\":\\\"\\\",\\\"photoUrl\\\":\\\"\\\",\\\"voucher\\\":\\\"\\\",\\\"voucherType\\\":\\\"\\\"}\",\"userName\":null,\"interceptType\":null,\"interceptId\":null,\"deputy\":false,\"forwardType\":null,\"salesArea\":\"111111\",\"itemGroupId\":null,\"subCode\":0,\"outorderNo\":null,\"callStatus\":2},{\"id\":218832,\"gmtCreate\":1537414849000,\"gmtModify\":1537414942000,\"status\":2,\"payOrderId\":248508,\"userId\":254,\"amount\":1000,\"amt\":1,\"bizId\":201,\"itemId\":20002,\"itemName\":\"移动话费-测试商品1元\",\"itemPrice\":1000,\"itemFacePrice\":1000,\"itemCategoryId\":null,\"itemUid\":\"13829751360\",\"itemExt1\":null,\"itemExt2\":null,\"itemExt3\":null,\"itemExt4\":null,\"itemExt5\":null,\"itemSupplyId\":null,\"stockId\":null,\"channel\":1,\"upstreamMemo\":\" \",\"downstreamId\":null,\"downstreamName\":null,\"downstreamDate\":1537414849000,\"downstreamSerialno\":\"9279949\",\"downstreamNotes\":null,\"memo\":null,\"lockOperId\":null,\"dealOperId\":null,\"uidAreaInfo\":\"广东广州\",\"lockOperName\":null,\"dealOperName\":null,\"costTime\":93,\"supplyType\":null,\"downstreamSupplyWay\":1,\"itemCostPrice\":1000,\"carrierType\":3,\"provinceCode\":\"440000\",\"cityCode\":\"440100\",\"actualCost\":null,\"combineType\":null,\"supplyCount\":1,\"supplyFilterIndex\":0,\"receivedOrderId\":null,\"forwardOrderId\":null,\"actualCardPrice\":null,\"extendsInfo\":\"{\\\"batchId\\\":\\\"\\\",\\\"beforeSubCode\\\":0,\\\"cardList\\\":[],\\\"cardTakeStock\\\":false,\\\"construcId\\\":\\\"\\\",\\\"forwardId\\\":0,\\\"itemSupplyList\\\":[3602],\\\"jianquan\\\":false,\\\"orderBeginTime\\\":\\\"\\\",\\\"originItemId\\\":0,\\\"originItemName\\\":\\\"\\\",\\\"photoUrl\\\":\\\"\\\",\\\"voucher\\\":\\\"\\\",\\\"voucherType\\\":\\\"\\\"}\",\"userName\":null,\"interceptType\":null,\"interceptId\":null,\"deputy\":false,\"forwardType\":null,\"salesArea\":\"111111\",\"itemGroupId\":null,\"subCode\":0,\"outorderNo\":null,\"callStatus\":2},{\"id\":218831,\"gmtCreate\":1537414393000,\"gmtModify\":1537414393000,\"status\":3,\"payOrderId\":248507,\"userId\":254,\"amount\":1000,\"amt\":1,\"bizId\":201,\"itemId\":20002,\"itemName\":\"移动话费-测试商品1元\",\"itemPrice\":1000,\"itemFacePrice\":1000,\"itemCategoryId\":null,\"itemUid\":\"13829751360\",\"itemExt1\":null,\"itemExt2\":null,\"itemExt3\":null,\"itemExt4\":null,\"itemExt5\":null,\"itemSupplyId\":null,\"stockId\":null,\"channel\":1,\"upstreamMemo\":\"30_触发成本控制(3602:)\",\"downstreamId\":null,\"downstreamName\":null,\"downstreamDate\":1537414393000,\"downstreamSerialno\":\"9279771\",\"downstreamNotes\":null,\"memo\":null,\"lockOperId\":null,\"dealOperId\":null,\"uidAreaInfo\":\"广东广州\",\"lockOperName\":null,\"dealOperName\":null,\"costTime\":null,\"supplyType\":null,\"downstreamSupplyWay\":1,\"itemCostPrice\":null,\"carrierType\":3,\"provinceCode\":\"440000\",\"cityCode\":\"440100\",\"actualCost\":null,\"combineType\":null,\"supplyCount\":null,\"supplyFilterIndex\":null,\"receivedOrderId\":null,\"forwardOrderId\":null,\"actualCardPrice\":null,\"extendsInfo\":\"{\\\"batchId\\\":\\\"\\\",\\\"beforeSubCode\\\":0,\\\"cardList\\\":[],\\\"cardTakeStock\\\":false,\\\"construcId\\\":\\\"\\\",\\\"forwardId\\\":0,\\\"itemSupplyList\\\":[],\\\"jianquan\\\":false,\\\"orderBeginTime\\\":\\\"\\\",\\\"originItemId\\\":0,\\\"originItemName\\\":\\\"\\\",\\\"photoUrl\\\":\\\"\\\",\\\"voucher\\\":\\\"\\\",\\\"voucherType\\\":\\\"\\\"}\",\"userName\":null,\"interceptType\":null,\"interceptId\":null,\"deputy\":false,\"forwardType\":null,\"salesArea\":\"111111\",\"itemGroupId\":null,\"subCode\":0,\"outorderNo\":null,\"callStatus\":0}],\"totalItem\":3,\"tmallUser\":false}";

    @Autowired
    private ShopBrangainRechargeOrderServiceFacade shopBrangainRechargeOrderServiceFacade;

    private String JH_REQUEST_URL = "http://47.99.130.76:8165/api/queryBizOrder.do";

    @RequestMapping(value = "/queryBizOrder")
    @ResponseBody
    public String queryBizOrder()
    {
        String requestUrl = JH_REQUEST_URL;
        //时间查询
        DateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        Date yesterday = calendar.getTime();
        String defaultStartDate = dateFmt.format(yesterday);
        String start = defaultStartDate.substring(0,10) + " 00:00:00";
        String end = defaultStartDate.substring(0,10) + " 23:23:59";

        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("startGmtCreate", start);
        requestParam.put("endGmtCreate", end);
        requestParam.put("pageSize", "500");
        int currentPage = 1;
        int currentPageDataSize = 1;
        while ( currentPageDataSize > 0 ){
            if ( requestParam.containsKey("sign") ){
                requestParam.remove("sign");
            }
            requestParam.put("currentPage", currentPage);
            TreeMap<String, Object> treeMap = new TreeMap<>();
            treeMap.putAll(requestParam);
            String signStr = "";
            for (Object v : treeMap.values()) {
                signStr += String.valueOf(v);
            }
            signStr += privateKey;

            StringBuilder sbf2 = new StringBuilder();
            Date date2 = new Date();
            SimpleDateFormat myfmt2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sbf2.append("\n\nTime:" + myfmt2.format(date2));
            sbf2.append("\n当前页：" + currentPage);
            sbf2.append("\ntreeMap：" + treeMap);
            sbf2.append("\n签名串：" + signStr);
            String fileName2 = "queryBizOrderSign";
            String path2 = "/logs/jpf-manage-web/log/";
            LogsCustomUtils.writeIntoFile(sbf2.toString(),path2, fileName2, true);

            String sign = Md5Encrypt.md5(signStr);
            requestParam.put("sign", sign);
            treeMap.put("sign", sign);
            String tmpStr = "";
            for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                tmpStr += entry.getKey() + "=" + entry.getValue() + "&";
            }
            String paramStr = StringUtils.stripEnd(tmpStr, "&");
            String url = requestUrl + "?" +paramStr;
            logger.info("-------------- 敬恒 拉取数据第 "+requestParam.get("currentPage")+" 页 ，start --------------");
            String result = OkHttpUtils.get(url);     //todo 打开注释
            logger.info("-------------- 敬恒 拉取数据第 "+requestParam.get("currentPage")+" 页，end --------------");
            JSONObject resultJosn = JSONObject.fromObject(result);
            JSONArray module;
            int valid_count = 0;
            if ( resultJosn.containsKey("module") )
            {
                module = JSONArray.fromObject(resultJosn.get("module"));
                logger.info("拉到数据，共计：{}条", module.size());
                currentPageDataSize = module.size();
                if( module.size() != 0 ){
                    int size = module.size();
                    for (int i=0; i<size; i++){
                        JSONObject one = module.getJSONObject(i);
                        logger.info("status: {}", one.get("status"));
                        logger.info("status--type: {}", one.get("status").getClass());
                        logger.info("equqls--result: {}", one.get("status").toString().equals("2") );
                        //过滤充值失败的订单
                        if ( one.containsKey("status") && !one.get("status").toString().equals("2") )
                        {
                            continue;
                        }
                        valid_count++;
                        ModifyBrangainRechargeorderRequest request = new ModifyBrangainRechargeorderRequest();

                        //订单编号
                        if ( one.containsKey("id") && one.get("id") != null )
                        {
                            request.setForeignOrderNo(one.get("id").toString());
                        }
                        //添加时间
                        if ( one.containsKey("gmtCreate") && one.get("gmtCreate") != null )
                        {
                            Date addtime = DateUtils.stampToDateRe( one.get("gmtCreate").toString());
                            request.setAddtime(addtime);
                        }
                        //订单修改时间
                        if ( one.containsKey("gmtModify") && one.get("gmtModify") != null )
                        {
                            Date recharge_time = DateUtils.stampToDateRe( one.get("gmtModify").toString());
                            request.setRechargeTime(recharge_time);
                        }
                        //订单状态
                        request.setStatus(1);
                        //充值状态
                        if ( one.containsKey("status") && one.get("status") != null )
                        {
                            request.setRechargeStatus(one.get("status").toString());
                        }
                        //充值号码
                        if ( one.containsKey("itemUid") && one.get("itemUid") != null )
                        {
                            request.setChargeNo(one.get("itemUid").toString());
                        }
                        //商品名称
                        if ( one.containsKey("itemName") && one.get("itemName") != null )
                        {
                            request.setItemName(one.get("itemName").toString());
                        }
                        //商品单价
                        if ( one.containsKey("itemPrice") && one.get("itemPrice") != null )
                        {
                            Double itemPrice = Double.valueOf(one.get("itemPrice").toString());
                            Double priceD = itemPrice/1000;
                            BigDecimal price = new BigDecimal(priceD.toString());
                            request.setPrice(price);
                        }
                        //商品面额
                        if ( one.containsKey("itemFacePrice") && one.get("itemFacePrice") != null )
                        {
                            Double itemFacePrice = Double.valueOf(one.get("itemFacePrice").toString());
                            Double facePrice = itemFacePrice/1000;
                            BigDecimal price = new BigDecimal(facePrice.toString());
                            request.setFacePrice(price);
                        }
                        //商品面额
                        if ( one.containsKey("amount") && one.get("amount") != null )
                        {
                            Double itemFacePrice = Double.valueOf(one.get("amount").toString());
                            Double amount = itemFacePrice/1000;
                            BigDecimal price = new BigDecimal(amount.toString());
                            request.setAmount(price);
                        }

                        request.setAmt(1);
                        request.setOrderType(3);
                        request.setInfoStatus(1);
                        request.setModule(one.toString());
                        JpfResponseDto res = shopBrangainRechargeOrderServiceFacade.insertInfo(request);
                        System.out.println(res.toString());
                    }
                }
                currentPage++;
            }
            logger.info("数据入库成功，有效数据共：{}条", valid_count);
            StringBuilder sbf = new StringBuilder();
            Date date = new Date();
            SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sbf.append("\n\nTime:" + myfmt.format(date));
            sbf.append("\n请求地址：" + url);
            sbf.append("\n接口参数：" + requestParam);
            sbf.append("\n回调信息：\n" + result);
            String fileName = "queryBizOrder-1";
            String path = "/logs/jpf-manage-web/log/";

            LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);
        }

        return "执行完成--拉取数据";
    }

    @RequestMapping(value = "/dobingdata", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doBingData(){

        List<ShopBargainRechargeViewInfo> list_view = shopBrangainRechargeOrderServiceFacade.getOrderView();
        if ( list_view == null || list_view.isEmpty() )
        {
            return "没有订单可执行";
        }
        List<ModifyBrangainRechargeorderInfo> list_order = shopBrangainRechargeOrderServiceFacade.getDataList();
        if ( list_order == null || list_order.isEmpty() )
        {
            return "没有数据可执行";
        }
        System.out.println("开始执行");
        int res = shopBrangainRechargeOrderServiceFacade.doBingOrderData(list_view, list_order);

        return "执行完成---bindData";
    }


}
