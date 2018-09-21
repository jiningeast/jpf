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

    private String result = "{\n" +
            "\t\"status\": \"success\",\n" +
            "\t\"errorMsg\": null,\n" +
            "\t\"errorCode\": null,\n" +
            "\t\"module\": [{\n" +
            "\t\t\"id\": 271200,\n" +
            "\t\t\"gmtCreate\": 1528529574000,\n" +
            "\t\t\"gmtModify\": 1528529605000,\n" +
            "\t\t\"status\": 3,\n" +
            "\t\t\"payOrderId\": 311341,\n" +
            "\t\t\"userId\": 188,\n" +
            "\t\t\"amount\": 99000,\n" +
            "\t\t\"amt\": 1,\n" +
            "\t\t\"bizId\": 600,\n" +
            "\t\t\"itemId\": 14894,\n" +
            "\t\t\"itemName\": \"中石化100元收卡\",\n" +
            "\t\t\"itemPrice\": 99000,\n" +
            "\t\t\"itemFacePrice\": 100000,\n" +
            "\t\t\"itemCategoryId\": null,\n" +
            "\t\t\"itemUid\": \"1000113300015769029\",     \n" +
            "\t\t\"itemExt1\": null,\n" +
            "\t\t\"itemExt2\": null,\n" +
            "\t\t\"itemExt3\": null,\n" +
            "\t\t\"itemExt4\": null,\n" +
            "\t\t\"itemExt5\": null,\n" +
            "\t\t\"itemSupplyId\": null,\n" +
            "\t\t\"stockId\": null,\n" +
            "\t\t\"channel\": 2,\n" +
            "\t\t\"upstreamMemo\": \"30_出库失败\",\n" +
            "\t\t\"downstreamId\": null,\n" +
            "\t\t\"downstreamName\": null,\n" +
            "\t\t\"downstreamDate\": 1528529574000,\n" +
            "\t\t\"downstreamSerialno\": \"1005352019386433536\",\n" +
            "\t\t\"downstreamNotes\": null,\n" +
            "\t\t\"memo\": null,\n" +
            "\t\t\"lockOperId\": null,\n" +
            "\t\t\"dealOperId\": null,\n" +
            "\t\t\"uidAreaInfo\": \"浙江\",\n" +
            "\t\t\"lockOperName\": null,\n" +
            "\t\t\"dealOperName\": null,\n" +
            "\t\t\"costTime\": null,\n" +
            "\t\t\"supplyType\": null,\n" +
            "\t\t\"downstreamSupplyWay\": 1,\n" +
            "\t\t\"itemCostPrice\": 9800,\n" +
            "\t\t\"carrierType\": null,\n" +
            "\t\t\"provinceCode\": \"330000\",\n" +
            "\t\t\"cityCode\": null,\n" +
            "\t\t\"actualCost\": null,\n" +
            "\t\t\"combineType\": null,\n" +
            "\t\t\"supplyCount\": 1,\n" +
            "\t\t\"supplyFilterIndex\": 0,\n" +
            "\t\t\"receivedOrderId\": null,\n" +
            "\t\t\"forwardOrderId\": null,\n" +
            "\t\t\"actualCardPrice\": null,\n" +
            "\t\t\"userName\": null,\n" +
            "\t\t\"interceptType\": null,\n" +
            "\t\t\"interceptId\": null,\n" +
            "\t\t\"deputy\": false,\n" +
            "\t\t\"forwardType\": null,\n" +
            "\t\t\"salesArea\": null,\n" +
            "\t\t\"itemGroupId\": 0,\n" +
            "\t\t\"subCode\": 0,\n" +
            "\t\t\"outorderNo\": null,\n" +
            "\t\t\"callStatus\": 0\n" +
            "\t}],\n" +
            "\t\"totalItem\": 11,\n" +
            "\t\"tmallUser\": false\n" +
            "}";

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
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = calendar.getTime();
        String defaultStartDate = dateFmt.format(yesterday);
        String start = defaultStartDate.substring(0,10) + " 00:00:00";
        String end = defaultStartDate.substring(0,10) + " 23:23:59";

        Map<String, Object> requestParam = new HashMap<>();
        requestParam.put("startGmtCreate", start);
        requestParam.put("endGmtCreate", end);
        requestParam.put("pageSize", "500");
        requestParam.put("currentPage", "1");
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(requestParam);
        Collection<Object> values = requestParam.values();
        String signStr = "";
        for (Object v : treeMap.values()) {
            signStr += (String)v;
        }
        signStr += privateKey;
        String sign = Md5Encrypt.md5(signStr);
        requestParam.put("sign", sign);
        treeMap.put("sign", sign);
        String tmpStr = "";
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            tmpStr += entry.getKey() + "=" + entry.getValue() + "&";
        }

        String paramStr = StringUtils.stripEnd(tmpStr, "&");
        String url = requestUrl + "?" +paramStr;
        logger.info("-------------- 敬恒 拉取数据，start --------------");
        String result = OkHttpUtils.get(url);     //todo 打开注释
        logger.info("-------------- 敬恒 拉取数据，end --------------");
//        String result = this.result;
        JSONObject resultJosn = JSONObject.fromObject(result);
        JSONArray module = null;
        int valid_count = 0;
        if ( resultJosn.containsKey("module") )
        {
            module = JSONArray.fromObject(resultJosn.get("module"));
            logger.info("拉到数据，共计：{}条", module.size());
            if( module.size() != 0 ){
                int size = module.size();
                for (int i=0; i<size; i++){
                    JSONObject one = module.getJSONObject(i);

                    //过滤充值失败的订单
                    if ( one.containsKey("status") && !one.get("status").equals("2") )
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
        String path = "/logs/manage-web/log/";

        LogsCustomUtils.writeIntoFile(sbf.toString(),path, fileName, true);

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
