package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantTypeInfo;

import java.util.List;

public interface MerTypeServiceFacade {

    public List<MerchantTypeInfo> getMerTypes(String pid);

}
