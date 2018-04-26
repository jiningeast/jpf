package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;
import com.joiest.jpf.dto.ModifyMerPayTypeRequest;
import com.joiest.jpf.entity.MerchantPayTypeInfo;

import java.util.List;

public interface MerPayTypeServiceFacade {

    /**
     * 获取所有商户的支付类型
     */
    public GetMerchPayTypeResponse getMerPayTypes(GetMerchPayTypeRequest request);

    /**
     * 添加支付类型 --批量
     */
    public JpfResponseDto addMerPayType(AddMerPayTypeRequest request);

    /**
     * 添加支付类型 -- 单个
     */
    public JpfResponseDto addMerPayTypeOne(ModifyMerPayTypeRequest request);

    /**
     * 编辑支付类型 -- 单个
     */
    public JpfResponseDto modifyMerPayTypeOne(ModifyMerPayTypeRequest request);

    /**
     * 删除支付类型 --批量
     */
    public JpfResponseDto deleteMerPayType(List<Long> id);

    /**
     * 获取某个商户的支付类型
     */
    public GetMerchPayTypeResponse getOneMerPayTypes(Long mtsid);

    /**
     * 获取某个商户的单个支付类型 by id
     */
    public MerchantPayTypeInfo getMerOnePayTypes(Long id);
}
