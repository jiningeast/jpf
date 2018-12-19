package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ChargeCompanyMoneyStreamInterfaceRequest;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;
import com.joiest.jpf.entity.ChargeCompanyMoneyStreamInfo;

import java.util.List;

public interface ChargeCompanyMoneyStreamServiceFacade {

    /**
     * 获取商户列表
     */
    public ChargeCompanyMoneyStreamResponse getRecords(ChargeCompanyMoneyStreamRequest request);

    /**
     * 接口获取商户列表
     */
    public ChargeCompanyMoneyStreamResponse getStreamList(ChargeCompanyMoneyStreamInterfaceRequest request);

    /**
     * 添加流水记录
     */
    public int create(PayChargeCompanyMoneyStream record);

    /**
     * 新增流水
     */
    public int insRecord(ChargeCompanyMoneyStreamInfo info);

    /**
     * 更新流水
     */
    public int updateRecord(PayChargeCompanyMoneyStream record,String order_no);

    /**
     * 根据订单id查询流水
     * @param id
     * @return
     */
    List<PayChargeCompanyMoneyStream> getChargeCompanyMoneyStreamByOrderId(String id);
}
