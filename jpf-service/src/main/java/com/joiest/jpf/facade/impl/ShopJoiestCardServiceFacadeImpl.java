package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.custom.PayShopOrderCustomExample;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopProductCustomMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.facade.ShopJoiestCardServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 中欣卡订单业务Service实现类
 * @author admin
 */
@Service("shopJoiestCardServiceFacade")
public class ShopJoiestCardServiceFacadeImpl implements ShopJoiestCardServiceFacade {

    private static Logger logger = LoggerFactory.getLogger(ShopJoiestCardServiceFacadeImpl.class);

    @Autowired
    private PayShopOrderCustomMapper payShopOrderCustomMapper;

    @Autowired
    private PayShopProductCustomMapper payShopProductCustomMapper;

    /**
     * 获取中欣卡订单列表
     * @param request
     * @return
     */
    @Override
    public GetShopOrderResponse getJoiestCardList(GetShopOrderRequest request) {
        GetShopOrderResponse response = new GetShopOrderResponse();

        PayShopOrderCustomExample payShopOrderCustomExample = new PayShopOrderCustomExample();
        payShopOrderCustomExample.setPageNo(request.getPage());
        payShopOrderCustomExample.setPageSize(request.getRows());
        payShopOrderCustomExample.setOrderByClause("id DESC");

        PayShopOrderCustomExample.Criteria c = payShopOrderCustomExample.createCriteria();
        matchCriteria(request,c);
        
        List<PayShopOrderCustom> joiestCardShopOrderList = payShopOrderCustomMapper.selectJoiestCardProductId(payShopOrderCustomExample);
        List<ShopOrderInfo> shopOrderInfoList = new ArrayList<>();
        for (PayShopOrderCustom payShopOrderCustom : joiestCardShopOrderList){
            ShopOrderInfo shopOrderInfo = new ShopOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopOrderCustom.class, ShopOrderInfo.class, false);
            beanCopier.copy(payShopOrderCustom, shopOrderInfo, null);
            shopOrderInfoList.add(shopOrderInfo);
        }
        if(!CollectionUtils.isEmpty(joiestCardShopOrderList)){
            response.setList(shopOrderInfoList);
            response.setCount(joiestCardShopOrderList.size());
        }else{
            response.setList(null);
            response.setCount(0);
        }
        return response;
    }

    /**
     * 欣豆市场订单查询搜索条件匹配
     * @param request
     * @param c
     */
    private void matchCriteria(GetShopOrderRequest request,PayShopOrderCustomExample.Criteria c){
        if(StringUtils.isNotBlank(request.getProductName())){
            c.andProductNameLike("%" + request.getProductName() + "%" );
        }
        if(StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if(request.getStatus() != null && request.getStatus().toString() != ""){
            c.andStatusEqualTo(request.getStatus());
        }
        if(request.getSource() != null && request.getSource().toString() != ""){
            c.andSourceEqualTo(request.getSource());
        }
        // 添加时间搜索
        if(StringUtils.isNotBlank(request.getAddtimeStart())){
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if(StringUtils.isNotBlank(request.getAddtimeEnd())){
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        // 添加时间搜索
        if(StringUtils.isNotBlank(request.getPaytimeStart())){
            c.andPaytimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getPaytimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if(StringUtils.isNotBlank(request.getPaytimeEnd())){
            c.andPaytimeLessThanOrEqualTo(DateUtils.getFdate(request.getPaytimeEnd(),DateUtils.DATEFORMATLONG));
        }
    }

    /**
     * 中欣卡订单审核操作
     * 审核订单状态  0=未支付, 1=运营已审核, 2=财务打款中, 3=打款成功, 4=打款失败, 5=取消订单
     * @param request 请求实体
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public JpfResponseDto audit(PayShopOrder request){
        return null;
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
