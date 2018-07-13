package com.joiest.jpf.facade;

import com.joiest.jpf.entity.MerchantTypeInfo;
import com.joiest.jpf.entity.MerchantTypeTree;

import java.util.List;

public interface MerTypeServiceFacade {

    /**
     * 获取类型信息
     * @param pid
     * @return
     */
    public List<MerchantTypeInfo> getMerTypes(String pid);

    /**
     * 获取单个信息
     * @param catid
     */
    public MerchantTypeInfo getOneTypeByCatid(String catid);

    public List<MerchantTypeInfo> getOneTypeByCatId(String catid);

    public List<MerchantTypeTree> getMerchantTypeTree(int catId);

}
