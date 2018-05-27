package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.YjResponseDto;

import java.util.Map;

public interface ChinaPayServiceFacade {

//    public IntallPay
    public YjResponseDto ChinaPaySmsCodeSend(Map<String,Object> map,String requestUrl);
    public YjResponseDto ChinaPayRefund(Map<String,Object> map,String requestUrl);
}
