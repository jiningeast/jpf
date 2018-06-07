package com.joiest.jpf.yinjia.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.dto.GetValueRequest;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.entity.OrderInfo;
import com.joiest.jpf.entity.OrderYinjiaApiInfo;
import com.joiest.jpf.entity.OrdersInfo;
import com.joiest.jpf.entity.ProductInfo;
import com.joiest.jpf.facade.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @Autowired
    private DemoServiceFacade demoServiceFacade;

    @Autowired
    private OrderServiceFacade orderServiceFacade;

    @Autowired
    private OrdersServiceFacade OrdersServiceFacade;

    @Autowired
    private ProductServiceFacade productServiceFacade;

    @Autowired
    private OrderYinjiaApiServiceFacade orderYinjiaApiServiceFacade;

    @RequestMapping(value = "/getValue")
    @ResponseBody
    public GetValueResponse getValue(@Valid GetValueRequest request){
        return demoServiceFacade.getValue(request);
    }

    @RequestMapping("/index")
    public void index(){
        System.out.println("it works");
    }

    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    @RequestMapping("/historyData9999999999999999999")
    public void historyData() {
        List<OrderInfo> list = orderServiceFacade.getAllOrders();
        for(OrderInfo orderInfo:list){
            System.out.println("开始处理orderid为"+orderInfo.getOrderid()+"的记录");
            String orderid = createOrderid();
            OrdersInfo ordersInfo = new OrdersInfo();
            ordersInfo.setOrderid(orderid);
            ordersInfo.setMtsid(""+orderInfo.getMtsid());
            ordersInfo.setMoney(orderInfo.getOrderprice());
            ordersInfo.setPaytype(orderInfo.getPaytype());
            ordersInfo.setProductId(""+orderInfo.getPid());
            // 获取商品详情
            Integer selfBusiness;
            if ( orderInfo.getPid() > 0 ){
                selfBusiness = 0;
            }else{
                selfBusiness = 1;
            }
            ProductInfo productInfo = productServiceFacade.getProduct(orderInfo.getPid());
            if ( productInfo.getPmoney() != null ){
                String produnaName = StringUtils.isNotBlank(productInfo.getPname()) ? productInfo.getPname() : "此商品无名称";
                ordersInfo.setProductName(produnaName);
                ordersInfo.setProductAmount("1");
                BigDecimal unitPrice;
                if ( StringUtils.isBlank(productInfo.getPmoney().toString()) ){
                    unitPrice = new BigDecimal("0");
                }else{
                    unitPrice = new BigDecimal(""+productInfo.getPmoney());
                }
                ordersInfo.setProductUnitPrice(unitPrice);
                ordersInfo.setSelfBusiness(selfBusiness);
                ordersInfo.setCreated(orderInfo.getAddtime());
            }else{
                ordersInfo.setProductName("(查无此商品)");
                ordersInfo.setProductAmount("1");
                ordersInfo.setProductUnitPrice(new BigDecimal("0"));
                ordersInfo.setSelfBusiness(selfBusiness);
                ordersInfo.setCreated(orderInfo.getAddtime() == null ? new Date() : orderInfo.getAddtime());
                System.out.println("UNEXPECTED:orderid为"+orderInfo.getOrderid()+"的数据中pid为"+orderInfo.getPid()+"查找不到商品信息");
            }
                // 原始数据表插入一条历史数据
                int ordersRes = OrdersServiceFacade.insRecord(ordersInfo);
                if ( ordersRes <= 0 ){
                    System.out.println("ERROR:orderid为"+ordersInfo.getOrderid()+"的数据插入orders表失败");
                }else{
                    System.out.println("SUCCESS:orderid为"+ordersInfo.getOrderid()+"的数据插入orders表成功");
                }
                // pay_order_yinjia_api表插入一条历史数据
                OrderYinjiaApiInfo orderYinjiaApiInfo = new OrderYinjiaApiInfo();
                orderYinjiaApiInfo.setOrderid(orderid);
                orderYinjiaApiInfo.setSignOrderid(orderInfo.getSignOrderid());
                String foreignRequest = "mid="+orderInfo.getMtsid()+"&orderid="+orderInfo.getOrderid()+"&notifyUrl="+"&productAmount=1"+"&productId="+orderInfo.getPid()+"&productName="+productInfo.getPname()+"&productTotalPrice="+orderInfo.getOrderprice()+"&productUniPrice="+orderInfo.getOrderprice()+"&returnUrl="+"";
                orderYinjiaApiInfo.setOrderid(orderid);
                orderYinjiaApiInfo.setForeignOrderid(orderInfo.getOrderid());
                orderYinjiaApiInfo.setSignOrderid(orderInfo.getSignOrderid());
                orderYinjiaApiInfo.setForeignRequest(foreignRequest);
                orderYinjiaApiInfo.setReturnUrl(orderInfo.getReturnUrl());
                orderYinjiaApiInfo.setNotifyUrl(orderInfo.getNotifyUrl());
                orderYinjiaApiInfo.setMtsid(orderInfo.getMtsid());
                orderYinjiaApiInfo.setPaytype(orderInfo.getPaytype());
                orderYinjiaApiInfo.setOrderPayPrice(orderInfo.getOrderprice());
                orderYinjiaApiInfo.setOrderStdPrice(orderInfo.getOrderselprice());
                orderYinjiaApiInfo.setProductAccount(1);
                orderYinjiaApiInfo.setPayDetail(orderInfo.getOrdername());
                orderYinjiaApiInfo.setPaytime(orderInfo.getPaytime());
                orderYinjiaApiInfo.setPayStatus(orderInfo.getOrderstatus());
                orderYinjiaApiInfo.setRefundStatus(orderInfo.getSinglestatus());
                // 根据订单状态判断用户操作流程
                byte userOperateStatus = 1;
                // 如果有签约id说明已经签约过
                if ( StringUtils.isNotBlank(""+orderInfo.getSignOrderid()) ){
                    userOperateStatus = 4;
                }
                if ( StringUtils.isNotBlank(""+orderInfo.getSignOrderid()) && orderInfo.getOrderstatus() == 1 ){
                    userOperateStatus = 11;
                }
                orderYinjiaApiInfo.setUserOperateStatus(userOperateStatus);
                orderYinjiaApiInfo.setAddtime(orderInfo.getAddtime());
                orderYinjiaApiInfo.setUpdatetime(orderInfo.getUpdatetime());
                int orderYinJiaApiRes = orderYinjiaApiServiceFacade.insOrder(orderYinjiaApiInfo);
                if ( orderYinJiaApiRes <= 0 ){
                    System.out.println("ERROR:orderid为"+orderInfo.getOrderid()+"的数据插入orderYinJiaApi表失败");
                }else{
                    System.out.println("SUCCESS:orderid为"+orderInfo.getOrderid()+"的数据插入orderYinJiaApi表成功");
                }
        }
    }

    /**
     * 测试获取配置文件的信息
     */
    public static String getProperties(String filePath, String keyWord){
        Properties properties = new Properties();
        String value = null;
        try{
            InputStream inputStream = DemoController.class.getResourceAsStream(filePath);
            properties.load(inputStream);
            value = properties.getProperty(keyWord);
        }catch (IOException e){
            e.printStackTrace();
        }
        return value;
    }


    // 生成一个pay_order表的orderid
    public String createOrderid(){
        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());
        middle = middle.substring(3,middle.length());

        return ""+pre+middle+last;
    }

    // 生成指定范围内的随机整数
    public int getRandomInt(int min, int max){
        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }

    public void main(String[] args){
//        this.getProperties();
    }
}
