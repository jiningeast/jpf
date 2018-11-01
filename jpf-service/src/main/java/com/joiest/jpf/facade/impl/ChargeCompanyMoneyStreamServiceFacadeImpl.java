package com.joiest.jpf.facade.impl;


import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStreamExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMoneyStreamMapper;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;
import com.joiest.jpf.entity.ChargeCompanyMoneyStreamInfo;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class ChargeCompanyMoneyStreamServiceFacadeImpl implements ChargeCompanyMoneyStreamServiceFacade {

    @Autowired
    private PayChargeCompanyMoneyStreamMapper payChargeCompanyMoneyStreamMapper;

    @Autowired

    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;
    /**
     * 获取充值订单
     */
    @Override
    public ChargeCompanyMoneyStreamResponse getRecords(ChargeCompanyMoneyStreamRequest request){
        ChargeCompanyMoneyStreamResponse response = new ChargeCompanyMoneyStreamResponse();

        PayChargeCompanyMoneyStreamExample e = new PayChargeCompanyMoneyStreamExample();
        PayChargeCompanyMoneyStreamExample.Criteria c = e.createCriteria();
        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        e.setOrderByClause("id DESC");
        // 添加时间搜索
        if (org.apache.commons.lang.StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (org.apache.commons.lang.StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        if ( StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }
        if ( StringUtils.isNotBlank(request.getMerchNo())){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        //交易类型
        if ( request.getStatus() !=null && StringUtils.isNotBlank(request.getStatus().toString())){
            c.andStatusEqualTo(request.getStatus());
        }
        //收支类型
        if ( StringUtils.isNotBlank(request.getStatusType())){
            List<Byte> statusArr = new ArrayList<>();
            if(request.getStatusType().equals("1")){ //入账
                statusArr.add((byte)1); //1=充值
                statusArr.add((byte)3);//3=退款
            }
            if(request.getStatusType().equals("2")){ //出账
                statusArr.add((byte)2);//2=下单
            }
            c.andStatusIn(statusArr);
        }

        
        List<ChargeCompanyMoneyStreamInfo> infos = new ArrayList<>();
        List<PayChargeCompanyMoneyStream> list = payChargeCompanyMoneyStreamMapper.selectByExample(e);
        int count = payChargeCompanyMoneyStreamMapper.countByExample(e);
        if ( list != null && !list.isEmpty() ){
            for ( PayChargeCompanyMoneyStream one:list ){
                ChargeCompanyMoneyStreamInfo chargeCompanyMoneyStreamInfo = new ChargeCompanyMoneyStreamInfo();
                BeanCopier beanCopier = BeanCopier.create(PayChargeCompanyMoneyStream.class,ChargeCompanyMoneyStreamInfo.class,false);
                beanCopier.copy(one,chargeCompanyMoneyStreamInfo,null);
                chargeCompanyMoneyStreamInfo.setStatusCn("");
                if( request.getStatusCnArr() !=null && request.getStatusCnArr().containsKey(chargeCompanyMoneyStreamInfo.getStatus())){
                    chargeCompanyMoneyStreamInfo.setStatusCn(request.getStatusCnArr().get(chargeCompanyMoneyStreamInfo.getStatus()));
                }
                chargeCompanyMoneyStreamInfo.setStatusType("");
                if( chargeCompanyMoneyStreamInfo.getStatus() == 1 || chargeCompanyMoneyStreamInfo.getStatus() == 2 ){
                    chargeCompanyMoneyStreamInfo.setStatusType("入账");
                }
                if( chargeCompanyMoneyStreamInfo.getStatus() == 3){
                    chargeCompanyMoneyStreamInfo.setStatusType("出账");
                }
                infos.add(chargeCompanyMoneyStreamInfo);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }


}
