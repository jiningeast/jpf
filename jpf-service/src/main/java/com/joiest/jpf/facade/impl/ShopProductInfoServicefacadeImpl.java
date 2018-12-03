package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopProductInfo;
import com.joiest.jpf.common.po.PayShopProductInfoExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductInfoMapper;
import com.joiest.jpf.dto.GetShopProductInfoRequest;
import com.joiest.jpf.dto.GetShopProductInfoResponse;
import com.joiest.jpf.dto.ShopProductInfoRequest;
import com.joiest.jpf.entity.ShopProductInfo;
import com.joiest.jpf.entity.ShopProductInfoInfo;
import com.joiest.jpf.facade.ShopProductInfoServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author admin
 */
public class ShopProductInfoServicefacadeImpl implements ShopProductInfoServiceFacade {

    @Autowired
    private PayShopProductInfoMapper payShopProductInfoMapper;

    @Override
    public GetShopProductInfoResponse getRecords(GetShopProductInfoRequest request){

        PayShopProductInfoExample example = new PayShopProductInfoExample();
        PayShopProductInfoExample.Criteria c = example.createCriteria();
        example.setOrderByClause("supplier_id DESC");

        List<PayShopProductInfo> list = payShopProductInfoMapper.selectByExample(example);
        if( list == null || list.size() <= 0 ){
            return null;
        }
        int count = payShopProductInfoMapper.countByExample(example);

        List<ShopProductInfoInfo> infos = new ArrayList<>();
        for(PayShopProductInfo one:list){
            ShopProductInfoInfo shopProductInfoInfo = new ShopProductInfoInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopProductInfo.class,ShopProductInfoInfo.class,false);
            beanCopier.copy(one,shopProductInfoInfo,null);
            infos.add(shopProductInfoInfo);
        }
        GetShopProductInfoResponse response = new GetShopProductInfoResponse();
        response.setList(infos);
        response.setCount(count);

        return response;

    }

    @Override
    public ShopProductInfoInfo getOne(String id){

        PayShopProductInfo payShopProductInfo = payShopProductInfoMapper.selectByPrimaryKey(Integer.valueOf(id));
        if( payShopProductInfo == null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "为匹配到数据");
        }
        ShopProductInfoInfo shopProductInfoInfo = new ShopProductInfoInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopProductInfo.class,ShopProductInfoInfo.class,false);
        beanCopier.copy(payShopProductInfo,shopProductInfoInfo,null);

        return shopProductInfoInfo;
    }
    /**
     * 获取旅游服务商品列表
     * @param payShopSupplierId 供应商Id
     * @return
     */
    @Override
    public List<PayShopProductInfo> getProductInfoList(String payShopSupplierId) {
        PayShopProductInfoExample example = new PayShopProductInfoExample();
        example.createCriteria().andSupplierIdEqualTo(Integer.valueOf(payShopSupplierId)).andStatusEqualTo((byte)0);
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

    @Override
    public JpfResponseDto editShopProductInfo(ShopProductInfoRequest request) {

        PayShopProductInfo info = new PayShopProductInfo();

        info.setId(Integer.parseInt(request.getId()));
        info.setBrandId(Integer.parseInt(request.getBrandId()));
        info.setSupplierId(Integer.parseInt(request.getSupplierId()));
        info.setTypeId(Integer.parseInt(request.getTypeId()));
        info.setSupplierName(request.getSupplierName());
        info.setTypeName(request.getTypeName());
        info.setBrandName(request.getBrandName());
        //info.setStatus((byte)1);
        info.setUpdatetime(new Date());
        info.setContactEmail(request.getContactEmail());
        info.setContactName(request.getContactName());
        info.setContactPhone(request.getContactPhone());
        info.setTitle(request.getTitle());
        info.setImgurl(request.getImgurl());
        info.setMoneyscope(request.getMoneyscope());

        int res = payShopProductInfoMapper.updateByPrimaryKeySelective(info);

        return new JpfResponseDto();
    }


}
