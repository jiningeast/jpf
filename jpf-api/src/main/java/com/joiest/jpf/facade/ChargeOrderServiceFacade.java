package com.joiest.jpf.facade;

import com.joiest.jpf.common.po.PayChargeOrder;
import com.joiest.jpf.dto.ChargeOrderInterfaceRequest;
import com.joiest.jpf.dto.GetChargeOrderRequest;
import com.joiest.jpf.dto.GetChargeOrderResponse;
import com.joiest.jpf.entity.BalanceOrder;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import com.joiest.jpf.entity.ChargeProductInfo;

import java.util.List;
import java.util.Map;

public interface ChargeOrderServiceFacade {

    /**
     * 查询单条信息
     */
    public ChargeOrderInfo getOne(PayChargeOrder record);

    /**
     * 生成订单
     * */
    public int placeOrder(ChargeOrderInfo placeOrderInfo);

    /**
     * 更新订单新
     * */
    public int upOrderInfo(ChargeOrderInfo upOrderInfo);

    /*
     * 查询订单
     */
    public GetChargeOrderResponse getRecords(GetChargeOrderRequest request);

    /*
     * 接口查询订单
     */
    public GetChargeOrderResponse getRecordsInterface(ChargeOrderInterfaceRequest request);

    /*
     * 订单统计
     */
    public Map<String, Object> getStatistics(ChargeOrderInterfaceRequest request);

    /**
     * 订单列表
     */
    public List<ChargeOrderInfo> getAbnormalOrders(ChargeOrderInfo request);

    /**
     * 订单列表
     */
    public List<PayChargeOrder> getAllAbnormalOrders();

    List<PayChargeOrder> getExcelRecords(GetChargeOrderRequest request);

    /**
     * 威能订单列表
     */
    public List<ChargeOrderInfo> getWeinengAbnormalOrders(ChargeOrderInfo request);

    Map<String,String> phoneRechargeWn(Map<String, String> actParam);


    /**
     * 更新订单状态
     * @param payChargeOrder
     */
    void updateOrder(PayChargeOrder payChargeOrder);

    /**
     * 对账单，如果存在异常订单，发送email
     * @param balanceOrders
     */
    void sendEmailToManager(List<BalanceOrder> balanceOrders);

    /**
     * 直充接口保存订单，并且保存流水，并且做相应的扣款操作
     * @param actParam
     * @param companyInfo
     * @param chargeProductInfo
     * @return
     */
    PayChargeOrder savePayOrder(Map<String, String> actParam, ChargeCompanyInfo companyInfo, ChargeProductInfo chargeProductInfo) throws Exception;

    /**
     * 扣款操作
     * @param companyInfo
     * @param chargeProductInfo
     * @throws Exception
     */
    void subCompanyMoney(ChargeCompanyInfo companyInfo,ChargeProductInfo chargeProductInfo) throws Exception;

    /**
     * 查询订单
     * @param id
     * @return
     */
    ChargeOrderInfo getById(String id);
}
