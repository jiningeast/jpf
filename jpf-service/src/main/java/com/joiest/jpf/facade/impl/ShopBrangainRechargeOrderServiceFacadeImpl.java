package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.generate.*;
import com.joiest.jpf.dto.ModifyBrangainRechargeorderRequest;
import com.joiest.jpf.entity.ModifyBrangainRechargeorderInfo;
import com.joiest.jpf.entity.ShopBargainRechargeViewInfo;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.facade.ShopBrangainRechargeOrderServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopBrangainRechargeOrderServiceFacadeImpl implements ShopBrangainRechargeOrderServiceFacade {

    private static final Logger logger = LogManager.getLogger(ShopBrangainRechargeOrderServiceFacadeImpl.class);

    @Autowired
    private PayShopBargainRechargeOrderMapper payShopBargainRechargeOrderMapper;

    @Autowired
    private PayShopBargainRechargeViewMapper payShopBargainRechargeViewMapper;

    @Autowired
    private PayShopBargainOrderMapper payShopBargainOrderMapper;

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Autowired
    private PayShopOrderMapper payShopOrderMapper;

    //数据入库
    public JpfResponseDto insertInfo(ModifyBrangainRechargeorderRequest rechargeorderRequest)
    {
        PayShopBargainRechargeOrder info = new PayShopBargainRechargeOrder();
        BeanCopier beanCopier = BeanCopier.create(ModifyBrangainRechargeorderRequest.class, PayShopBargainRechargeOrder.class, false);
        beanCopier.copy(rechargeorderRequest, info, null);

        payShopBargainRechargeOrderMapper.insertSelective(info);
        return new JpfResponseDto();
    }

    @Override
    public List<ShopBargainRechargeViewInfo> getOrderView() {

        PayShopBargainRechargeViewExample example = new PayShopBargainRechargeViewExample();
        example.setOrderByClause("id DESC");
        PayShopBargainRechargeViewExample.Criteria c = example.createCriteria();
        c.andStatusEqualTo(1);  //未完成
        List<PayShopBargainRechargeView> list = payShopBargainRechargeViewMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return null;
        }
        List<ShopBargainRechargeViewInfo> resultList = new ArrayList<>();
        for (PayShopBargainRechargeView one : list)
        {
            ShopBargainRechargeViewInfo info = new ShopBargainRechargeViewInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainRechargeView.class, ShopBargainRechargeViewInfo.class, false );
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }

        return resultList;
    }

    @Override
    public List<ModifyBrangainRechargeorderInfo> getDataList() {

        PayShopBargainRechargeOrderExample example = new PayShopBargainRechargeOrderExample();
        example.setOrderByClause("face_price DESC");
        PayShopBargainRechargeOrderExample.Criteria c = example.createCriteria();
        c.andInfoStatusEqualTo(1);
        List<PayShopBargainRechargeOrder> list = payShopBargainRechargeOrderMapper.selectByExample(example);
        if ( list == null || list.isEmpty() )
        {
            return null;
        }
        List<ModifyBrangainRechargeorderInfo> resultList = new ArrayList<>();
        for (PayShopBargainRechargeOrder one : list)
        {
            ModifyBrangainRechargeorderInfo info = new ModifyBrangainRechargeorderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainRechargeOrder.class, ModifyBrangainRechargeorderInfo.class, false);
            beanCopier.copy(one, info, null);
            resultList.add(info);
        }
        return resultList;
    }

    @Override
    public int doBingOrderData(List<ShopBargainRechargeViewInfo> viewList, List<ModifyBrangainRechargeorderInfo> orderList) {
        List<String> facePriceList = new ArrayList<String>()
        {
            {
                add("10.00");
            }
        };

        for (ShopBargainRechargeViewInfo view : viewList) {
            //绑定数据 _order
            BigDecimal order_remain = view.getRemainPrice();       //剩余金额
            BigDecimal order_already = view.getAlreadyPrice();      //已拥有
            System.out.println(order_remain.compareTo(BigDecimal.ZERO));
            if ( order_remain.compareTo(BigDecimal.ZERO) == 0 )
            {
                logger.info("订单被过滤掉：订单未绑定余额为0, 订单id: {}, 订单编号: {}", view.getBoid(), view.getOrderNo());
                continue;
            }
            List<ModifyBrangainRechargeorderInfo> list_order = getDataList();
            if ( list_order == null || list_order.isEmpty() )
            {
                logger.info("没有原始数据可供使用, 订单id: {}, 订单编号: {}", view.getBoid(), view.getOrderNo());
                return -1;  //没有数据可执行
            }
            for (ModifyBrangainRechargeorderInfo orderInfo : orderList) {
                System.out.println("order_remain.compareTo(orderInfo.getFacePrice()) : " + order_remain.compareTo(orderInfo.getFacePrice()));
                if (order_remain.compareTo(orderInfo.getFacePrice()) == 1) //大于
                {
                    order_remain = order_remain.subtract(orderInfo.getFacePrice());
                    order_already = order_already.add(orderInfo.getFacePrice());
                    view.setAlreadyPrice(order_already);
                    view.setRemainPrice(order_remain);
                    doBingData(orderInfo, view);
                } else {
                    //小于
                    System.out.println(facePriceList.contains(orderInfo.getFacePrice().toString()));
                    System.out.println(orderInfo.getFacePrice().toString());
                    if (order_remain.compareTo(orderInfo.getFacePrice()) == -1 && !facePriceList.contains(orderInfo.getFacePrice().toString())) {
                        continue;
                    }
                    order_remain = order_remain.subtract(orderInfo.getFacePrice());
                    order_already = order_already.add(orderInfo.getFacePrice());
                    if ( order_remain.compareTo(BigDecimal.ZERO) == -1 )
                    {
                        order_remain = new BigDecimal("0");
                        order_already = view.getTransferPrice();
                    }
                    view.setAlreadyPrice(order_already);
                    view.setRemainPrice(order_remain);
                    doBingData(orderInfo, view);
                }

                if (order_remain.compareTo(BigDecimal.ZERO) == 0 ) {
                    order_remain = new BigDecimal("0.00");
                    //订单：已完成
                    updateView(view);
                    break;
                }
            }
        }
        logger.info("crontab 执行完成， 订单共计 {} 条", viewList.size());
        return 1;
    }

    private Boolean doBingData(ModifyBrangainRechargeorderInfo orderInfo, ShopBargainRechargeViewInfo viewInfo)
    {
        //更新orderdata orderView
        PayShopBargainRechargeView view = new PayShopBargainRechargeView();
        view.setId(viewInfo.getId());
        view.setAlreadyPrice(viewInfo.getAlreadyPrice());
        view.setRemainPrice(viewInfo.getRemainPrice());
        view.setUpdatetime(new Date());
        logger.info("更新view订单，订单id: {}, already_price:{} ; remain_price: {}", viewInfo.getId(), viewInfo.getAlreadyPrice(), viewInfo.getRemainPrice());
        payShopBargainRechargeViewMapper.updateByPrimaryKeySelective(view);

        PayShopBargainRechargeOrder order = new PayShopBargainRechargeOrder();
        order.setId(orderInfo.getId());
        order.setOrderNo(viewInfo.getOrderNo());
        order.setBoid(viewInfo.getBoid());
        order.setInfoStatus(2);
        order.setUpdatetime(new Date());
        logger.info("更新bargain_recharge_order订单绑定状态，绑定金额为{}，pay_shop_bargain_order.id: {}, pay_shop_bargain_order.order_no", orderInfo.getFacePrice(), viewInfo.getId(), viewInfo.getOrderNo());
        payShopBargainRechargeOrderMapper.updateByPrimaryKeySelective(order);

        //获取订单信息
        PayShopBargainOrder bargainOrder = getOrderOne(viewInfo.getBoid().toString());
        if ( bargainOrder == null )
        {
            logger.info("未找到bargain订单, pay_shop_order 添加订单失败; pay_shop_bargain_order.id: {}", viewInfo.getBoid().toString());
            return false;
        }
        //匹配商品信息
        String goodsName = orderInfo.getItemName();
        String reg_money = "\\d{1,5}";
        Pattern pattern = Pattern.compile(reg_money);
        Matcher matcher = pattern.matcher(goodsName);
        String goodsMoney = "";
        if ( !matcher.find() )
        {
            logger.info("未匹配到商品金额, 订单id为：{}", viewInfo.getBoid().toString());
            return false;
        }

        logger.info("匹配商品金额为 {} 元", matcher.group(0));
        goodsMoney = matcher.group(0);
        ShopProductInterfaceInfo productInfo = getProduct(goodsMoney);      //直充类型
        if ( productInfo == null )
        {
            logger.info("未匹配到商品信息, pay_shop_bargain_order.id: {}", viewInfo.getBoid().toString());
            return false;
        }

        //添加订单信息
        PayShopOrder payShopOrder = new PayShopOrder();
        String orderno = createOrderid();
        payShopOrder.setOrderNo(orderno);
        payShopOrder.setChargeType((byte)0);
        payShopOrder.setOrderType((byte)3);
        payShopOrder.setForeignOrderNo(orderInfo.getForeignOrderNo());
        payShopOrder.setCustomerId(bargainOrder.getBuyerCustomerId());
        payShopOrder.setCustomerName(bargainOrder.getBuyerCustomerNickname());
        payShopOrder.setProductId(productInfo.getId());
        payShopOrder.setProductName(productInfo.getName());
        payShopOrder.setProductMoney(productInfo.getMoney());
        payShopOrder.setProductDou(productInfo.getDou());
        payShopOrder.setProductInfoId(productInfo.getProductInfoId());
        payShopOrder.setAmount(1);
        payShopOrder.setTotalMoney(productInfo.getMoney());
        payShopOrder.setTotalDou(productInfo.getDou());
        payShopOrder.setPayWay((byte)0);
        payShopOrder.setChargeNo(orderInfo.getChargeNo());
        payShopOrder.setStatus((byte)1);
        payShopOrder.setSource((byte)1);

        int random = getRandomInt(5,20);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(bargainOrder.getPaytime());
        calendar.add(Calendar.MINUTE, random);
        Date addtime = calendar.getTime();
        int random_second = getRandomInt(5,60);
        calendar.add(Calendar.SECOND, random_second);
        Date paytime = calendar.getTime();

        payShopOrder.setAddtime(addtime);
        payShopOrder.setPaytime(paytime);
        payShopOrder.setBargainOrderId(viewInfo.getBoid().toString());
        payShopOrder.setBargainOrderNo(viewInfo.getOrderNo());
        payShopOrder.setSource((byte)1);
        payShopOrderMapper.insertSelective(payShopOrder);

        return true;
    }

    private Boolean updateView(ShopBargainRechargeViewInfo viewInfo)
    {
        PayShopBargainRechargeView view = new PayShopBargainRechargeView();
        view.setId(viewInfo.getId());
        view.setStatus(2);
        view.setUpdatetime(new Date());
        payShopBargainRechargeViewMapper.updateByPrimaryKeySelective(view);
        return true;
    }

    private PayShopBargainOrder getOrderOne(String orderid) {
        PayShopBargainOrder order  = payShopBargainOrderMapper.selectByPrimaryKey(orderid);
        if ( order == null )
        {
            return null;
        }
        return order;
    }

    private ShopProductInterfaceInfo getProduct(String goodsMoney)
    {
        PayShopProductExample example = new PayShopProductExample();
        PayShopProductExample.Criteria c = example.createCriteria();
        c.andRechargeMoneyEqualTo(Integer.valueOf(goodsMoney));
        c.andTypeEqualTo((byte)0);      //直充类型
        c.andNameLike("%话费%");  //话费充值
//        c.andStatusEqualTo((byte)1);    //上架
        List<PayShopProduct> productInfoList = payShopProductMapper.selectByExample(example);
        if ( productInfoList == null || productInfoList.isEmpty() )
        {
            return null;
        }
        ShopProductInterfaceInfo productInfo = new ShopProductInterfaceInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopProduct.class, ShopProductInterfaceInfo.class, false);
        beanCopier.copy(productInfoList.get(0), productInfo, null);

        return productInfo;
    }

    /**
     * 生成一个唯一数
     * */
    private static String createOrderid(){

        int pre = getRandomInt(100,999);
        int last = getRandomInt(100,999);
        String middle = String.valueOf(System.currentTimeMillis());

        return ""+pre+middle+last;
    }

    /*
     *   生成指定范围内的随机整数
     **/
    private static int getRandomInt(int min, int max){

        Random random = new Random();
        int randomInt = random.nextInt(max)%(max-min+1) + min;

        return randomInt;
    }

}
