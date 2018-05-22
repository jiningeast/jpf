package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AuditMerchRequest;
import com.joiest.jpf.dto.GetMerchsRequest;
import com.joiest.jpf.dto.GetMerchsResponse;
import com.joiest.jpf.dto.ModifyMerchRequest;
import com.joiest.jpf.entity.MerchantBankInfo;
import com.joiest.jpf.entity.MerchantInfo;
import com.joiest.jpf.entity.MerchantInterfaceInfo;

public interface MerchantInterfaceServiceFacade {

    /**
     * 获取商户信息列表
     * @param request
     * @return
     */
    public GetMerchsResponse getMerchInfoList(GetMerchsRequest request);

    /**
     * 获取商户信息
     * @param id
     * @return
     */
    public MerchantInterfaceInfo getMerchant(Long id);

    /**
     * 获取商户对公账户信息
     * @param mtsid
     * @return
     */
    public MerchantBankInfo getMerchBank(Long mtsid);

    /**
     * 修改商户（商户信息及商户对公账户信息）
     * @param request
     * @return
     */
    public JpfResponseDto modifyMerchant(ModifyMerchRequest request);

    /**
     * 审核商户信息
     * @param request
     * @return
     */
    public JpfResponseDto auditMerchant(AuditMerchRequest request);

}
