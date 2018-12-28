package com.joiest.jpf.facade.impl;

import com.alibaba.fastjson.JSONArray;
import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.custom.PayShopOrderCustomExample;
import com.joiest.jpf.common.custom.ShopIncomeConfirmationDetailResponse;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.*;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyChargeMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponActiveMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponRemainMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.ShopOrderCouponConsumeDetailResponse;
import com.joiest.jpf.facade.ShopIncomeConfirmationServiceFacade;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 欣豆市场确认收入订单服务接口
 * @author zhouchaowei 
 */
@Service("ShopIncomeConfirmationServiceFacade")
public class ShopIncomeConfirmationServiceFacadeImpl implements ShopIncomeConfirmationServiceFacade {
    
    private Logger logger = LoggerFactory.getLogger(ShopIncomeConfirmationServiceFacadeImpl.class);
    
    @Autowired
    private PayShopOrderCustomMapper payShopOrderCustomMapper;

    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;
    
    @Autowired
    private PayShopBatchMapper payShopBatchMapper;
    
    @Autowired
    private PayShopBatchCouponCustomMapper payShopBatchCouponCustomMapper;
    
    @Autowired
    private PayShopCouponOrderCustomMapper payShopCouponOrderCustomMapper;
    
    @Autowired
    private PayShopCompanyChargeMapper payShopCompanyChargeMapper;
    
    @Autowired
    private PayShopIncomeConfirmationMapper payShopIncomeConfirmationMapper;
    
    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;
    
    /**
     * 获取欣豆市场确认收入订单详情列表
     * @param orderNo 欣豆市场订单号
     * @return
     */
    @Override
    public ShopOrderCouponConsumeDetailResponse getOrderIncomeConfirmationDetail(String orderNo) {
        PayShopOrderCustomExample example = new PayShopOrderCustomExample();
        example.createCriteria().andOrderNoEqualTo(orderNo);
        PayShopOrderCustom payShopOrderCustom = payShopOrderCustomMapper.selectOrderAll(example);
        if(payShopOrderCustom == null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "此条记录不存在");
        }
        
        // 将取出来的订单的欣券消费详情做json解析
        String couponDetail = payShopOrderCustom.getCouponDetail();
        List<ShopIncomeConfirmationDetailResponse> shopIncomeConfirmationDetailResponseList = new ArrayList<>();
        
        if(StringUtils.isNotBlank(couponDetail)){
            List<CouponConsumeDetail> couponConsumeDetailList = (List<CouponConsumeDetail>)JSONArray.parseArray(couponDetail,CouponConsumeDetail.class);
            for (CouponConsumeDetail couponConsumeDetail : couponConsumeDetailList){
                ShopIncomeConfirmationDetailResponse shopIncomeConfirmationDetailResponse = new ShopIncomeConfirmationDetailResponse();
                
                BigDecimal deduct = couponConsumeDetail.getDeduct();
                String couponNo = couponConsumeDetail.getCouponNo();
                BigDecimal couponBanlance = null;
                BigDecimal merchCouponOrderBanlance = null;
                String companyName = "";
                String contractNo = "";
                String merchCouponOrderNo = "";
                BigDecimal rate = null;
                
                PayShopCouponRemainExample payShopCouponRemainExample = new PayShopCouponRemainExample();
                payShopCouponRemainExample.createCriteria().andCouponNoEqualTo(couponNo);
                List<PayShopCouponRemain> payShopCouponRemainList = payShopCouponRemainMapper.selectByExample(payShopCouponRemainExample);
                if(!CollectionUtils.isEmpty(payShopCouponRemainList)){
                    couponBanlance = payShopCouponRemainList.get(0).getCouponDouLeft().add(payShopCouponRemainList.get(0).getSaleDouLeft());
                }

                PayShopCouponActiveExample payShopCouponActiveExample = new PayShopCouponActiveExample();
                payShopCouponActiveExample.createCriteria().andOrderIdEqualTo(payShopOrderCustom.getId());
                List<PayShopCouponActive> payShopCouponActiveList = payShopCouponActiveMapper.selectByExample(payShopCouponActiveExample);
                // TODO 添加欣券商户订单内余额字段赋值
                
                PayShopBatchCouponExample payShopBatchCouponExample = new PayShopBatchCouponExample();
                payShopBatchCouponExample.createCriteria().andCouponNoEqualTo(couponNo);
                List<PayShopBatchCoupon> payShopBatchCouponList = payShopBatchCouponCustomMapper.selectByExample(payShopBatchCouponExample);
                if(!CollectionUtils.isEmpty(payShopBatchCouponList)){
                    companyName = payShopBatchCouponList.get(0).getCompanyName();
                    
                    PayShopBatch payShopBatch = payShopBatchMapper.selectByPrimaryKey(payShopBatchCouponList.get(0).getBatchId());
                    if(payShopBatch != null){
                        contractNo = payShopBatch.getContractNo();

                        PayShopCouponOrder payShopCouponOrder = payShopCouponOrderCustomMapper.selectByPrimaryKey(payShopBatch.getOrderId());
                        if(payShopCouponOrder != null){
                            merchCouponOrderNo = payShopCouponOrder.getOrderNo();
                        }

                        PayShopCompanyCharge payShopCompanyCharge = payShopCompanyChargeMapper.selectByPrimaryKey(payShopBatch.getCompanyChargeId());
                        if(payShopCompanyCharge != null){
                            rate = payShopCompanyCharge.getRate();
                        }
                    }
                }
                
                shopIncomeConfirmationDetailResponse.setDou(deduct);
                shopIncomeConfirmationDetailResponse.setCouponNo(couponNo);
                shopIncomeConfirmationDetailResponse.setCouponBanlance(couponBanlance);
                shopIncomeConfirmationDetailResponse.setContractNo(contractNo);
                shopIncomeConfirmationDetailResponse.setMerchCouponOrderNo(merchCouponOrderNo);
                shopIncomeConfirmationDetailResponse.setMerchCouponOrderBanlance(merchCouponOrderBanlance);
                shopIncomeConfirmationDetailResponse.setCompanyName(companyName);
                shopIncomeConfirmationDetailResponse.setRate(rate);
                shopIncomeConfirmationDetailResponseList.add(shopIncomeConfirmationDetailResponse);
            }
        }
        
