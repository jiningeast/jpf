package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.common.po.PayShopCouponOrder;
import com.joiest.jpf.common.po.PayShopCouponOrderInfo;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.CouponOrderList;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.facade.PayShopCouponOrderServiceFacade;
import com.joiest.jpf.facade.ShopCompanyChargeServiceFacade;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: admin
 * @Date: 2018/11/28 14:58
 * @Description:
 */
@Controller
@RequestMapping("/market-manager/shopBatchController")
public class ShopBatchController {

    @Autowired
    private ShopCompanyChargeServiceFacade shopCompanyChargeServiceFacade;
    @Autowired
    private ShopCompanyServiceFacade shopCompanyServiceFacade;
    @Autowired
    private PayShopCouponOrderServiceFacade payShopCouponOrderServiceFacade;

    /**
     * 查询可用的订单号
     * @param request
     * @return
     */
    @RequestMapping(value = "/getUsableBatchNo",method = RequestMethod.POST)
    @ResponseBody
    public String getUsableBatchNo(HttpServletRequest request){

        String companyId = request.getParameter("companyId");
        if(StringUtils.isBlank(companyId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            List<PayShopCompanyCharge> payShopCompanyCharges = shopCompanyChargeServiceFacade.getListByCompanyId(Base64CustomUtils.base64Decoder(companyId));
            for (PayShopCompanyCharge payShopCompanyCharge:payShopCompanyCharges) {
                Map<String,Object> map =new ConcurrentHashMap<>();
                map.put("id",payShopCompanyCharge.getId());
                map.put("contractNo",payShopCompanyCharge.getContractNo());
                map.put("balance",payShopCompanyCharge.getBalance());
                map.put("rate",payShopCompanyCharge.getRate());
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }

        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),list);
    }

