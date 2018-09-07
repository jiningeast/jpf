package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ShopBatchCouponResponse;
import com.joiest.jpf.entity.ShopCustomerInfo;

import java.util.LinkedHashMap;
import java.util.List;

public interface ShopBatchCouponServiceFacade {

    /**
     * 根据批次号获取券
     */
    public ShopBatchCouponResponse getCouponByBatchId(String batchId, int page, int rows);

    /**
     * 根据面值和批次号查找某企业的券数量
     */
    public int getCouponNumByValue(String companyId, String value, String batchNo);

    /**
     * 批量发放欣券给个人
     */
    public List<ShopCustomerInfo> sendCouponsToPersons(List<LinkedHashMap<String,Object>> list, String companyId, String companyName, String batchNo, String excelLocalUrl);

    /**
     * 将一个欣券发给某个人
     */
    public ShopCustomerInfo sendCouponToPerson(String name, String phone, String idno, String dou, String companyId, String companyName, String batchId);

    /**
     * 根据批次id批量更新券的发送方式
     */
    public int updateCouponSendTypeByBatchId(String batchId);
}
