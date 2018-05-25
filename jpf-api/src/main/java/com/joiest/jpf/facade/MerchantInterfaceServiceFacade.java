package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantInterfaceInfo;

public interface MerchantInterfaceServiceFacade {

    /**
     * 获取商户信息
     * @param id
     * @return
     */
    public MerchantInterfaceInfo getMerchant(Long id);

}