    /**
     * 欣享爱生活下单接口
     * @return
     */
    @RequestMapping(value = "/payCouponOrder",method = RequestMethod.POST)
    @ResponseBody
    public String payCouponOrder(HttpServletRequest request){
        //返回的参数
        String couponOrder = request.getParameter("couponOrderInfo");
        if(StringUtils.isBlank(couponOrder)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        //转化成实体信息 判断参数是否合法
        CouponOrderList couponOrderList = JsonUtils.toObject(Base64CustomUtils.base64Decoder(couponOrder), CouponOrderList.class);
        if(StringUtils.isBlank(couponOrderList.getCompanyId())||StringUtils.isBlank(couponOrderList.getContractNo())
                ||StringUtils.isBlank(couponOrderList.getTotalMoney())||StringUtils.isBlank(couponOrderList.getTotalNum())
                ||(couponOrderList.getCouponList()==null||couponOrderList.getCouponList().size()==0)||StringUtils.isBlank(couponOrderList.getContractId())){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ABNORMAL_STATUS.getCode(),JpfInterfaceErrorInfo.ABNORMAL_STATUS.getDesc(),null);
        }

        //查询合同的余额够不够
        PayShopCompanyCharge payShopCompanyCharge = shopCompanyChargeServiceFacade.getById(couponOrderList.getContractId());
        if(payShopCompanyCharge==null){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }

        if(payShopCompanyCharge.getBalance().compareTo(new BigDecimal(couponOrderList.getTotalMoney()))<0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.CONTRACT_SUFFICIENT.getCode(),JpfInterfaceErrorInfo.CONTRACT_SUFFICIENT.getDesc(),null);
        }
        ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(couponOrderList.getCompanyId());
        if ( shopCompanyInfo.getStatus() != 1 ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ABNORMAL_STATUS.getCode(),JpfInterfaceErrorInfo.ABNORMAL_STATUS.getDesc(),null);
        }

        if(shopCompanyInfo.getMoney().compareTo(new BigDecimal(couponOrderList.getTotalMoney()))<0){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getCode(),JpfInterfaceErrorInfo.USER_DOU_NOT_SUFFICIENT.getDesc(),null);
        }

        try {
            payShopCouponOrderServiceFacade.saveCouponOrder(couponOrderList);
        } catch (Exception e) {
            e.printStackTrace();
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),null);
    }

    /**
     * 获取订单的接口
     * @return
     */
    @RequestMapping(value = "/getOrderList",method = RequestMethod.POST)
    @ResponseBody
    public String getOrderList(HttpServletRequest request){
        String companyId = request.getParameter("companyId");
        String pageNo = request.getParameter("pageNo");
        String pageSize = request.getParameter("pageSize");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        Map<String,Object> map =new ConcurrentHashMap<>();
        if(StringUtils.isNotBlank(startTime)&&StringUtils.isNotBlank(endTime)){
            map.put("startTime",Base64CustomUtils.base64Decoder(startTime));
            map.put("endTime",Base64CustomUtils.base64Decoder(endTime));
        }
        if(StringUtils.isBlank(pageNo)||StringUtils.isBlank(pageSize)&&StringUtils.isBlank(companyId)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }
        map.put("companyId",Base64CustomUtils.base64Decoder(companyId));
        map.put("pageNo",Base64CustomUtils.base64Decoder(pageNo));
        map.put("pageSize",Base64CustomUtils.base64Decoder(pageSize));
        List<PayShopCouponOrder> orderList;
        try {
           orderList = payShopCouponOrderServiceFacade.getOrderList(map);
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),orderList);
    }

    /**
     * 根据订单号查询订单详情
     * @param request
     * @return
     */
    @RequestMapping(value = "/getOrderInfo",method = RequestMethod.POST)
    @ResponseBody
    public String getOrderInfo(HttpServletRequest request){
        String orderNo = request.getParameter("orderNo");
        if(StringUtils.isBlank(orderNo)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(),JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(),null);
        }

        List<PayShopCouponOrderInfo> payShopCouponOrderInfos = null;
        try {
            payShopCouponOrderInfos = payShopCouponOrderServiceFacade.getOrderInfo(Base64CustomUtils.base64Decoder(orderNo));
        } catch (Exception e) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(),JpfInterfaceErrorInfo.FAIL.getDesc(),null);
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(),JpfInterfaceErrorInfo.SUCCESS.getDesc(),payShopCouponOrderInfos);
    }

    /**
     * 取消订单
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancalOrder",method = RequestMethod.POST)
    public String cancalOrder(HttpServletRequest request) {
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(orderId)) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(), JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(), null);
        }
        //查询订单。查看订单状态是不是0  0只是已经申请，但是还没有匹配券。可以做取消操作
        PayShopCouponOrder payShopCouponOrder = payShopCouponOrderServiceFacade.getByOrderId(Base64CustomUtils.base64Decoder(orderId));
        if (payShopCouponOrder != null && payShopCouponOrder.getStatus() != 0) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ORDER_STATUS_ERROR.getCode(), JpfInterfaceErrorInfo.ORDER_STATUS_ERROR.getDesc(), null);
        } else {
            try {
                payShopCouponOrderServiceFacade.cancalOrder(payShopCouponOrder);
            } catch (Exception e) {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), null);
            }
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), null);
    }


    @RequestMapping(value = "/deleteOrder",method = RequestMethod.POST)
    @ResponseBody
    public String deleteOrder(HttpServletRequest request){
        String orderId = request.getParameter("orderId");
        if (StringUtils.isBlank(orderId)) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.PARAMNOTNULL.getCode(), JpfInterfaceErrorInfo.PARAMNOTNULL.getDesc(), null);
        }
        //查询订单。查看订单状态是不是2  2是已经取消的订单能够做删除操作
        PayShopCouponOrder payShopCouponOrder = payShopCouponOrderServiceFacade.getByOrderId(Base64CustomUtils.base64Decoder(orderId));
        if (payShopCouponOrder != null && payShopCouponOrder.getStatus() != 2) {
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.ORDER_STATUS_CANCAL.getCode(), JpfInterfaceErrorInfo.ORDER_STATUS_CANCAL.getDesc(), null);
        } else {
            try {
                payShopCouponOrderServiceFacade.deleteOrder(payShopCouponOrder);
            } catch (Exception e) {
                return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.FAIL.getCode(), JpfInterfaceErrorInfo.FAIL.getDesc(), null);
            }
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), null);
    }

}
