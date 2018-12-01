package com.joiest.jpf.facade;

import com.joiest.jpf.entity.ChargeConsumerOrderInfo;

import com.joiest.jpf.common.po.PayChargeConsumerOrder;
import com.joiest.jpf.entity.ChargeCompanyInfo;


public interface ConsumerOrderServiceFacade {

    /**
     * 根据单号获取单条记录
     */
    public ChargeConsumerOrderInfo getOneByOrderNo(String orderNo);
    /**
     * 下单接口
     * @param merchNo 商户号
     * @param money   下单金额
     * @param result  商户信息
     * @return
     */
    String payConsumerOrder(String merchNo, String money, ChargeCompanyInfo result);

    /**
     * 查询未执行的记录并且更新状态
     * @return
     */
    PayChargeConsumerOrder selectConsumerOrderTask();

    /**
     * 开始匹配数据
     * @param payChargeConsumerOrder
     */
    void matchingDataTaskStart(PayChargeConsumerOrder payChargeConsumerOrder) throws  Exception;
}
