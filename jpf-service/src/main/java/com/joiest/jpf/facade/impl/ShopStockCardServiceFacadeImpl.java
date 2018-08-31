package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockCardMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderProductMapper;
import com.joiest.jpf.facade.ShopStockCardServiceFacade;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 添加采购商品
     * */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })//事务添加
    public int addShopStockCard(JSONObject stockCard,String OrderNo) throws Exception{

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

        payShopStockCard.setAddtime(new Date());

        int isSuc =payShopStockCardMapper.insertSelective(payShopStockCard);

        //upProductStockByOrderNo(OrderNo);

        return isSuc;
    }
    /***
     *根据订单更新商品库存
     * */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })//事务添加
    public int upProductStockByOrderNo(String order_no) throws Exception{

        PayShopStockOrderProductExample example = new PayShopStockOrderProductExample();
        PayShopStockOrderProductExample.Criteria c = example.createCriteria();
        c.andStockOrderNoEqualTo(order_no);

        List<PayShopStockOrderProduct> listProducts = payShopStockOrderProductMapper.selectByExample(example);
        Boolean isUp = true;
        //循环修改商品表里面的库存
        for (int i=0;i<listProducts.size();i++)
        {
            //修改商品表
            PayShopProductExample example1=new PayShopProductExample();
            PayShopProductExample.Criteria c1=example1.createCriteria();

            c1.andIdEqualTo(listProducts.get(i).getProductId());

            PayShopProduct payShopProduct=new PayShopProduct();//listProducts.get(i).getAmount()
            payShopProduct.setStored(Integer.valueOf(""));

            int isSuc = payShopProductMapper.updateByExampleSelective(payShopProduct,example1);
            if(isSuc != 1){

                isUp = false;
            }
            //插入上商品log表；
        }
        if(isUp)
            return 0;
        else
            return 1;
    }
}
