package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopAd;
import com.joiest.jpf.common.po.PayShopAdExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopAdMapper;
import com.joiest.jpf.dto.GetShopAdRequest;
import com.joiest.jpf.dto.GetShopAdResponse;
import com.joiest.jpf.entity.ShopAdInfo;
import com.joiest.jpf.facade.ShopAdServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopAdServiceFacadeImpl implements ShopAdServiceFacade {

    @Autowired
    private PayShopAdMapper payShopAdMapper;


    @Override
    public GetShopAdResponse getList(GetShopAdRequest GetShopAdRequest){
        GetShopAdResponse GetShopAdResponse = new GetShopAdResponse();

        PayShopAdExample e = new PayShopAdExample();
        PayShopAdExample.Criteria c = e.createCriteria();

        if ( StringUtils.isNotBlank(GetShopAdRequest.getTitle())  ){
            c.andTitleLike("%"+GetShopAdRequest.getTitle()+"%");
        }

        if ( GetShopAdRequest.getType() != null &&  StringUtils.isNotBlank(GetShopAdRequest.getType().toString()) ){
            c.andTypeEqualTo(GetShopAdRequest.getType());
        }

        // 添加时间搜索
        Date addtimeStart = new Date();
        if ( StringUtils.isNotBlank(GetShopAdRequest.getAddtimeStart()) ){
            addtimeStart = DateUtils.getFdate( GetShopAdRequest.getAddtimeStart(), DateUtils.DATEFORMATLONG );
            c.andAddtimeGreaterThan( addtimeStart );
        }
        Date addtimeEnd = new Date();
        if ( StringUtils.isNotBlank(GetShopAdRequest.getAddtimeEnd()) ){
            addtimeEnd = DateUtils.getFdate( GetShopAdRequest.getAddtimeEnd(), DateUtils.DATEFORMATLONG );
            c.andAddtimeLessThan( addtimeEnd );
        }

        e.setPageSize(GetShopAdRequest.getRows());
        e.setPageNo(GetShopAdRequest.getPage());
        e.setOrderByClause("weight DESC");

        List<PayShopAd> list = payShopAdMapper.selectByExample(e);
        GetShopAdResponse.setCount(payShopAdMapper.countByExample(e));
        List<ShopAdInfo> infos = new ArrayList<>();
        if ( !list.isEmpty() ){
            for (PayShopAd payShopAd:list){
                ShopAdInfo shopAdInfo = new ShopAdInfo();
                BeanCopier beanCopier = BeanCopier.create(PayShopAd.class, ShopAdInfo.class, false);
                beanCopier.copy(payShopAd, shopAdInfo, null);

                infos.add(shopAdInfo);
            }
        }

        GetShopAdResponse.setList(infos);

        return GetShopAdResponse;
    }

    /**
     * 添加或更新图片记录
     */
    @Override
    public JpfResponseDto addRecord(GetShopAdRequest request){

        Date date = new Date();
        PayShopAd record = new PayShopAd();
        record.setTitle(request.getTitle());
        record.setImgUrl(request.getImgUrl());
        record.setUrl(request.getUrl());
        if( request.getWeight() ==null || StringUtils.isBlank(request.getWeight().toString()) ){
            request.setWeight(0);
        }
        record.setWeight(request.getWeight());
        record.setType(request.getType());
        record.setStatus(request.getStatus());
        record.setUpdatetime(date);

        int count = 0;
        if(StringUtils.isNotBlank(request.getId()) &&  request.getId() != null ){ //更新操作
            record.setId(request.getId());
            count = payShopAdMapper.updateByPrimaryKeySelective(record);
        }else{ //添加
            record.setAddtime(date);
            count = payShopAdMapper.insertSelective(record);
        }

        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"操作失败");
        }
        return new JpfResponseDto();
    }

    /**
     * 添加或更新图片记录
     */
    @Override
    public PayShopAd getOneRecord(String id){
        if(StringUtils.isBlank(id)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"ID不能为空");
        }

        PayShopAd payShopAd = payShopAdMapper.selectByPrimaryKey(id);

        if( payShopAd==null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER,"未查询到数据");
        }
        return payShopAd;
    }

}
