package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantPayTypeInfo;

import java.util.List;

public interface MerPayTypeInterfaceServiceFacade {

    /**
     * 获取某个商户指定支付类型的详情
     * 6：中银消费金融分期支付
     * 7：银联信用卡分期支付
     * 8：花呗分期支付
     * 9：微信全额支付
     */
    public MerchantPayTypeInfo getOneMerPayTypeByTpid(Long mtsid, Integer tpid);

}
