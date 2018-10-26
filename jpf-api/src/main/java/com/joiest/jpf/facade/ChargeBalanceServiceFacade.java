package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ChargeBalanceRequest;
import com.joiest.jpf.dto.ChargeBalanceResponse;
import com.joiest.jpf.entity.ChargeBalanceInfo;
import net.sf.json.JSONObject;

public interface ChargeBalanceServiceFacade {

    /**
     * 获取余额列表
     */
    public ChargeBalanceResponse getRecords(ChargeBalanceRequest request);

    /**
     * 根据主键id获取
     */
    public ChargeBalanceInfo getRecordByPrimaryKey(String id);

    /**
     * 根据主键更新
     */
    public JpfResponseDto edit(ChargeBalanceRequest request);

    /**
     * 根据主键修改余额信息
     * */
    public int updateBalanceById(ChargeBalanceInfo chargeBalanceInfo);

    /**
     * 获取余额信息 one
     * */
    public ChargeBalanceInfo getChargeBalanceOne(JSONObject param);
}
