package com.joiest.jpf.facade;

import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.entity.ShopBatchInfo;

import java.util.List;

public interface ShopBatchServiceFacade {

    /**
     * 获取批量欣券批次
     */
    public List<ShopBatchInfo> getBatches(ShopBatchRequest shopBatchRequest);

}
