package com.joiest.jpf.facade.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.Base64CustomUtils;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopProductMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopStockOrderProductMapper;
import com.joiest.jpf.dto.GetShopOrderRequest;
import com.joiest.jpf.dto.GetShopOrderResponse;
import com.joiest.jpf.dto.GetShopStockOrderRequest;
import com.joiest.jpf.dto.GetShopStockOrderResponse;
import com.joiest.jpf.entity.ShopOrderInfo;
import com.joiest.jpf.entity.ShopStockOrderInfo;
import com.joiest.jpf.facade.ShopOrderServiceFacade;
import com.joiest.jpf.facade.ShopStockOrderServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShopStockOrderServiceFacadeImpl implements ShopStockOrderServiceFacade {

    @Autowired
    private PayShopStockOrderMapper payShopStockOrderMapper;

    @Autowired
    private PayShopProductMapper payShopProductMapper;

    @Autowired
    private PayShopStockOrderProductMapper payShopStockOrderProductMapper;

    /**
     * 运营采购订单列表---后台
     */
    @Override
    public GetShopStockOrderResponse getList(GetShopStockOrderRequest request)
    {
        if( request.getPage() <= 1){
            request.setPage(Long.parseLong("1"));
        }
        PayShopStockOrderExample example = new PayShopStockOrderExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("id DESC");

        PayShopStockOrderExample.Criteria c =example.createCriteria();
        //查询搜索条件数据
        if( request.getStatus() != null ){
            c.andStatusEqualTo(request.getStatus()); //查询搜索状态值数据
        }else{
            if( request.getStatusArr() != null ){
                c.andStatusIn(request.getStatusArr()); //查询指定状态值数据
            }
        }
        if(StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }

        List<PayShopStockOrder> list = payShopStockOrderMapper.selectByExample(example);

        List<ShopStockOrderInfo> infoList = new ArrayList<>();

        for (PayShopStockOrder one : list)
        {
            ShopStockOrderInfo info = new ShopStockOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopStockOrder.class, ShopStockOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopStockOrderResponse response = new GetShopStockOrderResponse();
        response.setList(infoList);
        int count = payShopStockOrderMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 财务采购订单列表---后台
     */
    @Override
    public GetShopStockOrderResponse getListCaiwu(GetShopStockOrderRequest request)
    {
        if( request.getPage() <= 1){
            request.setPage(Long.parseLong("1"));
        }
        PayShopStockOrderExample example = new PayShopStockOrderExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("id DESC");

        PayShopStockOrderExample.Criteria c =example.createCriteria();
        //查询搜索条件数据
        if( request.getStatus() != null ){
            c.andStatusEqualTo(request.getStatus()); //查询搜索状态值数据
        }else{
            if( request.getStatusArr() != null ){
                c.andStatusIn(request.getStatusArr()); //查询指定状态值数据
            }
        }
        // 添加时间搜索
        if (StringUtils.isNotBlank(request.getAddtimeStart()))
        {
            c.andAddtimeGreaterThanOrEqualTo(DateUtils.getFdate(request.getAddtimeStart(),DateUtils.DATEFORMATSHORT));
        }
        if (StringUtils.isNotBlank(request.getAddtimeEnd()))
        {
            c.andAddtimeLessThanOrEqualTo(DateUtils.getFdate(request.getAddtimeEnd(),DateUtils.DATEFORMATLONG));
        }
        if(StringUtils.isNotBlank(request.getOrderNo())){
            c.andOrderNoEqualTo(request.getOrderNo());
        }
        List<PayShopStockOrder> list = payShopStockOrderMapper.selectByExample(example);

        List<ShopStockOrderInfo> infoList = new ArrayList<>();

        for (PayShopStockOrder one : list)
        {
            ShopStockOrderInfo info = new ShopStockOrderInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopStockOrder.class, ShopStockOrderInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopStockOrderResponse response = new GetShopStockOrderResponse();
        response.setList(infoList);
        int count = payShopStockOrderMapper.countByExample(example);
        response.setCount(count);
        return response;
    }


    /**
     * 采购订单详情
     */
    @Override
    public ShopStockOrderInfo getOne(String order_no)
    {
        if(StringUtils.isBlank(order_no)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "采购单号不能为空");
        }

        PayShopStockOrderExample example = new PayShopStockOrderExample();
        PayShopStockOrderExample.Criteria c = example.createCriteria();
        c.andOrderNoEqualTo(order_no);

        List<PayShopStockOrder> list = payShopStockOrderMapper.selectByExample(example);

        if(list.size() <=0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到数据");
        }

        ShopStockOrderInfo info = new ShopStockOrderInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopStockOrder.class, ShopStockOrderInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }


    /**
     * 采购订单添加
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })//事务添加
    public JpfResponseDto addStocks(GetShopStockOrderRequest request) throws Exception{

        //判断参数
        if(StringUtils.isBlank(request.getTypeName())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择付款方式");
        }
        if(StringUtils.isBlank(request.getSupplierName())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择付款周期");
        }
        if(StringUtils.isBlank(request.getProductsAll())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "请选择商品");
        }
        String orderNo="CG"+ToolUtils.createID();
        //采购订单表里插入数据
        PayShopStockOrder  payShopStockOrder=new PayShopStockOrder();
        payShopStockOrder.setOrderNo(orderNo);
        payShopStockOrder.setMoney(request.getCountMoneys());
        payShopStockOrder.setOperatorId(request.getOperatorId());
        payShopStockOrder.setOperatorName(request.getOperatorName());
        payShopStockOrder.setStatus((byte)1);
        payShopStockOrder.setPaywayId(request.getTypeNum());
        payShopStockOrder.setPaywayCn(request.getTypeName());
        payShopStockOrder.setPaytypeId(request.getSupplierNum());
        payShopStockOrder.setPaytypeCn(request.getSupplierName());
        payShopStockOrder.setProductAmount(request.getCountProducts());
        //添加备注+时间格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        if(request.getMemo() != null && StringUtils.isNotBlank(request.getMemo()) ){

            payShopStockOrder.setMemo(request.getMemo()+"&#13;&#10;["+ df.format(new Date()) + "]");
        }else{
            payShopStockOrder.setMemo("["+ df.format(new Date()) + "] ");

        }
        Date date = new Date();
        payShopStockOrder.setAddtime(date);
        //开始插入
        int insertOrder=payShopStockOrderMapper.insertSelective(payShopStockOrder);
        //查询出当前插入的订单信息
        PayShopStockOrderExample eorder=new PayShopStockOrderExample();
        PayShopStockOrderExample.Criteria c=eorder.createCriteria();
        c.andOrderNoEqualTo(orderNo);
        List<PayShopStockOrder> list=payShopStockOrderMapper.selectByExample(eorder);
        String newId="";
        if(list != null && !list.isEmpty()){
            //重新生成商户编号
            newId=list.get(0).getId();
        }
        //数据解析json
        Map<String,Object> requestMap = null;
        JSONObject jData = null;

        try {
            String dataStr = request.getProductsAll().replaceAll("\\\\","").replaceAll("\r","").replaceAll("\n","").replaceAll(" ","+");
             jData = JSONObject.fromObject(dataStr);
             if(jData==null || jData.size()==0 ){
                 throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "添加失败");
             }
        } catch (Exception e) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "添加失败");
        }
            PayShopStockOrderProduct products=new PayShopStockOrderProduct();
            for (int i=0;i<jData.size();i++){

                 JSONObject aa = jData.getJSONObject(String.valueOf(i));//JSONObject.fromObject(jData.get(i));
                 //循环插入数据
                 products.setStockOrderId(newId);
                 products.setStockOrderNo(orderNo);
                 products.setProductId(aa.get("id").toString());
                 BigDecimal bidOld = new BigDecimal(aa.get("bidOld").toString());
                 products.setProductName(aa.get("pname").toString());
                 products.setProductBid(bidOld);
                 products.setStockAmount((Integer) aa.get("stored"));
                 products.setSupplierId(aa.get("supplierId").toString());
                 products.setSupplierName(aa.get("supplierName").toString());
                 products.setBrandId((Integer) aa.get("brandId"));
                 products.setBrandName(aa.get("brandName").toString());
                 BigDecimal bid = new BigDecimal(aa.get("bid").toString());
                 products.setBid(bid);
                 //System.out.println(aa.get("amount").getClass().getName());
                 Integer amount = Integer.valueOf(aa.get("amount").toString());
                 products.setAmount(amount);
                 BigDecimal money = new BigDecimal(aa.get("money").toString());
                 products.setMoney(money);
                 products.setAddtime(date);
                 //插入采购商品表
                payShopStockOrderProductMapper.insertSelective(products);
             }

        return new JpfResponseDto();
    }

    /*
     * 审核采购订单状态
     * */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })//事务处理
    public JpfResponseDto AuditOrder(GetShopStockOrderRequest request) throws Exception{

        String  infoId = request.getId();
        String memo = request.getMemo();
        Byte infoStatus = request.getStatus();
        if( infoId == null || StringUtils.isBlank(request.getId())){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayShopStockOrder payShopStockOrder = payShopStockOrderMapper.selectByPrimaryKey(infoId);
        if( payShopStockOrder ==null){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }
        //审核判断
        int res=0;
        Date date = new Date();
        //根据状态判断更新
        PayShopStockOrder recordData = new PayShopStockOrder();
        switch (request.getStatus()){

            case 0://取消
                recordData.setStatus(request.getStatus());
                recordData.setId(infoId);
                recordData.setMemo(memo);
                //设置运营操作人
                recordData.setOperatorId(request.getOperatorId());
                recordData.setOperatorName(request.getOperatorName());
                recordData.setUpdatetime(date);
                recordData.setCheckTime(date);
                res = payShopStockOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                break;
            case 2:
                if(payShopStockOrder.getStatus() ==(byte)2){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为提交状态");
                }else{
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);
                    recordData.setMemo(memo);
                    recordData.setOperatorId(request.getOperatorId());
                    recordData.setOperatorName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    recordData.setCheckTime(date);
                    res = payShopStockOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                }

                break;
            case 3:
                if(payShopStockOrder.getStatus() !=(byte)2 && !payShopStockOrder.getStatus().equals(request.getStatus())){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "您还未提交");
                }else if(payShopStockOrder.getStatus()==(byte)3){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为审批状态");
                }else{
                    recordData.setStatus(request.getStatus());
                    recordData.setId(infoId);
                    recordData.setMemo(memo);
                    recordData.setOperatorId(request.getOperatorId());
                    recordData.setOperatorName(request.getOperatorName());
                    recordData.setUpdatetime(date);
                    recordData.setCheckTime(date);
                    res = payShopStockOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
                }
                break;
            case 4:
                //当为财务审批的时候
               if(payShopStockOrder.getStatus()!=(byte)3 && !payShopStockOrder.getStatus().equals(request.getStatus())){
                   throw new JpfException(JpfErrorInfo.DAL_ERROR, "您还未审批");
               }else if(payShopStockOrder.getStatus()==(byte)4){
                   throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为已付款状态");
               }else{
                   recordData.setStatus(request.getStatus());
                   recordData.setId(infoId);
                   recordData.setMemo(memo);
                   recordData.setCheckOperatorId(request.getCheckOperatorId());
                   recordData.setCheckOperatorName(request.getCheckOperatorName());
                   recordData.setCheckTime(date);
                   recordData.setUpdatetime(date);
                   res = payShopStockOrderMapper.updateByPrimaryKeySelective(recordData); //指定字段更新
               }
                break;

            default:
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态");

        }
        if( res !=1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }

        return new JpfResponseDto();
    }

}
