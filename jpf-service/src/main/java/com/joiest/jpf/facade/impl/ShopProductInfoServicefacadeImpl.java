package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.generate.PayBankMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayMerchantsTypeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductInfoMapper;
import com.joiest.jpf.dto.ShopProductInfoRequest;
import com.joiest.jpf.entity.BankInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.facade.BankServiceFacade;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductInfoMapper;
import com.joiest.jpf.facade.ShopProductInfoServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @author admin
 */
public class ShopProductInfoServicefacadeImpl implements ShopProductInfoServiceFacade {

    @Autowired
    private PayShopProductInfoMapper payShopProductInfoMapper;

    /**
     * 获取旅游服务商品列表
     * @param payShopSupplierId 供应商Id
     * @return
     */
    @Override
    public List<PayShopProductInfo> getProductInfoList(String payShopSupplierId) {
        PayShopProductInfoExample example = new PayShopProductInfoExample();
        example.createCriteria().andSupplierIdEqualTo(Integer.valueOf(payShopSupplierId));
        List<PayShopProductInfo> payShopProductInfosList = payShopProductInfoMapper.selectByExample(example);
        return payShopProductInfosList;
    }

    /**
     * 获取旅游服务商品列表数据条数
     * @param decoderPayShopSupplierId 供应商Id
     * @return
     */
    @Override
    public int getProductInfoCount(String decoderPayShopSupplierId) {
        PayShopProductInfoExample example = new PayShopProductInfoExample();
        example.createCriteria().andSupplierIdEqualTo(Integer.valueOf(decoderPayShopSupplierId));
        return payShopProductInfoMapper.countByExample(example);
    }


}
