package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.*;
import com.joiest.jpf.common.util.ArithmeticUtils;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCouponOrderCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCouponRemainCustomMapper;
import com.joiest.jpf.dao.repository.mapper.custom.PayShopCustomerCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopBatchCouponMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponActiveMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCouponRemainMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCustomerMapper;
import com.joiest.jpf.dto.*;
import com.joiest.jpf.entity.ShopCouponRemainInfo;
import com.joiest.jpf.entity.ShopCustomerInfo;
import com.joiest.jpf.facade.ShopBatchCouponServiceFacade;
import com.joiest.jpf.facade.ShopCustomerServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class ShopCustomerServiceFacadeImpl implements ShopCustomerServiceFacade {

    @Autowired
    private PayShopCustomerMapper payShopCustomerMapper;
    @Autowired
    private PayShopCustomerCustomMapper payShopCustomerCustomMapper;
    @Autowired
    private PayShopCouponRemainMapper payShopCouponRemainMapper;
    @Autowired
    private PayShopBatchCouponMapper payShopBatchCouponMapper;
    @Autowired
    private PayShopCouponActiveMapper payShopCouponActiveMapper;
    @Autowired
    private PayShopCouponRemainCustomMapper payShopCouponRemainCustomMapper;
    @Autowired
    private PayShopCouponOrderCustomMapper payShopCouponOrderCustomMapper;

    /**
     * 公司列表---后台
     */
    @Override
    public GetShopCustomerResponse getList(GetShopCustomerRequest request)
    {
        if ( request.getRows() <= 0 )
        {
            request.setRows(10);
        }

        if ( request.getPage() <= 0 )
        {
            request.setPage(1);
        }

        PayShopCustomerExample example = new PayShopCustomerExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("addtime DESC");

        PayShopCustomerExample.Criteria c = example.createCriteria();
        if ( StringUtils.isNotBlank(request.getNickname()))
        {
            String  nickname="";
            try {
                nickname = URLEncoder.encode(request.getNickname().toString(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            c.andNicknameEqualTo( nickname);
        }
        if( StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameEqualTo(request.getCompanyName());
        }
        if( StringUtils.isNotBlank(request.getPhone())){
            c.andPhoneEqualTo(request.getPhone());
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
        if(request.getStatus()!=null && request.getStatus().toString()!=""){
            c.andStatusEqualTo(request.getStatus());
        }
        if(request.getIsVerify()!=null && request.getIsVerify().toString()!=""){
            c.andIsVerifyEqualTo(request.getIsVerify());
        }
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(example);
        List<ShopCustomerInfo> infoList = new ArrayList<>();
        for (PayShopCustomer one : list)
        {
            ShopCustomerInfo info = new ShopCustomerInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopCustomerResponse response = new GetShopCustomerResponse();
        response.setList(infoList);
        int count = payShopCustomerMapper.countByExample(example);
        response.setCount(count);
        return response;
    }



    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto delCompanyCustomer(String id,int type)
    {
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        //查询当前添加的是否存在
        PayShopCustomerExample example= new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);

        List<PayShopCustomer> payShopCustomerList = payShopCustomerMapper.selectByExample(example);
        if(payShopCustomerList.isEmpty() || payShopCustomerList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条记录不存在");
        }
        Byte defaultStatus =payShopCustomerList.get(0).getStatus();
        PayShopCustomer payShopCustomer = new PayShopCustomer();
        Byte status=-1;
        if(type==2){
            status = 0;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该客户已被冻结");
            }
        }else if (type==1){
            status = 1;
            if(defaultStatus==status){
                throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "该客户已被恢复正常");
            }
        }
        // 创建日期
        Date d = new Date();
        payShopCustomer.setStatus(status);
        payShopCustomer.setUpdatetime(d);
        payShopCustomerMapper.updateByExampleSelective(payShopCustomer,example);
        return new JpfResponseDto();
    }

    /**
     * 停用公司 更改状态 1启用 0停用
     */
    @Override
    public JpfResponseDto editCompanyCustomer(GetShopCustomerRequest request)
    {
        if ( StringUtils.isBlank(request.getId()) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        //查询当前添加的是否存在
        PayShopCustomerExample example= new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());

        List<PayShopCustomer> payShopCustomerList = payShopCustomerMapper.selectByExample(example);
        if(payShopCustomerList.isEmpty() || payShopCustomerList==null){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "此条记录不存在");
        }
        Byte defaultType =payShopCustomerList.get(0).getType();
        Byte defaultIsBargainBuyer =payShopCustomerList.get(0).getIsBargainBuyer();
        PayShopCustomer payShopCustomer = new PayShopCustomer();
//        if(defaultType==request.getType()){
//            String word = defaultType == 1 ? "特殊用户":"正常用户";
//            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "不能更新状态为："+word);
//        }
//        if(defaultIsBargainBuyer==request.getIsBargainBuyer()){
//            String word = defaultIsBargainBuyer == 1 ? "是":"不是";
//            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "不能更新状态为："+word);
//        }

        // 创建日期
        Date d = new Date();
        payShopCustomer.setType(request.getType());
        payShopCustomer.setIsBargainBuyer(request.getIsBargainBuyer());
        payShopCustomer.setUpdatetime(d);
        int count = payShopCustomerMapper.updateByExampleSelective(payShopCustomer,example);
        if(count != 1 ){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "更新失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public PayShopCustomer getCustomerByPhone(String phone){
        PayShopCustomerExample e = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = e.createCriteria();
        c.andPhoneEqualTo(phone);
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(e);
        if(list!=null&&list.size()!=0){
            return list.get(0);
        }
        return null;

    }

    @Override
    public PayShopCustomer getCustomerById(String id) {
        PayShopCustomerExample e = new PayShopCustomerExample();
        PayShopCustomerExample.Criteria c = e.createCriteria();
        c.andIdEqualTo(id);
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(e);


        try {
            list.get(0).setNickname( URLDecoder.decode( list.get(0).getNickname(),"UTF-8" ));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        return list.get(0);
    }

    /**
     * 更新用户信息
     * */
    @Override
    public int upCustomerInfo(ShopCustomerInfo shopCustomerInfo){

       PayShopCustomer payShopCustomer = new PayShopCustomer();

       BeanCopier beanCopier = BeanCopier.create(ShopCustomerInfo.class,PayShopCustomer.class,false);
       beanCopier.copy(shopCustomerInfo,payShopCustomer,null);

        return payShopCustomerMapper.updateByPrimaryKeySelective(payShopCustomer);
    }
    /**
     * 修改所有用户的code
     * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateCode(){

        PayShopCustomerExample example = new PayShopCustomerExample();
        example.setOrderByClause("addtime DESC");
        List<PayShopCustomer> list = payShopCustomerMapper.selectByExample(example);
        for (PayShopCustomer one : list)
        {
            ShopCustomerInfo info = new ShopCustomerInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCustomer.class, ShopCustomerInfo.class, false);
            beanCopier.copy(one, info, null);
            //进行code转换
            String code= ToolUtils.CreateCode(info.getDou().toString(),info.getId());
            info.setCode(code);
            //修改用户信息
            int result=upCustomerInfo(info);
            if(result!=1){
                return false;
            }
        }
        return true;
    }

    /**
     * 商户扣款操作
     * @param map
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> pay(Map<String, Object> map) throws  Exception {
        Map<String,Object> resMap = new HashMap<>();
        List<CouponActive> couponNolist =new ArrayList<>();
        List<CouponActive> couponYeslist =new ArrayList<>();
        resMap.put("customerId",map.get("customerId").toString());
        resMap.put("orderNo",map.get("orderNo").toString());
        //查出当前用户的可转让金额总数
        PayShopCouponRemainExample exampleNo=new PayShopCouponRemainExample();
        PayShopCouponRemainExample.Criteria cNo=exampleNo.createCriteria();
        cNo.andCustomerIdEqualTo(map.get("customerId").toString());
        exampleNo.setOrderByClause(" expire_time ASC");
        cNo.andStatusEqualTo((byte)0);
        List<PayShopCouponRemain> saleNo= payShopCouponRemainMapper.selectByExample(exampleNo);
        BigDecimal totalMoney = new BigDecimal(map.get("money").toString());
        //不可转让订单扣除豆
        BigDecimal couponDou = new BigDecimal(0.00);
        //可转让豆扣除情况
        BigDecimal saleDou = new BigDecimal(0.00);
        //每次消耗的豆
        BigDecimal dou;
        for (PayShopCouponRemain payShopCouponRemain:saleNo) {
            CouponActive couponActive = new CouponActive();
            couponActive.setId(payShopCouponRemain.getId());
            dou = new BigDecimal(0.00);
            //证明券商不可转让金额小于总额
            if(totalMoney.compareTo(new BigDecimal(0.00))==0){
                break;
            }
            if(totalMoney.compareTo(payShopCouponRemain.getCouponDouLeft())>=0){
                totalMoney = ArithmeticUtils.sub(totalMoney.toString(),payShopCouponRemain.getCouponDouLeft().toString());
                couponDou =ArithmeticUtils.add(couponDou.toString(),payShopCouponRemain.getCouponDouLeft().toString());
                dou = ArithmeticUtils.add(dou.toString(),payShopCouponRemain.getCouponDouLeft().toString());
                //payShopCouponRemain.setCouponDouLeft(new BigDecimal(0.00));
                payShopCouponRemain.setStatus((byte)1);
            }else{
                //证明欣券的剩余额度大于剩余需扣除的额度
                //payShopCouponRemain.setCouponDouLeft(ArithmeticUtils.sub(payShopCouponRemain.getCouponDouLeft().toString(),totalMoney.toString()));
                couponDou =ArithmeticUtils.add(couponDou.toString(),totalMoney.toString());
                dou = ArithmeticUtils.add(dou.toString(),totalMoney.toString());
                totalMoney = new BigDecimal(0.00);
            }
            int count =subCouponDouNo(payShopCouponRemain,dou);
            if (count==0){
                throw new Exception("扣款失败");
            }
            //扣除欣券对应订单的余额
            int subCount=subShopCouponOrder(payShopCouponRemain,dou);
            if (subCount==0){
                throw new Exception("欣券所属订余额更新失败");
            }
            saveCouponActive(payShopCouponRemain,map,dou);
            // payShopCouponRemainMapper.updateByPrimaryKeySelective(payShopCouponRemain);
            couponActive.setTotalSaleDouNo(dou.toString());
            couponNolist.add(couponActive);
        }

        //如果上面的循环执行完毕，最终金额仍然大于0，此时扣减可转让豆
        if(totalMoney.compareTo(new BigDecimal(0.00))>0){
            //查出当前用户的非转让金额总数
            PayShopCouponRemainExample exampleYes=new PayShopCouponRemainExample();
            PayShopCouponRemainExample.Criteria cYes=exampleYes.createCriteria();
            cYes.andCustomerIdEqualTo(map.get("customerId").toString());
            cYes.andSalestatusEqualTo((byte)0);
            //可转换列表
            List<PayShopCouponRemain> saleYes=payShopCouponRemainMapper.selectByExample(exampleYes);
            for (PayShopCouponRemain payShopCouponRemain:saleYes) {
                CouponActive couponActive = new CouponActive();
                //证明券商不可转让金额小于总额
                dou = new BigDecimal(0.00);
                if(totalMoney.compareTo(new BigDecimal(0.00))==0){
                    break;
                }
                if(totalMoney.compareTo(payShopCouponRemain.getSaleDouLeft())>=0){
                    totalMoney = ArithmeticUtils.sub(totalMoney.toString(),payShopCouponRemain.getSaleDouLeft().toString());
                    saleDou =ArithmeticUtils.add(saleDou.toString(),payShopCouponRemain.getSaleDouLeft().toString());
                    dou = ArithmeticUtils.add(dou.toString(),payShopCouponRemain.getSaleDouLeft().toString());
                    //payShopCouponRemain.setSaleDouLeft(new BigDecimal(0.00));
                    payShopCouponRemain.setStatus((byte)1);
                }else{
                    //payShopCouponRemain.setSaleDouLeft(ArithmeticUtils.sub(saleDou.toString(),payShopCouponRemain.getSaleDouLeft().toString()));
                    saleDou =ArithmeticUtils.add(saleDou.toString(),totalMoney.toString());
                    dou = ArithmeticUtils.add(dou.toString(),totalMoney.toString());
                    totalMoney = new BigDecimal(0.00);

                }
                int count =subCouponDouYes(payShopCouponRemain,dou);
                if (count==0){
                    throw new Exception("扣款失败");
                }
                int subCount=subShopCouponOrder(payShopCouponRemain,dou);
                if (subCount==0){
                    throw new Exception("欣券所属订余额更新失败");
                }
                saveCouponActive(payShopCouponRemain,map,dou);
                couponActive.setTotalSaleDouNo(dou.toString());
                couponNolist.add(couponActive);
            }
        }
        //扣减用户的豆
        subCustomerDou(map,couponDou,saleDou);
        resMap.put("totalSaleDouNo",couponDou);
        resMap.put("totalSaleDouYes",saleDou);
        resMap.put("subDate",DateUtils.getFdate(DateUtils.getCurDate(),DateUtils.DATEFORMATSHORT));
        resMap.put("couponNolist",couponNolist);
        resMap.put("couponYeslist",couponYeslist);
        return resMap;
    }

    /**
     * 扣除合同的余额
     * @param payShopCouponRemain
     * @param dou
     * @return
     */
    private int subShopCouponOrder(PayShopCouponRemain payShopCouponRemain, BigDecimal dou) {
        //查询欣券
        PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(payShopCouponRemain.getCouponId());
        //查询订单的信息
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",payShopBatchCoupon.getOrderId());
        map.put("subDou",dou);
        int count =payShopCouponOrderCustomMapper.subShopCouponOrder(map);
        return count;
    }

    /**
     * 增加合同的余额
     * @param payShopCouponRemain
     * @param dou
     * @return
     */
    public int addShopCouponOrder(PayShopCouponRemain payShopCouponRemain, BigDecimal dou) {
        //查询欣券
        PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(payShopCouponRemain.getCouponId());
        //查询订单的信息
        Map<String,Object> map = new HashMap<>();
        map.put("orderId",payShopBatchCoupon.getOrderId());
        map.put("addDou",dou);
        int count =payShopCouponOrderCustomMapper.addShopCouponOrder(map);
        return count;
    }

    /**
     * 更新可转让豆
     * @param payShopCouponRemain
     * @param dou
     * @return
     */
    private int subCouponDouYes(PayShopCouponRemain payShopCouponRemain, BigDecimal dou) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",payShopCouponRemain.getId());
        map.put("status",payShopCouponRemain.getStatus());
        map.put("subDouYes",dou);
        int count = payShopCouponRemainCustomMapper.subCouponDouYes(map);
        return count;
    }

    /**
     * 退还可转让豆
     * @param payShopCouponRemain
     * @param dou
     * @return
     */
    private int addCouponDouYes(PayShopCouponRemain payShopCouponRemain, BigDecimal dou) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",payShopCouponRemain.getId());
        map.put("status",payShopCouponRemain.getStatus());
        map.put("addDouYes",dou);
        int count = payShopCouponRemainCustomMapper.addCouponDouYes(map);
        return count;
    }

    /**
     * 更新不可转让券豆
     * @param payShopCouponRemain
     * @param dou
     * @return
     */
    private int subCouponDouNo(PayShopCouponRemain payShopCouponRemain, BigDecimal dou) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",payShopCouponRemain.getId());
        map.put("status",payShopCouponRemain.getStatus());
        map.put("subDou",dou);
        int count = payShopCouponRemainCustomMapper.subCouponDouNo(map);
        return count;
    }
    /**
     * 退还不可转让券豆
     * @param payShopCouponRemain
     * @param dou
     * @return
     */
    private int addCouponDouNo(PayShopCouponRemain payShopCouponRemain, BigDecimal dou) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",payShopCouponRemain.getId());
        map.put("status",payShopCouponRemain.getStatus());
        map.put("addDou",dou);
        int count = payShopCouponRemainCustomMapper.addCouponDouNo(map);
        return count;
    }

    /**
     * 扣减用户的豆
     * @param map
     * @param couponDou
     * @param saleDou
     */
    private void subCustomerDou(Map<String, Object> map, BigDecimal couponDou, BigDecimal saleDou) throws Exception {
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("couponDouNo",couponDou);
        mapParam.put("saleDouYes",saleDou);
        mapParam.put("customerId",map.get("customerId"));
        int count = payShopCustomerCustomMapper.subCustomerDou(mapParam);
        if(count==0){
            throw  new Exception("扣款失败");
        }
    }

    /**
     * 扣减用户的豆
     * @param map
     * @param couponDou
     * @param saleDou
     */
    private void addCustomerDou(Map<String, Object> map, BigDecimal couponDou, BigDecimal saleDou) throws Exception {
        Map<String,Object> mapParam = new HashMap<>();
        mapParam.put("couponDouNo",couponDou);
        mapParam.put("saleDouYes",saleDou);
        mapParam.put("customerId",map.get("customerId"));
        int count = payShopCustomerCustomMapper.addCustomerDou(mapParam);
        if(count==0){
            throw  new Exception("退还客户欣豆失败");
        }
    }

    /**
     *
     * @param payShopCouponRemain
     * @param map
     * @param dou
     */
    private void saveCouponActive(PayShopCouponRemain payShopCouponRemain, Map<String, Object> map,BigDecimal dou) {
        PayShopCustomer customer = payShopCustomerMapper.selectByPrimaryKey(map.get("customerId").toString());
        PayShopCouponActive payShopCouponActive = new PayShopCouponActive();
        payShopCouponActive.setCustomerId(customer.getId());
        payShopCouponActive.setCustomerName(customer.getName());
        payShopCouponActive.setCompanyId(Integer.parseInt(customer.getCompanyId().toString()));
        payShopCouponActive.setCompanyName(customer.getCompanyName());
        //查询一下券
        PayShopBatchCoupon payShopBatchCoupon = payShopBatchCouponMapper.selectByPrimaryKey(payShopCouponRemain.getCouponId());
        payShopCouponActive.setBatchId(Integer.parseInt(payShopBatchCoupon.getBatchId()));
        payShopCouponActive.setBatchNo(payShopBatchCoupon.getBatchNo());
        payShopCouponActive.setCouponNo(payShopBatchCoupon.getCouponNo());
        payShopCouponActive.setActiveCode(payShopBatchCoupon.getActiveCode());
        payShopCouponActive.setPayWay((byte)0);
        payShopCouponActive.setMoney(new BigDecimal("0.00"));
        payShopCouponActive.setDou(dou);     //消费豆数量
        payShopCouponActive.setContent("行为:消费;用户ID:" + customer.getId() + ";用户名称:" + customer.getNickname() + ";豆消费:" + dou + ";订单号:" +map.get("orderNo").toString() + ";" );
        payShopCouponActive.setType("1");
        payShopCouponActive.setExpireTime(payShopBatchCoupon.getExpireTime());
        payShopCouponActive.setAddtime(new Date());
        payShopCouponActive.setOrderId(map.get("orderId")!=null?map.get("orderId").toString():"");
        payShopCouponActive.setOrderNo(map.get("orderNo").toString());
        payShopCouponActive.setSource(map.get("source").toString());
        payShopCouponActiveMapper.insertSelective(payShopCouponActive);
    }
}
