package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.YjResponseDto;

import java.util.Map;

public interface ChinaPayServiceFacade {

    /**
     * 银联分期---发起支付
     * @param map
     */
    public YjResponseDto IntallPay(Map<String,Object> map, String backUrl);
    public YjResponseDto ChinaPaySmsCodeSend(Map<String,Object> map,String requestUrl);
}
