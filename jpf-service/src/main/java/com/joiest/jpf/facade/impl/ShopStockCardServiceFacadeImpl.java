package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopStockLogCustom;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopStockLogCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockCardMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderProductMapper;
import com.joiest.jpf.dto.GetShopStockCardResponse;
import com.joiest.jpf.entity.ShopProductInfo;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.entity.ShopStockCardInfo;
import com.joiest.jpf.facade.ShopProductServiceFacade;
import com.joiest.jpf.facade.ShopStockCardServiceFacade;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShopStockCardServiceFacadeImpl implements ShopStockCardServiceFacade {

    @Autowired
    private PayShopStockCardMapper payShopStockCardMapper;

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Autowired
    private PayShopStockOrderProductMapper payShopStockOrderProductMapper;

    @Autowired
    private ShopProductServiceFacade shopProductServiceFacade;

    @Autowired
    private PayShopStockLogCustomMapper payShopStockLogCustomMapper;
    /**
     * 添加采购商品
     * */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })//事务添加
    public int addShopStockCard(JSONObject stockCard,JSONObject param) throws Exception{

        PayShopStockCard payShopStockCard = new PayShopStockCard();

        if (stockCard.containsKey("productId") && !(stockCard.get("productId").equals(""))){

            payShopStockCard.setProductId(stockCard.get("productId").toString());
        }
        if (stockCard.containsKey("productName") && !(stockCard.get("productName").equals("")))
            payShopStockCard.setProductName(stockCard.get("productName").toString());

        if (stockCard.containsKey("supplierId") && !(stockCard.get("supplierId").equals("")))
            payShopStockCard.setSupplierId(stockCard.get("supplierId").toString());

        if (stockCard.containsKey("supplierName") && !(stockCard.get("supplierName").equals("")))
            payShopStockCard.setSupplierName(stockCard.get("supplierName").toString());

        if (stockCard.containsKey("cardNo") && !(stockCard.get("cardNo").equals("")))
            payShopStockCard.setCardNo(stockCard.get("cardNo").toString());

        if (stockCard.containsKey("cardPass") && !(stockCard.get("cardPass").equals("")))
            payShopStockCard.setCardPass(stockCard.get("cardPass").toString());

        if (stockCard.containsKey("expireMonth") && !(stockCard.get("expireMonth").equals("")))
            payShopStockCard.setExpireMonth(Integer.valueOf(stockCard.get("expireMonth").toString()));

        if (stockCard.containsKey("expireDate") && !(stockCard.get("expireDate").equals(""))){

            JSONObject expireTime = JSONObject.fromObject(stockCard.get("expireDate"));
            Date expireDate = DateUtils.stampToDateRe(expireTime.get("time").toString());
            payShopStockCard.setExpireDate(expireDate);
        }
        if (stockCard.containsKey("stockOrderId") && !(stockCard.get("stockOrderId").equals("")))
            payShopStockCard.setStockOrderId(stockCard.get("stockOrderId").toString());

        if (stockCard.containsKey("stockOrderNo") && !(stockCard.get("stockOrderNo").equals("")))
            payShopStockCard.setStockOrderNo(stockCard.get("stockOrderNo").toString());

        if (stockCard.containsKey("cardType") && !(stockCard.get("cardType").equals("")))
            payShopStockCard.setCardType(Byte.valueOf(stockCard.get("cardType").toString()));

        payShopStockCard.setAddtime(new Date());

        int isSuc =payShopStockCardMapper.insertSelective(payShopStockCard);

        if(param.get("sigleSize").equals(param.get("allSize")))
            upProductStockByOrderNo(param);

        return isSuc;
    }
    /***
     *根据采购订单更新商品库存
     * */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })//事务添加
    public int upProductStockByOrderNo(JSONObject param) throws Exception{

        //获取采购订单商品
        PayShopStockOrderProductExample example = new PayShopStockOrderProductExample();
        PayShopStockOrderProductExample.Criteria c = example.createCriteria();
        c.andStockOrderNoEqualTo(param.get("orderNo").toString());
        List<PayShopStockOrderProduct> listProducts = payShopStockOrderProductMapper.selectByExample(example);

        Boolean isUp = true;
        //循环修改商品表里面的库存
        for (int i=0;i<listProducts.size();i++)
        {
            //获取单挑商品数据
            PayShopStockOrderProduct stockOrderProduct = listProducts.get(i);

            //获取当前商品最后进销存统一信息   取出最后一条数据
            PayShopStockLogExample exampledesc = new PayShopStockLogExample();
            PayShopStockLogExample.Criteria logc = exampledesc.createCriteria();
            exampledesc.setOrderByClause("ID DESC");
            logc.andProductIdEqualTo(stockOrderProduct.getProductId());
            List<PayShopStockLogCustom> listdesc = payShopStockLogCustomMapper.selectCustomAsc(exampledesc);

            //获取商品数据 pay_shop_product
            ShopProductInfo shopProductInfo =shopProductServiceFacade.getShopProduct(listProducts.get(i).getProductId());
            Integer store = shopProductInfo.getStored()+listProducts.get(i).getAmount();

            //修改商品表库存 pay_shop_product
            PayShopProduct payShopProduct=new PayShopProduct();
            payShopProduct.setStored(store);
            payShopProduct.setId(listProducts.get(i).getProductId());
            int isSuc = payShopProductMapper.updateByPrimaryKeySelective(payShopProduct);
            if(isSuc != 1){

                isUp = false;
            }
            //插入上商品log表 pay_shop_stock_log
            PayShopStockLog payShopStockLog = new PayShopStockLog();
            payShopStockLog.setStockOrderId(stockOrderProduct.getStockOrderId());
            payShopStockLog.setStockOrderNo(stockOrderProduct.getStockOrderNo());
            payShopStockLog.setProductId(stockOrderProduct.getProductId());
            payShopStockLog.setProductName(stockOrderProduct.getProductName());
            payShopStockLog.setOperatorId(param.get("userid").toString());
            payShopStockLog.setOperatorName(param.get("username").toString());
            //payShopStockLog.setInitMoney(stockOrderProduct.getProductBid());

            payShopStockLog.setDailyInMoney(stockOrderProduct.getMoney());
            payShopStockLog.setDailyInAmount(stockOrderProduct.getAmount());
            /**
             *
             *当前采购商品为空   期初结存为0   期末结存为日常进货
             *当前采购商品不为空  期初结存为当前商品最后一条的期末结存   期末结存为日常进货+期初结存
            */
            if(listdesc == null || listdesc.size()==0){

                payShopStockLog.setInitMoney(new BigDecimal(0));
                payShopStockLog.setInitAmount(0);

                payShopStockLog.setFinalMoney(stockOrderProduct.getMoney());
                payShopStockLog.setFinalAmount(stockOrderProduct.getAmount());
            }else{

                payShopStockLog.setInitMoney(listdesc.get(0).getFinalMoney());
                payShopStockLog.setInitAmount(listdesc.get(0).getFinalAmount());

                BigDecimal finalyMoney = listdesc.get(0).getFinalMoney().add(stockOrderProduct.getMoney());
                payShopStockLog.setFinalMoney(finalyMoney);
                payShopStockLog.setFinalAmount(listdesc.get(0).getFinalAmount()+stockOrderProduct.getAmount());
            }
            payShopStockLog.setAddtime(new Date());
            int stockLog = payShopStockLogCustomMapper.insertSelective(payShopStockLog);
            if(stockLog != 1){

                isUp = false;
            }
        }
        if(isUp)
            return 1;
        else
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新库存失败");
    }

    /**
     * 查询商品的卡密 一条
     * */
    public ShopStockCardInfo getShopCard(String productId,Byte status){

        PayShopStockCardExample example = new PayShopStockCardExample();
        example.setPageNo(1);
        example.setPageSize(1);
        PayShopStockCardExample.Criteria c = example.createCriteria();

        c.andProductIdEqualTo(productId);
        c.andStatusEqualTo(status);

        List<PayShopStockCard> getPayShopStockCard = payShopStockCardMapper.selectByExample(example);

        if(getPayShopStockCard == null || getPayShopStockCard.isEmpty()) return null;

        PayShopStockCard payShopStockCard = getPayShopStockCard.get(0);

        ShopStockCardInfo shopStockCardInfo = new ShopStockCardInfo();

        BeanCopier beanCopier = BeanCopier.create(PayShopStockCard.class,ShopStockCardInfo.class,false);
        beanCopier.copy(payShopStockCard,shopStockCardInfo,null);

        return shopStockCardInfo;
    }

    /**
     * 通过主键更新卡密信息
     * */
    @Override
    public int upShopCardById(Map<String,String> cardInfo){

        PayShopStockCard payShopStockCard = new PayShopStockCard();

        payShopStockCard.setId(cardInfo.get("id"));
        if(cardInfo.containsKey("customer_id"))
            payShopStockCard.setCustomerId(cardInfo.get("customer_id"));

        if(cardInfo.containsKey("customer_name"))
            payShopStockCard.setCustomerName(cardInfo.get("customer_name"));

        if(cardInfo.containsKey("customer_phone"))
            payShopStockCard.setCustomerPhone(cardInfo.get("customer_phone"));

        if(cardInfo.containsKey("paytime"))
            payShopStockCard.setPaytime(new Date());

        if(cardInfo.containsKey("status"))
            payShopStockCard.setStatus(Byte.valueOf(cardInfo.get("status")));


        return payShopStockCardMapper.updateByPrimaryKeySelective(payShopStockCard);
    }
    /***
     *根据pay_shop_product更新商品库存
     * */
    @Override
    public int upProductStockByPid(Map<String,String> cardInfo , ShopProductInterfaceInfo productInfo, ShopStockCardInfo shopStockCard) throws Exception{

        boolean isUp = true;
        Integer orderAmout = Integer.valueOf(cardInfo.get("orderAmount"));

        //获取当前商品最后进销存统一信息   取出最后一条数据
        PayShopStockLogExample exampledesc = new PayShopStockLogExample();
        PayShopStockLogExample.Criteria logc = exampledesc.createCriteria();
        exampledesc.setOrderByClause("ID DESC");
        logc.andProductIdEqualTo(productInfo.getId());
        List<PayShopStockLogCustom> listdesc = payShopStockLogCustomMapper.selectCustomAsc(exampledesc);

        //修改商品表库存 pay_shop_product
        Integer store = productInfo.getStored()-orderAmout;
        PayShopProduct payShopProduct=new PayShopProduct();
        payShopProduct.setStored(store);
        payShopProduct.setId(productInfo.getId());
        int isSuc = payShopProductMapper.updateByPrimaryKeySelective(payShopProduct);
        if(isSuc != 1){

            isUp = false;
        }
        //插入上商品log表 pay_shop_stock_log
        PayShopStockLog payShopStockLog = new PayShopStockLog();
        payShopStockLog.setStockOrderId(shopStockCard.getStockOrderId());
        payShopStockLog.setStockOrderNo(shopStockCard.getStockOrderNo());
        payShopStockLog.setProductId(shopStockCard.getProductId());
        payShopStockLog.setProductName(shopStockCard.getProductName());
        payShopStockLog.setCustomerId(cardInfo.get("customer_id"));
        payShopStockLog.setCustomerName(cardInfo.get("customer_name"));

        payShopStockLog.setDailyOutMoney(new BigDecimal(cardInfo.get("total_money")));//日常进货金额
        payShopStockLog.setDailyOutAmount(orderAmout);//日常进货数量

        /**
         *
         *当前采购商品为空   期初结存为0   期末结存为日常进货
         *当前采购商品不为空  期初结存为当前商品最后一条的期末结存   期末结存为日常进货+期初结存
         */
        if(listdesc == null || listdesc.size()==0){

            payShopStockLog.setInitMoney(new BigDecimal(0));
            payShopStockLog.setInitAmount(0);

            payShopStockLog.setFinalMoney(new BigDecimal(cardInfo.get("total_money")));
            payShopStockLog.setFinalAmount(orderAmout);
        }else{

            payShopStockLog.setInitMoney(listdesc.get(0).getFinalMoney());
            payShopStockLog.setInitAmount(listdesc.get(0).getFinalAmount());

            BigDecimal finalyMoney = listdesc.get(0).getFinalMoney().subtract(new BigDecimal(cardInfo.get("total_money")));
            payShopStockLog.setFinalMoney(finalyMoney);
            payShopStockLog.setFinalAmount(listdesc.get(0).getFinalAmount()-orderAmout);
        }
        payShopStockLog.setAddtime(new Date());
        int stockLog = payShopStockLogCustomMapper.insertSelective(payShopStockLog);
        if(stockLog != 1){

            isUp = false;
        }

        if(isUp)
            return 1;
        else
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新库存失败");
    }



    /**
     * 查找某个用户买过的卡密个数
     */
    @Override
    public int getBoughtCardCount(String customerId){
        PayShopStockCardExample e = new PayShopStockCardExample();
        PayShopStockCardExample.Criteria c = e.createCriteria();
        c.andCustomerIdEqualTo(customerId);
        c.andCardTypeEqualTo((byte)2);
        c.andStatusEqualTo((byte)1);

        return payShopStockCardMapper.countByExample(e);
    }

    /**
     * 查找某个用户买过的卡密
     */
    @Override
    public GetShopStockCardResponse getCardM (String customerId){

        PayShopStockCardExample e = new PayShopStockCardExample();
        PayShopStockCardExample.Criteria c = e.createCriteria();
        e.setOrderByClause("ID DESC");
        c.andCustomerIdEqualTo(customerId);
        c.andCardTypeEqualTo((byte)2);
        c.andStatusEqualTo((byte)1);
        List<PayShopStockCard> list=payShopStockCardMapper.selectByExample(e);
        List<ShopStockCardInfo>infoList= new ArrayList<>();
        for (PayShopStockCard one : list)
        {
            ShopStockCardInfo info = new ShopStockCardInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopStockCard.class, ShopStockCardInfo.class, false);
            beanCopier.copy(one, info, null);
            //时间格式转换
            if( info !=null){
                Date currentTime = one.getPaytime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateString = formatter.format(currentTime);
                info.setPaytimeCopy(dateString);
            }
            infoList.add(info);
        }
        GetShopStockCardResponse response = new GetShopStockCardResponse();
        response.setList(infoList);
        int count = payShopStockCardMapper.countByExample(e);
        response.setCount(count);
        return response;
    }
}
