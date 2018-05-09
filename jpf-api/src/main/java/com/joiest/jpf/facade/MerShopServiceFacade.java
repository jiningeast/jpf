package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.MerShopRequest;
import com.joiest.jpf.dto.MerShopResponse;
import com.joiest.jpf.entity.MerchantShopInfo;

public interface MerShopServiceFacade {

    public Integer getMerShopsCount();

    public MerShopResponse getMerShops(MerShopRequest merShopRequest);

    /**
     * 获取单个商户的门店信息
     */
    public MerchantShopInfo getOneMerShopInfo(Long mtsid);

}
