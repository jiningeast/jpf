package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.common.po.PayShopCompanyChargeExample;
import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayShopCompanyChargeMapper;
import com.joiest.jpf.dto.GetShopCompanyChargeRequest;
import com.joiest.jpf.dto.GetShopCompanyChargeResponse;
import com.joiest.jpf.entity.ShopCompanyChargeInfo;
import com.joiest.jpf.entity.ShopCompanyInfo;
import com.joiest.jpf.facade.ShopCompanyChargeServiceFacade;
import com.joiest.jpf.facade.ShopCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShopCompanyChargeServiceFacadeImpl implements ShopCompanyChargeServiceFacade {

    @Autowired
    private PayShopCompanyChargeMapper payShopCompanyChargeMapper;

    @Autowired
    private ShopCompanyServiceFacade shopCompanyServiceFacade;

    /**
     * 订单列表---后台
     */
    @Override
    public GetShopCompanyChargeResponse getList(GetShopCompanyChargeRequest request)
    {
        if( request.getPage() <= 1){
            request.setPage(Long.parseLong("1"));
        }
        PayShopCompanyChargeExample example = new PayShopCompanyChargeExample();
        example.setPageNo(request.getPage());
        example.setPageSize(request.getRows());
        example.setOrderByClause("id DESC");

        PayShopCompanyChargeExample.Criteria c =example.createCriteria();
        if(request.getStatus() != null && !request.getStatus().equals("")){
            c.andStatusEqualTo(request.getStatus());
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
        if( StringUtils.isNotBlank(request.getCompanyName())){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }

        List<PayShopCompanyCharge> list = payShopCompanyChargeMapper.selectByExample(example);

        List<ShopCompanyChargeInfo> infoList = new ArrayList<>();

        for (PayShopCompanyCharge one : list)
        {
            ShopCompanyChargeInfo info = new ShopCompanyChargeInfo();
            BeanCopier beanCopier = BeanCopier.create(PayShopCompanyCharge.class, ShopCompanyChargeInfo.class, false);
            beanCopier.copy(one, info, null);
            infoList.add(info);
        }
        GetShopCompanyChargeResponse response = new GetShopCompanyChargeResponse();
        response.setList(infoList);
        int count = payShopCompanyChargeMapper.countByExample(example);
        response.setCount(count);
        return response;
    }

    /**
     * 订单列表---后台
     */
    @Override
    public ShopCompanyChargeInfo getOne(String id)
    {
        if(StringUtils.isBlank(id)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "采购单号不能为空");
        }
        PayShopCompanyChargeExample example = new PayShopCompanyChargeExample();
        PayShopCompanyChargeExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);

        List<PayShopCompanyCharge> list = payShopCompanyChargeMapper.selectByExample(example);
        if(list.size() <=0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到数据");
        }

        ShopCompanyChargeInfo info = new ShopCompanyChargeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayShopCompanyCharge.class, ShopCompanyChargeInfo.class, false);
        beanCopier.copy(list.get(0), info, null);


        return info;
    }

    /**
     * 公司列表---后台
     */
    @Override
    public JpfResponseDto add(GetShopCompanyChargeRequest request){

        if( StringUtils.isBlank(request.getCompanyId()) || StringUtils.isBlank(request.getCompanyName()) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司不能为空");
        }

        if(request.getMoney()==null || request.getMoney().toString().equals("") || request.getMoney().compareTo(new BigDecimal("0")) < 1   ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "实际到帐金额不能为空或小于0");
        }

        if(request.getContractMoney()==null || request.getContractMoney().toString().equals("") || request.getContractMoney().compareTo(new BigDecimal("0")) < 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "合同金额不能为空或小于0");
        }
        if( request.getRate()==null || request.getRate().toString().equals("") || request.getRate().compareTo(new BigDecimal("0")) <0   ){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "费率不能为空或小于0");
        }
        if( request.getImgUrl().equals("") ||  request.getImgUrl()== null  ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "图片不能为空");
        }
        BigDecimal resultMoney;
        BigDecimal roteMoney;
        roteMoney=request.getContractMoney().multiply(request.getRate().divide(new BigDecimal("100")));
        resultMoney=request.getContractMoney().subtract(roteMoney).setScale(2,BigDecimal.ROUND_HALF_DOWN);
        //金额校验
        if(request.getMoney().compareTo(resultMoney)!=0){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "金额校验失败");
        }
        Date curretDate = new Date();
        PayShopCompanyCharge payShopCompanyCharge = new PayShopCompanyCharge();
        payShopCompanyCharge.setCompanyId(request.getCompanyId());
        payShopCompanyCharge.setCompanyName(request.getCompanyName());

        payShopCompanyCharge.setOperatorId(request.getOperatorId());
        payShopCompanyCharge.setOperatorName(request.getOperatorName());

        payShopCompanyCharge.setImgUrl(request.getImgUrl());
        payShopCompanyCharge.setContractMoney(request.getContractMoney());
        payShopCompanyCharge.setRate(request.getRate());
        payShopCompanyCharge.setMoney(request.getMoney());
        payShopCompanyCharge.setAddtime(curretDate);
        payShopCompanyCharge.setStatus((byte)0);
        payShopCompanyCharge.setUpdatetime(curretDate);
        payShopCompanyCharge.setContractNo(request.getContractNo());
        payShopCompanyCharge.setDuetime(DateUtils.getFdate(request.getDuetime(),DateUtils.DATEFORMATSHORT));
        payShopCompanyCharge.setCouponMoney(request.getCouponMoney());
        payShopCompanyCharge.setServiceMoney(request.getServiceMoney());
        payShopCompanyCharge.setTransferRate(request.getTransferRate());
        payShopCompanyCharge.setBalance(request.getCouponMoney());
        payShopCompanyCharge.setServiceContent(request.getServiceContent());
        int count = payShopCompanyChargeMapper.insertSelective(payShopCompanyCharge);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "添加失败");
        }
        return new JpfResponseDto();
    }

    /**
     * 公司列表---后台
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto auditCompanyCharge(GetShopCompanyChargeRequest request){

        if( StringUtils.isBlank(request.getId()) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司不能为空");
        }

        PayShopCompanyCharge shopCompanyCharge = payShopCompanyChargeMapper.selectByPrimaryKey(request.getId());
        ShopCompanyChargeInfo shopCompanyChargeInfo = new ShopCompanyChargeInfo();
        if( shopCompanyCharge ==null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }
        BeanCopier beanCopier = BeanCopier.create(PayShopCompanyCharge.class,ShopCompanyChargeInfo.class,false);
        beanCopier.copy(shopCompanyCharge,shopCompanyChargeInfo,null);

        //已取消状态 无法继续操作
        if( shopCompanyChargeInfo.getStatus() == -1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatusCn());
        }

        Date curretDate = new Date();
        PayShopCompanyCharge payShopCompanyCharge = new PayShopCompanyCharge();

        payShopCompanyCharge.setCheckOperatorId(request.getCheckOperatorId());
        payShopCompanyCharge.setId(request.getId());
        payShopCompanyCharge.setStatus((byte)request.getStatus());
        payShopCompanyCharge.setCheckTime(curretDate);
        payShopCompanyCharge.setUpdatetime(curretDate);
        payShopCompanyCharge.setCheckOperatorName(request.getCheckOperatorName());
        payShopCompanyCharge.setCheckOperatorId(request.getCheckOperatorId());

        int count = payShopCompanyChargeMapper.updateByPrimaryKeySelective(payShopCompanyCharge);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }
        return new JpfResponseDto();
    }

    /**
     * 公司列表---后台
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto caiWuCompanyCharge(GetShopCompanyChargeRequest request){

        if( StringUtils.isBlank(request.getId()) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "公司不能为空");
        }

        PayShopCompanyCharge shopCompanyCharge = payShopCompanyChargeMapper.selectByPrimaryKey(request.getId());
        ShopCompanyChargeInfo shopCompanyChargeInfo = new ShopCompanyChargeInfo();
        if( shopCompanyCharge ==null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }
        BeanCopier beanCopier = BeanCopier.create(PayShopCompanyCharge.class,ShopCompanyChargeInfo.class,false);
        beanCopier.copy(shopCompanyCharge,shopCompanyChargeInfo,null);

        Date curretDate = new Date();
        PayShopCompanyCharge payShopCompanyCharge = new PayShopCompanyCharge();

        payShopCompanyCharge.setCheckOperatorId(request.getCheckOperatorId());
        payShopCompanyCharge.setId(request.getId());
        payShopCompanyCharge.setStatus((byte)request.getStatus());
        payShopCompanyCharge.setCheckTime(curretDate);
        payShopCompanyCharge.setUpdatetime(curretDate);
        payShopCompanyCharge.setCheckOperatorName(request.getCheckOperatorName());
        payShopCompanyCharge.setCheckOperatorId(request.getCheckOperatorId());

        int ret = 0;
        switch (shopCompanyChargeInfo.getStatus()){
            case 0:
                if( request.getStatus() ==  1){ // 审核通过，并充值充值
                    //欣豆商户账户充值
                    BigDecimal money = shopCompanyChargeInfo.getMoney();
                    String companyId = shopCompanyChargeInfo.getCompanyId();
                    //校验欣豆商户金额
                    ShopCompanyInfo shopCompanyInfo = shopCompanyServiceFacade.getCompanyOne(companyId);
                    Boolean flag = ToolUtils.ValidateCode(shopCompanyInfo.getMoneyCode(),companyId,shopCompanyInfo.getMoney().toString());
                    if( flag ){
                        BigDecimal companyMoney =shopCompanyInfo.getMoney();
                        BigDecimal afterMoney = money.add(companyMoney);
                        afterMoney =afterMoney.setScale(2,BigDecimal.ROUND_DOWN);
                        String afterCode = ToolUtils.CreateCode(afterMoney.toString(),companyId);
                        PayShopCompany shopCompany = new PayShopCompany();
                        shopCompany.setMoney(afterMoney);
                        shopCompany.setMoneyCode(afterCode);
                        shopCompany.setId(companyId);
                        JpfResponseDto jpfResponseDto = shopCompanyServiceFacade.updateCompanyRecord(shopCompany);
                        if(jpfResponseDto.getRetCode().equals("0000")){ //更新成功
                            ret = 1;
                        }

                    }else{
                        throw new JpfException(JpfErrorInfo.DAL_ERROR, "金额校验失败，公司："+shopCompanyCharge.getCompanyName());
                    }

                }
                if( request.getStatus() ==  2){ // 审核驳回
                    ret = 1;
                }
                break;
            case 1:
            case 2:
            default:
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatusCn());

        }

        if( ret !=1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }

        int count = payShopCompanyChargeMapper.updateByPrimaryKeySelective(payShopCompanyCharge);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public List<PayShopCompanyCharge> getListByCompanyId(String companyId) {
        PayShopCompanyChargeExample example = new PayShopCompanyChargeExample();
        PayShopCompanyChargeExample.Criteria criteria= example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andBalanceGreaterThan(new BigDecimal(0));
        criteria.andStatusEqualTo((byte)1);
        example.setOrderByClause(" id asc ");
        return payShopCompanyChargeMapper.selectByExample(example);
    }

    @Override
    public PayShopCompanyCharge getById(String contractId) {
        return payShopCompanyChargeMapper.selectByPrimaryKey(contractId);
    }

    @Override
    public List<PayShopCompanyCharge> getListByCompanyIdByPage(Map<String, Object> map) {
        PayShopCompanyChargeExample example = new PayShopCompanyChargeExample();
        PayShopCompanyChargeExample.Criteria criteria= example.createCriteria();
        if (map.get("companyId")!=null){
            criteria.andCompanyIdEqualTo(map.get("companyId").toString());
        }
        if (map.get("pageSize")==null||Long.valueOf(map.get("pageSize").toString())<= 0){
            example.setPageSize(10);
        }else{
            example.setPageSize(Long.valueOf(map.get("pageSize").toString()));
        }
        if(map.get("pageNo")==null||Long.valueOf(map.get("pageNo").toString())== 0){
            example.setPageNo(1);
        }else{
            example.setPageNo(Long.valueOf(map.get("pageNo").toString()));
        }
        criteria.andBalanceGreaterThan(new BigDecimal(0));
        criteria.andStatusEqualTo((byte)1);
        example.setOrderByClause(" id asc ");
        return payShopCompanyChargeMapper.selectByExample(example);
    }

    @Override
    public Integer getTotal(Map<String, Object> map) {
        PayShopCompanyChargeExample example = new PayShopCompanyChargeExample();
        PayShopCompanyChargeExample.Criteria criteria= example.createCriteria();
        if (map.get("companyId")!=null){
            criteria.andCompanyIdEqualTo(map.get("companyId").toString());
        }
        criteria.andBalanceGreaterThan(new BigDecimal(0));
        criteria.andStatusEqualTo((byte)1);
        example.setOrderByClause(" id asc ");
        return payShopCompanyChargeMapper.countByExample(example);
    }


}
