package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompanySalesInfo;

public interface CloudCompanySalesServiceFacade {

    /**
     * 根据商户号获取业务公司
     */
    public CloudCompanySalesInfo getSalesBySalesNo(String salesNo);
}
