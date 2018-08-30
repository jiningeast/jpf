package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.GetShopStockOrderRequest;
import com.joiest.jpf.dto.GetShopStockOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopStockOrderInfo;

import java.util.Map;

public interface ShopStockOrderServiceFacade {

    /**
     * 运营采购订单列表---后台
     */
    public GetShopStockOrderResponse getList(GetShopStockOrderRequest request);
    /**
     * 财务采购订单列表---后台
     */
    public GetShopStockOrderResponse getListCaiwu(GetShopStockOrderRequest request);

    /**
     * 采购订单详情---后台
     */
    public ShopStockOrderInfo getOne(String order_no);

    /***
     *根据主键id获取采购订单
     * */
    public ShopStockOrderInfo getStockOrderById(String id);


    /***
     *根据主键id更新采购订单
     * */
    public int upStockOrderById(Map<String,String> stockOrder);

    /**
     * 添加采购订单
     */
    public JpfResponseDto addStocks(GetShopStockOrderRequest request) throws Exception;

    /*
     * 审核采购信息
     * */
     public JpfResponseDto AuditOrder(GetShopStockOrderRequest request) throws Exception;
}
