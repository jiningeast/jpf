package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.GetShopBargainRequestRequest;
import com.joiest.jpf.dto.GetShopBargainRequestResponse;

public interface ShopBargainRequestServiceFacade {
    /**
     * 收购列表---后台
     */
    public GetShopBargainRequestResponse getList (GetShopBargainRequestRequest request);


    /**
     * 收购开启和关闭
     */
    public JpfResponseDto delShopBargain(String merchNo, int type);

}
