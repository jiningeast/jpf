package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.custom.PayShopBargainOrderCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopBargainOrder;
import com.joiest.jpf.common.po.PayShopBargainOrderExample;
import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.po.PayShopCustomerExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopBargainOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBargainOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCustomerMapper;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainOrderResponse;
import com.joiest.jpf.entity.ShopBargainOrderInfo;
import com.joiest.jpf.facade.ShopBargainOrderServiceFacade;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
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
        String buy = "";
        String sale = "";
        for (PayShopBargainOrderCustom one : list) {
            ShopBargainOrderInfo info = new ShopBargainOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopBargainOrderCustom.class, ShopBargainOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            //卖家买家赋值
            if (info != null) {
                //买家
                if (info.getBuyerCustomerId() != null) {

                    PayShopCustomerExample em = new PayShopCustomerExample();
                    PayShopCustomerExample.Criteria cm = em.createCriteria();
                    cm.andIdEqualTo(info.getBuyerCustomerId());
                    List<PayShopCustomer> listCos = payShopCustomerMapper.selectByExample(em);
                    if (listCos != null && !listCos.isEmpty()) {
                        try {
                            sale = URLDecoder.decode(listCos.get(0).getNickname(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        info.setBuyName(sale);
                    }
                }
                if (info.getSellerCustomerId() != null) {
                    //卖家
                    PayShopCustomerExample emS = new PayShopCustomerExample();
                    PayShopCustomerExample.Criteria cmS = emS.createCriteria();
                    cmS.andIdEqualTo(info.getSellerCustomerId());
                    List<PayShopCustomer> listSale = payShopCustomerMapper.selectByExample(emS);
                    if (listSale != null && !listSale.isEmpty()) {

                        try {
                            buy = URLDecoder.decode(listSale.get(0).getNickname(), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        info.setSaleName(buy);
                    }
                }

            }
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
        if (shopBargainOrderInfo.getBuyerCustomerId() != null) {

            PayShopCustomerExample em = new PayShopCustomerExample();
            PayShopCustomerExample.Criteria cm = em.createCriteria();
            cm.andIdEqualTo(shopBargainOrderInfo.getBuyerCustomerId());
            List<PayShopCustomer> listCos = payShopCustomerMapper.selectByExample(em);
            if (listCos != null && !listCos.isEmpty()) {
                try {
                    sale = URLDecoder.decode(listCos.get(0).getNickname(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                shopBargainOrderInfo.setBuyName(sale);
            }
        }
        if (shopBargainOrderInfo.getSellerCustomerId() != null) {
            //卖家
            PayShopCustomerExample emS = new PayShopCustomerExample();
            PayShopCustomerExample.Criteria cmS = emS.createCriteria();
            cmS.andIdEqualTo(shopBargainOrderInfo.getSellerCustomerId());
            List<PayShopCustomer> listSale = payShopCustomerMapper.selectByExample(emS);
            if (listSale != null && !listSale.isEmpty()) {

                try {
                    buy = URLDecoder.decode(listSale.get(0).getNickname(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                shopBargainOrderInfo.setSaleName(buy);
            }
        }
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
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
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
    public int sellerPlaceOrder(ShopBargainOrderInfo orderInfo){

        PayShopBargainOrder payShopBargainOrder = new PayShopBargainOrder();

        BeanCopier beanCopier = BeanCopier.create(ShopBargainOrderInfo.class,PayShopBargainOrder.class,false);
        beanCopier.copy(orderInfo,payShopBargainOrder,null);

        return payShopBargainOrderMapper.insertSelective(payShopBargainOrder);
    }

}
