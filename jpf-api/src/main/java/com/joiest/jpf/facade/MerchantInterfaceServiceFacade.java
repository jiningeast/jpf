package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantInterfaceInfo;

public interface MerchantInterfaceServiceFacade {

    /**
     * 获取商户信息
     * @param MerchNo
     * @return
     */
    public MerchantInterfaceInfo getMerchantByMerchNo(String MerchNo);

    /**
     * 根据主键获取商户信息
     * @param id
     * @return
     */
    public MerchantInterfaceInfo getMerchantByMid(Long id);
}
