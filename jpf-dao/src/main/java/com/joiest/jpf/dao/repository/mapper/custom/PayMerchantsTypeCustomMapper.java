package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayMerchantsTypeCustom;

import java.util.List;

public interface PayMerchantsTypeCustomMapper {

    List<PayMerchantsTypeCustom> findPayMerchantsTypeTree(int catId);

}