        ShopOrderCouponConsumeDetailResponse response = new ShopOrderCouponConsumeDetailResponse();
        response.setCount(shopIncomeConfirmationDetailResponseList.size());
        response.setList(shopIncomeConfirmationDetailResponseList);
        return response;
    }

    /**
     * 查询所有订单及其对应的欣券详情记录
     * @param request
     * @return
     */
    @Override
    public List<PayShopOrderCustom> getShopOrderList(GetShopOrderRequest request) {
        PayShopOrderCustomExample payShopOrderCustomExample = new PayShopOrderCustomExample();
        PayShopOrderCustomExample.Criteria c =payShopOrderCustomExample.createCriteria();
        if(StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if(request.getStatus() != null && request.getStatus().toString() != ""){
            c.andStatusEqualTo(request.getStatus());
        }
        // 添加时间搜索
        if(StringUtils.isNotBlank(request.getAddtimeStart())){
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if(StringUtils.isNotBlank(request.getAddtimeEnd())){
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        // TODO 添加查询欣券订单内余额的业务逻辑 2018.12.27
        return payShopIncomeConfirmationMapper.selectShopOrderCouponDetailList(payShopOrderCustomExample);
    }

    /**
     * 欣券消费详情JSON转换内部实体类
     * @author zhouchaowei 
     */
    static class CouponConsumeDetail{
        
        /**
         * remain表的id字段
         */
        private String remainId;

        /**
         * 欣券id
         */
        private String couponId;

        /**
         * 欣券号
         */
        private String couponNo;
        
        /**
         * 本券剩余豆数量
         */
        private BigDecimal remainDou;

        /**
         * 此券扣减豆的数量
         */
        private BigDecimal deduct;

        public String getRemainId() {
            return remainId;
        }

        public void setRemainId(String remainId) {
            this.remainId = remainId;
        }

        public String getCouponId() {
            return couponId;
        }

        public void setCouponId(String couponId) {
            this.couponId = couponId;
        }

        public String getCouponNo() {
            return couponNo;
        }

        public void setCouponNo(String couponNo) {
            this.couponNo = couponNo;
        }

        public BigDecimal getRemainDou() {
            return remainDou;
        }

        public void setRemainDou(BigDecimal remainDou) {
            this.remainDou = remainDou;
        }

        public BigDecimal getDeduct() {
            return deduct;
        }

        public void setDeduct(BigDecimal deduct) {
            this.deduct = deduct;
        }
    }
}
