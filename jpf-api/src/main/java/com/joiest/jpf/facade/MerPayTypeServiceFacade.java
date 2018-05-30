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
     * 获取某个商户指定支付类型的详情
     * 6：中银消费金融分期支付
     * 7：银联信用卡分期支付
     * 8：花呗分期支付
     * 9：微信全额支付
     */
    public MerchantPayTypeInfo getOneMerPayTypeByTpid(Long mtsid, Integer tpid);
    public MerchantPayTypeInfo getOneMerPayTypeByTpid(Long mtsid, Integer tpid, boolean forInterface);

    /**
     * 获取某个商户的单个支付类型 by id
     */
    public MerchantPayTypeInfo getMerOnePayTypes(Long id);

    /**
     * 商户分期类型配置
     */
    public JpfResponseDto modifyMerBankcatid(ModifyMerPayTypeRequest request);

    /**
     * 添加、修改商户密钥
     * @param id
     * @param pkey
     * @return
     */
    public JpfResponseDto modifyMerPKey(String id,String pkey);
}
