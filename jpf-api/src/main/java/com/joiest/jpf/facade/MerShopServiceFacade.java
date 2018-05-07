package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantShopInfo;

import java.util.List;

public interface MerShopServiceFacade {

    public Integer getMerShopsCount();

    public List<MerchantShopInfo> getMerShops(long page, long rows);
}
