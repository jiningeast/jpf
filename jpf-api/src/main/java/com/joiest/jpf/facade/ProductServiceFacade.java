package com.joiest.jpf.facade;

import com.joiest.jpf.dto.*;

public interface ProductServiceFacade {

    /**
     * 获取产品信息列表
     * @param request
     * @return
     */
    public GetProductResponse getProductList(GetProductRequest request);


}