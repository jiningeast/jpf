package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopBargainOrderCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.ArithmeticUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBargainOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBargainOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBargainRechargeViewMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponActiveMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCustomerMapper;
import com.joiest.jpf.dto.GetCouponRemainResponse;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainOrderResponse;
import com.joiest.jpf.dto.GetShopCouponRemainResponse;
import com.joiest.jpf.entity.ShopBargainOrderInfo;
import com.joiest.jpf.entity.ShopCouponRemainInfo;
import com.joiest.jpf.entity.ShopCustomerInterfaceInfo;
import com.joiest.jpf.facade.ShopBargainOrderServiceFacade;
import com.joiest.jpf.facade.ShopCouponRemainServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopBargainOrderServiceFacadeImpl implements ShopBargainOrderServiceFacade {

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;

    @Autowired
    private PayShopBargainOrderCustomMapper payShopBargainOrderCustomMapper;

    @Autowired
    private PayShopBargainOrderMapper payShopBargainOrderMapper;

    @Autowired
    ShopCouponRemainServiceFacade shopCouponRemainServiceFacade;

    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;

    @Autowired

    private PayShopBargainRechargeViewMapper  payShopBargainRechargeViewMapper;
    /**
     * 运营采购订单列表---后台
     */
    @Override
    public GetShopBargainOrderResponse getList(GetShopBargainOrderRequest request) {
        if (request.getPage() <= 1) {
            request.setPage(1);
        }
        PayShopBargainOrderExample example = new PayShopBargainOrderExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("id DESC");

        PayShopBargainOrderExample.Criteria c = example.createCriteria();
        //查询搜索条件数据
        if (request.getStatus() != null) {
            c.andStatusEqualTo(request.getStatus()); //查询搜索状态值数据
        } else {
            if (request.getStatusArr() != null) {
                c.andStatusIn(request.getStatusArr()); //查询指定状态值数据
            }
        }
        if (StringUtils.isNotBlank(request.getOrderNo())) {
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        if (StringUtils.isNotBlank(request.getRealName())) {
            c.andRealNameLike("%" + request.getRealName() + "%");
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart())) {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(), DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd())) {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(), DateUtils.DATEFORMATLONG));
        }

        List<PayShopBargainOrderCustom> list = payShopBargainOrderCustomMapper.selectJoin(example);

        List<ShopBargainOrderInfo> infoList = new ArrayList<>();

        for (PayShopBargainOrderCustom one : list) {
            String buy = "";
            String sale = "";
            ShopBargainOrderInfo info = new ShopBargainOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainOrderCustom.class, ShopBargainOrderInfo.class, false);
            beanCopier.copy(one, info, null);

            try {
                if(StringUtils.isNotBlank(info.getBuyerCustomerNickname()))
                    sale = URLDecoder.decode(info.getBuyerCustomerNickname(), "UTF-8");

                if(StringUtils.isNotBlank(info.getSellerCustomerNickname())&& info.getSellerCustomerNickname()!=null)
                    buy = URLDecoder.decode(info.getSellerCustomerNickname(), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            info.setSellerCustomerNickname(buy);
            info.setBuyerCustomerNickname(sale);
            infoList.add(info);
        }
        GetShopBargainOrderResponse response = new GetShopBargainOrderResponse();
        response.setList(infoList);
        int count = payShopBargainOrderCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }


    /**
     * 收购订单详情---后台
     */
    @Override
    public ShopBargainOrderInfo getOne(String orderNo) {
        if (StringUtils.isBlank(orderNo)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "订单编号不能为空");
        }
        PayShopBargainOrderExample exampleall = new PayShopBargainOrderExample();
        PayShopBargainOrderExample.Criteria cll = exampleall.createCriteria();
        cll.andOrderNoEqualTo(orderNo);
        List<PayShopBargainOrder> listOrder = payShopBargainOrderMapper.selectByExample(exampleall);

        if (listOrder.size() <= 0) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到数据");
        }
        ShopBargainOrderInfo shopBargainOrderInfo = new ShopBargainOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopBargainOrder.class, ShopBargainOrderInfo.class, false);
        beanCopier.copy(listOrder.get(0), shopBargainOrderInfo, null);
        String buy = "";
        String sale = "";
        //查询出卖家买家信息
        try {
            if(StringUtils.isNotBlank(shopBargainOrderInfo.getBuyerCustomerNickname()))
                sale = URLDecoder.decode(shopBargainOrderInfo.getBuyerCustomerNickname(), "UTF-8");

            if(StringUtils.isNotBlank(shopBargainOrderInfo.getSellerCustomerNickname()))
                buy = URLDecoder.decode(shopBargainOrderInfo.getSellerCustomerNickname(), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        shopBargainOrderInfo.setSellerCustomerNickname(buy);
        shopBargainOrderInfo.setBuyerCustomerNickname(sale);

        return shopBargainOrderInfo;
    }

    /*
     * 审核收购订单状态
     * */
    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})//事务处理
    public JpfResponseDto AuditOrder(GetShopBargainOrderRequest request) throws Exception {

        String infoId = request.getId();
        String memo = request.getMemo();
        Byte infoStatus = request.getStatus();
        if (infoId == null || org.apache.commons.lang3.StringUtils.isBlank(request.getId())) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayShopBargainOrder payShopBargainOrder = payShopBargainOrderMapper.selectByPrimaryKey(infoId);
        if (payShopBargainOrder == null) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "获取订单信息失败");
        }
        ShopBargainOrderInfo shopBargainOrderInfo = new ShopBargainOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopBargainOrder.class, ShopBargainOrderInfo.class, false);
        beanCopier.copy(payShopBargainOrder,shopBargainOrderInfo, null);
        PayShopCustomer  payShopCustomer   =payShopCustomerMapper.selectByPrimaryKey(payShopBargainOrder.getSellerCustomerId());
        if(payShopCustomer==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "获取卖家信息失败");
        }
        //审核判断
        int res = 0;
        //根据状态判断更新
        PayShopBargainOrder recordData = new PayShopBargainOrder();
        Date date=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        switch (request.getStatus()) {

            case 1://审核操作
                if (payShopBargainOrder.getStatus() == (byte) 1) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为审核状态");
                }else if(payShopBargainOrder.getStatus() == (byte) 5){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前订单已取消不可审核");
                } else {
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);
                    if (request.getMemo() != null && org.apache.commons.lang3.StringUtils.isNotBlank(request.getMemo())) {

                        recordData.setMemo( request.getMemo()+"&#13;&#10;[" + df.format(new Date()) + "]");
                    } else {
                        recordData.setMemo("[" + df.format(new Date()) + "] ");

                    }
                    //设置运营操作人
                    recordData.setOperatorId(request.getOperatorId());
                    recordData.setOperatorName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    res = payShopBargainOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                }
                break;
            case 2://打款中
                if (payShopBargainOrder.getStatus() == (byte) 2) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为打款中状态");
                } else {
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);
                    if (request.getMemo() != null && org.apache.commons.lang3.StringUtils.isNotBlank(request.getMemo())) {

                        recordData.setMemo( request.getMemo()+"&#13;&#10;[" + df.format(new Date()) + "]");
                    } else {
                        recordData.setMemo("[" + df.format(new Date()) + "] ");

                    }
                    recordData.setOperatorId(request.getOperatorId());
                    recordData.setOperatorName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    res = payShopBargainOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                }

                break;
            case 3://打款成功
                 if (payShopBargainOrder.getStatus() == (byte) 3) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为打款成功状态");
                } else if (payShopBargainOrder.getStatus() == (byte) 5) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前订单已取消");
                } else {
                     if(StringUtils.isBlank(request.getPayImg())){
                         throw new JpfException(JpfErrorInfo.DAL_ERROR, "请上传付款凭证");
                     }
                     //卖家金额校验
                     String custemID=payShopCustomer.getId();
                     String code=payShopCustomer.getCode();
                     String dou=payShopCustomer.getDou().toString();
                     Boolean isYes= ToolUtils.ValidateCode(code,custemID,dou);
                     if(!isYes){
                         throw new JpfException(JpfErrorInfo.DAL_ERROR, "卖家金额校验失败");
                     }
                     //拼出扣豆卖家的参数
                     ShopCustomerInterfaceInfo shopCustomerInterfaceInfo = new ShopCustomerInterfaceInfo();
                     BeanCopier beanCopierCutem = BeanCopier.create(PayShopCustomer.class, ShopCustomerInterfaceInfo.class, false);
                     beanCopierCutem.copy(payShopCustomer,shopCustomerInterfaceInfo, null);
                     //用户可用券列表
                    /* GetCouponRemainResponse userCouponList = shopCouponRemainServiceFacade.getCouponRemainByUidForInterface(custemID);
                     if ( userCouponList == null || userCouponList.getCount() == 0)
                     {
                         throw new JpfException(JpfErrorInfo.DAL_ERROR, "卖家无可用欣豆");
                     }*/
                     //欣券转让改版
                     GetShopCouponRemainResponse remainResponseAfter=shopCouponRemainServiceFacade.getSum(custemID);
                     List<ShopCouponRemainInfo> saleYes=remainResponseAfter.getSaleYes();//可转让欣券列表
                     BigDecimal saleYesSum=remainResponseAfter.getSaleYesSum();//可转让总额
                     if(saleYesSum.compareTo(new BigDecimal("0.00"))==0 || saleYes.size()<0){
                         throw new JpfException(JpfErrorInfo.DAL_ERROR, "卖家无可用欣豆");
                     }
                     //执行扣豆操作、
                    shopCouponRemainServiceFacade.CouponAttorn(saleYes, shopBargainOrderInfo, shopCustomerInterfaceInfo);

                    recordData.setPayImg(request.getPayImg());
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);

                    if (request.getMemo() != null && org.apache.commons.lang3.StringUtils.isNotBlank(request.getMemo())) {
                        recordData.setMemo( request.getMemo()+"&#13;&#10;[" + df.format(new Date()) + "]");
                    } else {
                        recordData.setMemo("[" + df.format(new Date()) + "] ");
                    }
                    recordData.setFinanceId(request.getOperatorId());
                    recordData.setFinanceName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    res = payShopBargainOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新

                    //服务转让表中插入数据
                     PayShopBargainRechargeView payShopBargainRechargeView=new PayShopBargainRechargeView();
                     payShopBargainRechargeView.setBoid(Long.valueOf(payShopBargainOrder.getId()));
                     payShopBargainRechargeView.setOrderNo(payShopBargainOrder.getOrderNo());
                     payShopBargainRechargeView.setStatus(1);
                     payShopBargainRechargeView.setTransferPrice(payShopBargainOrder.getDou());
                     payShopBargainRechargeView.setAlreadyPrice(new BigDecimal("0.00"));
                     payShopBargainRechargeView.setRemainPrice(payShopBargainOrder.getDou());
                     payShopBargainRechargeView.setAddtime(new Date());
                     payShopBargainRechargeViewMapper.insertSelective(payShopBargainRechargeView);

                }
                break;
            case 4:
                //打款失败
                if (payShopBargainOrder.getStatus() == (byte) 4) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为打款失败状态");
                } else {
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);
                    if (request.getMemo() != null && org.apache.commons.lang3.StringUtils.isNotBlank(request.getMemo())) {

                        recordData.setMemo( request.getMemo()+"&#13;&#10;[" + df.format(new Date()) + "]");
                    } else {
                        recordData.setMemo("[" + df.format(new Date()) + "] ");

                    }
                    recordData.setOperatorId(request.getOperatorId());
                    recordData.setOperatorName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    res = payShopBargainOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                }
                break;
            case 5://取消

                if (payShopBargainOrder.getStatus() == (byte) 5) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为取消状态");
                } else if (payShopBargainOrder.getStatus() == (byte) 3) {
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前订单已打款成功不可取消");
                }else {
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);
                    if (request.getMemo() != null && org.apache.commons.lang3.StringUtils.isNotBlank(request.getMemo())) {

                        recordData.setMemo( request.getMemo()+"&#13;&#10;[" + df.format(new Date()) + "]");
                    } else {
                        recordData.setMemo("[" + df.format(new Date()) + "] ");

                    }
                    //取消转让修改豆
                    // 客户总豆数量加上一部分pay_shop_customer
                    PayShopCustomer payShopCustomerUpdate = new PayShopCustomer();
                    BigDecimal countDou=new BigDecimal(ArithmeticUtils.add(payShopCustomer.getDou().toString(),payShopBargainOrder.getDou().toString(),2));
                    //int countDou=payShopCustomer.getDou()+ payShopBargainOrder.getDou();
                    BigDecimal dou =new BigDecimal(ArithmeticUtils.add(payShopCustomer.getSaleDou().toString(),payShopBargainOrder.getDou().toString(),2));
                    //int dou = payShopCustomer.getSaleDou() + payShopBargainOrder.getDou();
                    String code = ToolUtils.CreateCode(String.valueOf(countDou),payShopBargainOrder.getSellerCustomerId());
                    payShopCustomerUpdate.setId(payShopBargainOrder.getSellerCustomerId());
                    payShopCustomerUpdate.setDou(countDou);
                    payShopCustomerUpdate.setSaleDou(dou);
                    //用户总豆增加
                    BigDecimal freeDou=new BigDecimal(ArithmeticUtils.sub(payShopCustomer.getFreezeDou().toString(),payShopBargainOrder.getDou().toString(),2));
                    payShopCustomerUpdate.setFreezeDou(freeDou);
                    payShopCustomerUpdate.setCode(code);
                    payShopCustomerUpdate.setUpdatetime(new Date());
                    int res_updateCustomCode = payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomerUpdate);


                    if ( res_updateCustomCode < 1 )
                    {
                        throw new Exception("退还豆失败");
                    }
                    //active 表中添加一条取消的数据
                    PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
                    payShopCouponActive.setCustomerId(payShopBargainOrder.getSellerCustomerId());
                    //payShopCouponActive.setCustomerName(orderInfo.getCustomerName());
                    payShopCouponActive.setCompanyId(Integer.parseInt("0"));
                    //payShopCouponActive.setCompanyName(payShopCompany.getCompanyName());
                    //payShopCouponActive.setBatchId(Integer.parseInt(payShopBatch.getId()));
                    //payShopCouponActive.setBatchNo(payShopBatch.getBatchNo());
                    payShopCouponActive.setCouponNo("");
                    payShopCouponActive.setActiveCode("");
                    payShopCouponActive.setPayWay((byte)0);
                    payShopCouponActive.setMoney(new BigDecimal("0"));
                    payShopCouponActive.setDou(payShopBargainOrder.getDou());     //消费豆数量
                    payShopCouponActive.setContent("行为:取消;用户ID:" + payShopCustomer.getId() + ";用户名称:" + payShopCustomer.getNickname() + ";取消转让:" + payShopBargainOrder.getDou() + ";bargain_order_id:" + payShopBargainOrder.getId());
                    payShopCouponActive.setType("6");
                    payShopCouponActive.setExpireTime(new Date());
                    payShopCouponActive.setAddtime(new Date());
                    payShopCouponActive.setBargainOrderId(payShopBargainOrder.getId());
                    payShopCouponActive.setBargainOrderNo(payShopBargainOrder.getOrderNo());
                    int res_couponActive = payShopCouponActiveMapper.insertSelective(payShopCouponActive);
                    if ( res_couponActive < 1 )
                    {
                        throw new Exception("添加券记录失败");
                    }
                  // === ==end======
                    recordData.setOperatorId(request.getOperatorId());
                    recordData.setOperatorName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    res = payShopBargainOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                }
                break;
            default:
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态");
        }
        if (res != 1) {
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }
        return new JpfResponseDto();
    }

    /**
     *卖家转让下单
     * */
    @Override
    public int sellerPlaceOrder(ShopBargainOrderInfo orderInfo){

        PayShopBargainOrder payShopBargainOrder = new PayShopBargainOrder();

        BeanCopier beanCopier = BeanCopier.create(ShopBargainOrderInfo.class,PayShopBargainOrder.class,false);
        beanCopier.copy(orderInfo,payShopBargainOrder,null);

        //return payShopBargainOrderMapper.insertSelective(payShopBargainOrder);
        Integer id =  payShopBargainOrderCustomMapper.insertSelective(payShopBargainOrder);

        return Integer.valueOf(payShopBargainOrder.getId());

    }

    /**
     * 前台转让订单列表页
     */
    @Override
    public GetShopBargainOrderResponse getFrontList(GetShopBargainOrderRequest request) {
        if (request.getPage() <= 1) {
            request.setPage(1);
        }
        PayShopBargainOrderExample example = new PayShopBargainOrderExample();
        example.setPageNo(request.getPage());
        //example.setPageSize(request.getRows());
        example.setOrderByClause("id DESC");

        PayShopBargainOrderExample.Criteria c = example.createCriteria();
        //查询搜索条件数据
        // 1=我转让的
        if (request.getBuyerCustomerId() != null ) {
            c.andBuyerCustomerIdEqualTo(request.getBuyerCustomerId());
        }
        if( request.getSellerCustomerId() != null ){
            c.andSellerCustomerIdEqualTo(request.getSellerCustomerId());
        }

        List<PayShopBargainOrder> list = payShopBargainOrderMapper.selectByExample(example);

        List<ShopBargainOrderInfo> infoList = new ArrayList<>();

        for (PayShopBargainOrder one : list) {
            ShopBargainOrderInfo info = new ShopBargainOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainOrder.class, ShopBargainOrderInfo.class, false);
            beanCopier.copy(one, info, null);

            infoList.add(info);
        }
        GetShopBargainOrderResponse response = new GetShopBargainOrderResponse();
        response.setList(infoList);
        int count = payShopBargainOrderCustomMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 获取订单详情
     * */
    @Override
    public ShopBargainOrderInfo getBargainOrderByNo(String orderNo){

        PayShopBargainOrderExample example = new PayShopBargainOrderExample();
        PayShopBargainOrderExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(orderNo);

        List<PayShopBargainOrder> getPayShopBargainOrder = payShopBargainOrderMapper.selectByExample(example);

        if(getPayShopBargainOrder.isEmpty()) return null;

        PayShopBargainOrder payShopBargainOrder = getPayShopBargainOrder.get(0);

        ShopBargainOrderInfo shopBargainOrderInfo = new ShopBargainOrderInfo();

        BeanCopier beanCopier = BeanCopier.create(PayShopBargainOrder.class,ShopBargainOrderInfo.class,false);
        beanCopier.copy(payShopBargainOrder,shopBargainOrderInfo,null);

        return shopBargainOrderInfo;
    }

    /**
     * 获取
     * */
    @Override
    public int getBargainOrderCountByCustomId(String customId,String type){

        if(StringUtils.isBlank(type)) return 0;

        PayShopBargainOrderExample example = new PayShopBargainOrderExample();
        PayShopBargainOrderExample.Criteria c = example.createCriteria();

        if(type.equals("1"))
            c.andSellerCustomerIdEqualTo(customId);

        if(type.equals("2"))
            c.andBuyerCustomerIdEqualTo(customId);

        int count = payShopBargainOrderMapper.countByExample(example);

        return count;

    }
}
