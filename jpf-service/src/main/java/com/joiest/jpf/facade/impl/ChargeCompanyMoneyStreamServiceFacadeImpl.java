package com.joiest.jpf.facade.impl;


import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStreamExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMoneyStreamMapper;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamInterfaceRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamRequest;
import com.joiest.jpf.dto.ChargeCompanyMoneyStreamResponse;
import com.joiest.jpf.entity.ChargeCompanyMoneyStreamInfo;
import com.joiest.jpf.facade.ChargeCompanyMoneyStreamServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
        if( request.getPage() > 0 && request.getRows() > 0 ){
            e.setPageNo(request.getPage());
            e.setPageSize(request.getRows());
        }
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
        if ( request.getStreamType() !=null && StringUtils.isNotBlank(request.getStreamType().toString())){
            c.andStreamTypeEqualTo(request.getStreamType());
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
                if( request.getStatusCnArr() !=null && request.getStatusCnArr().containsKey(chargeCompanyMoneyStreamInfo.getStatus().toString())){
                    chargeCompanyMoneyStreamInfo.setStatusCn(request.getStatusCnArr().get(chargeCompanyMoneyStreamInfo.getStatus().toString()));
                }
                chargeCompanyMoneyStreamInfo.setStreamReturn("");
                if( chargeCompanyMoneyStreamInfo.getStreamType() == 1 ){
                    chargeCompanyMoneyStreamInfo.setStreamReturn("支出");
                }else{
                    chargeCompanyMoneyStreamInfo.setStreamReturn("收入");
                }
                chargeCompanyMoneyStreamInfo.setIsDelCn("");
                if( chargeCompanyMoneyStreamInfo.getIsDel() == 0 ){
                    chargeCompanyMoneyStreamInfo.setIsDelCn("正常");
                }else{
                    chargeCompanyMoneyStreamInfo.setIsDelCn("删除");
                }
                infos.add(chargeCompanyMoneyStreamInfo);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

    /**
     * 记录资金流水日志
     */
    @Override
    public int create(PayChargeCompanyMoneyStream record){

        //记录资金流水日志
        BigDecimal zeroNum = new BigDecimal("0");
        Date curretDate = new Date();
        PayChargeCompanyMoneyStream streamData = new PayChargeCompanyMoneyStream();
        streamData.setStreamNo(record.getStreamNo());//流水号
        streamData.setCompanyId(record.getCompanyId());//商户id
        streamData.setCompanyName(record.getCompanyName());//商户名称
        streamData.setMerchNo(record.getMerchNo());//商户号
        streamData.setOrderId(record.getOrderId());//订单id 可能是消费订单、充值订单、退款订单
        streamData.setOrderNo(record.getOrderNo()); // 订单号可能是消费订单、充值订单、退款订单
        streamData.setProductId(record.getProductId());//产品Id
        streamData.setProductName(record.getProductName());//产品名称
        streamData.setProductValue(record.getProductValue()); //产品面值
        streamData.setProductBidPrice(record.getProductBidPrice());//产品成本价
        streamData.setProductSalePrice(record.getProductSalePrice());//产品标准售价 默认null
        streamData.setProductInterfacePrice(record.getProductInterfacePrice());//产品接口价
        streamData.setProductAmount(record.getProductAmount());//产品数量
        streamData.setTotalMoney(record.getTotalMoney());//总价
        streamData.setInterfaceType(record.getInterfaceType());//接口类型 0=欧非 1=威能 默认null
        streamData.setInterfaceOrderNo(record.getInterfaceOrderNo());//接口订单号 默认null
        streamData.setStatus(record.getStatus());//流水类型 1=充值 2=下单 3=退款
        streamData.setStreamType(record.getStreamType());//流水类型 0=收入 1=支出
        streamData.setNewMoney(record.getNewMoney());//变动后的余额
        streamData.setMemo(record.getMemo());//流水备注
        streamData.setIsDel(record.getIsDel());//删除标记 0=未删除 1=已删除
        streamData.setAddtime(curretDate);//添加时间
        streamData.setUpdatetime(curretDate);//更新时间

        int streamCount = payChargeCompanyMoneyStreamMapper.insertSelective(streamData);

        return streamCount;
    }

    /**
     * 新增流水
     */
    @Override
    public int insRecord(ChargeCompanyMoneyStreamInfo info){

        PayChargeCompanyMoneyStream payChargeCompanyMoneyStream = new PayChargeCompanyMoneyStream();

        BeanCopier beanCopier = BeanCopier.create(ChargeCompanyMoneyStreamInfo.class,PayChargeCompanyMoneyStream.class,false);
        beanCopier.copy(info,payChargeCompanyMoneyStream,null);

        return payChargeCompanyMoneyStreamMapper.insertSelective(payChargeCompanyMoneyStream);
    }

    /**
     * 接口获取流水列表
     */
    @Override
    public ChargeCompanyMoneyStreamResponse getStreamList(ChargeCompanyMoneyStreamInterfaceRequest request){


        if ( request.getPageSize() ==null || Long.parseLong(request.getPageSize()) <= 0 || Long.parseLong(request.getPageSize()) > 10 )
        {
            request.setPageSize("10");
        }else{
            request.setPageSize(request.getPageSize());
        }

        if ( request.getPage() ==null || Long.parseLong(request.getPage()) <= 0 )
        {
            request.setPage("1");
        }

        ChargeCompanyMoneyStreamResponse response = new ChargeCompanyMoneyStreamResponse();

        PayChargeCompanyMoneyStreamExample e = new PayChargeCompanyMoneyStreamExample();
        PayChargeCompanyMoneyStreamExample.Criteria c = e.createCriteria();
        e.setPageNo(Long.parseLong(request.getPage()));
        e.setPageSize(Long.parseLong(request.getPageSize()));
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

        if ( StringUtils.isNotBlank(request.getMerchNo())){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        //交易类型
        if ( request.getStatus() !=null && StringUtils.isNotBlank(request.getStatus())){
            c.andStatusEqualTo(Byte.valueOf(request.getStatus()));
        }
        //收支类型
        if ( request.getStreamType() !=null && StringUtils.isNotBlank(request.getStreamType())){
            c.andStreamNoEqualTo(request.getStreamType());
        }
        //订单号
        if(StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        //流水号
        if(StringUtils.isNotBlank(request.getStreamNo())){
            c.andStreamNoEqualTo(request.getStreamNo());
        }
        List<ChargeCompanyMoneyStreamInfo> infos = new ArrayList<>();
        List<PayChargeCompanyMoneyStream> list = payChargeCompanyMoneyStreamMapper.selectByExample(e);
        int count = payChargeCompanyMoneyStreamMapper.countByExample(e);
        if ( list != null && !list.isEmpty() ){

            for ( PayChargeCompanyMoneyStream one:list ){
                ChargeCompanyMoneyStreamInfo chargeCompanyMoneyStreamInfo = new ChargeCompanyMoneyStreamInfo();
                BeanCopier beanCopier = BeanCopier.create(PayChargeCompanyMoneyStream.class,ChargeCompanyMoneyStreamInfo.class,false);
                beanCopier.copy(one,chargeCompanyMoneyStreamInfo,null);
                if( chargeCompanyMoneyStreamInfo.getStreamType() == 1 ){
                    chargeCompanyMoneyStreamInfo.setStreamReturn("支出");
                }else{
                    chargeCompanyMoneyStreamInfo.setStreamReturn("收入");
                }
                if(chargeCompanyMoneyStreamInfo.getStatus()==1){
                   chargeCompanyMoneyStreamInfo.setStatusReturn("充值");
                }
                if(chargeCompanyMoneyStreamInfo.getStatus()==2){
                    chargeCompanyMoneyStreamInfo.setStatusReturn("下单");
                }
                if(chargeCompanyMoneyStreamInfo.getStatus()==3){
                    chargeCompanyMoneyStreamInfo.setStatusReturn("退款");
                }
                if ( chargeCompanyMoneyStreamInfo.getInterfaceType() != null ){
                    if(chargeCompanyMoneyStreamInfo.getInterfaceType()==1){
                        chargeCompanyMoneyStreamInfo.setInterfaceReturn("威能");
                    }else{
                        chargeCompanyMoneyStreamInfo.setInterfaceReturn("欧非");
                    }
                }else{
                    chargeCompanyMoneyStreamInfo.setInterfaceReturn("无");
                }

                infos.add(chargeCompanyMoneyStreamInfo);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

}
