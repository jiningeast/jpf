package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayShopBatch;
import com.joiest.jpf.common.po.PayShopBatchExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchMapper;
import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.entity.ShopBatchInfo;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ShopBatchServiceFacadeImpl implements ShopBatchServiceFacade {

    @Autowired
    private PayShopBatchMapper payShopBatchMapper;

    @Override
    public List<ShopBatchInfo> getBatches(ShopBatchRequest shopBatchRequest){
        PayShopBatchExample e = new PayShopBatchExample();
        PayShopBatchExample.Criteria c = e.createCriteria();
        if ( shopBatchRequest.getId() != null ){
            c.andIdEqualTo(shopBatchRequest.getId());
        }
        if ( shopBatchRequest.getBatchNo() != null ){
            c.andBatchNoEqualTo(shopBatchRequest.getBatchNo());
        }
        if ( shopBatchRequest.getCompanyName() != null ){
            c.andCompanyNameEqualTo(shopBatchRequest.getCompanyName());
        }
        if ( shopBatchRequest.getReceiveName() != null ){
            c.andReceiveNameEqualTo(shopBatchRequest.getReceiveName());
        }
        if ( shopBatchRequest.getSalesName() != null ){
            c.andSalesNameEqualTo(shopBatchRequest.getSalesName());
        }
        c.andStatusEqualTo(shopBatchRequest.getStatus());

        List<PayShopBatch> list = payShopBatchMapper.selectByExample(e);
        List<ShopBatchInfo> infos = new ArrayList<>();
        ShopBatchInfo shopBatchInfo = new ShopBatchInfo();
        for (PayShopBatch payShopBatch:list){
            BeanCopier beanCopier = BeanCopier.create(PayShopBatch.class, ShopBatchInfo.class, false);
            beanCopier.copy(payShopBatch, shopBatchInfo, null);

            infos.add(shopBatchInfo);
        }

        return infos;
    }
}
