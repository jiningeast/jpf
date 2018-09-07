package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopAd;
import com.joiest.jpf.dto.GetShopAdRequest;
import com.joiest.jpf.dto.GetShopAdResponse;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.dto.ShopBatchResponse;

public interface ShopAdServiceFacade {

    /**
     * 获取批量欣券批次
     */
    public GetShopAdResponse getList(GetShopAdRequest request);

    /**
     * 图片添加
     */
    public JpfResponseDto addRecord(GetShopAdRequest request);

    /**
     * 查询单条记录
     */
    public PayShopAd getOneRecord(String id);

}
