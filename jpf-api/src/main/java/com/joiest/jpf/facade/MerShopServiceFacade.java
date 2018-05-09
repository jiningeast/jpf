package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.MerShopRequest;
import com.joiest.jpf.dto.MerShopResponse;

public interface MerShopServiceFacade {

    public Integer getMerShopsCount();

    public MerShopResponse getMerShops(MerShopRequest merShopRequest);

    public JpfResponseDto delMerShop(Long id);
}
