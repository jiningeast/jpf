package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyCharge;
import com.joiest.jpf.common.po.PayChargeCompanyChargeExample;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyChargeMapper;
import com.joiest.jpf.dto.GetChargeCompanyChargeRequest;
import com.joiest.jpf.dto.GetChargeCompanyChargeResponse;
import com.joiest.jpf.entity.ChargeCompanyChargeInfo;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyChargeServiceFacade;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeCompanyChargeServiceFacadeImpl implements ChargeCompanyChargeServiceFacade {

    @Autowired
    private PayChargeCompanyChargeMapper payChargeCompanyChargeMapper;

    @Autowired

    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;
    /**
     * 获取充值订单
     */
    @Override
    public GetChargeCompanyChargeResponse getRecords(GetChargeCompanyChargeRequest request){
        GetChargeCompanyChargeResponse response = new GetChargeCompanyChargeResponse();

        PayChargeCompanyChargeExample e = new PayChargeCompanyChargeExample();
        PayChargeCompanyChargeExample.Criteria c = e.createCriteria();
        e.setPageNo(request.getPage());
        e.setPageSize(request.getRows());
        if ( request.getCompanyId() != null && StringUtils.isNotBlank(request.getCompanyId())){
            c.andCompanyIdEqualTo(request.getCompanyId());
        }
        if ( request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }
        if ( request.getStatus() != null && request.getStatus() == 0 ){
            c.andStatusEqualTo((byte)0);
        }else if ( request.getStatus() != null && request.getStatus() == 1 ){
            c.andStatusEqualTo((byte)1);
        }else if ( request.getStatus() != null && request.getStatus() == 2 ){
            c.andStatusEqualTo((byte)2);
        }
        List<ChargeCompanyChargeInfo> infos = new ArrayList<>();
        List<PayChargeCompanyCharge> list = payChargeCompanyChargeMapper.selectByExample(e);
        int count = payChargeCompanyChargeMapper.countByExample(e);
        if ( list != null && !list.isEmpty() ){
            for ( PayChargeCompanyCharge one:list ){
                ChargeCompanyChargeInfo chargeCompanyChargeInfo = new ChargeCompanyChargeInfo();

                BeanCopier beanCopier = BeanCopier.create(PayChargeCompanyCharge.class,ChargeCompanyChargeInfo.class,false);
                beanCopier.copy(one,chargeCompanyChargeInfo,null);

                infos.add(chargeCompanyChargeInfo);
            }
        }
        response.setList(infos);
        response.setCount(count);

        return response;
    }

    /**
     * 添加充值订单
     */
    @Override
    public int addRecord(ChargeCompanyChargeInfo info){
        PayChargeCompanyCharge payChargeCompanyCharge = new PayChargeCompanyCharge();

        BeanCopier beanCopier = BeanCopier.create(ChargeCompanyChargeInfo.class,PayChargeCompanyCharge.class,false);
        beanCopier.copy(info,payChargeCompanyCharge,null);
        return payChargeCompanyChargeMapper.insert(payChargeCompanyCharge);
    }

    /**
     * 充值单条信息---后台
     */
    @Override
    public ChargeCompanyChargeInfo getOne(String id)
    {
        if(StringUtils.isBlank(id)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayChargeCompanyChargeExample example = new PayChargeCompanyChargeExample();
        PayChargeCompanyChargeExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(id);

        List<PayChargeCompanyCharge> list = payChargeCompanyChargeMapper.selectByExample(example);
        if(list.size() <=0 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到数据");
        }

        ChargeCompanyChargeInfo info = new ChargeCompanyChargeInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompanyCharge.class, ChargeCompanyChargeInfo.class, false);
        beanCopier.copy(list.get(0), info, null);

        return info;
    }

    /**
     * 财务审核---后台
     */
    @Override
    @Transactional(rollbackFor = { Exception.class, RuntimeException.class })
    public JpfResponseDto caiWuCompanyCharge(GetChargeCompanyChargeRequest request){

        if( StringUtils.isBlank(request.getId()) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }

        PayChargeCompanyCharge payChargeCompanyCharges = payChargeCompanyChargeMapper.selectByPrimaryKey(request.getId());
        ChargeCompanyChargeInfo chargeCompanyChargeInfo = new ChargeCompanyChargeInfo();
        if( payChargeCompanyCharges ==null ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "未查询到相关信息");
        }
        if(payChargeCompanyCharges.getImgUrl()==null || StringUtils.isBlank(payChargeCompanyCharges.getImgUrl())){

            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "当前充值未上传付款凭证！");
        }
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompanyCharge.class,ChargeCompanyChargeInfo.class,false);
        beanCopier.copy(payChargeCompanyCharges,chargeCompanyChargeInfo,null);

        Date curretDate = new Date();
        PayChargeCompanyCharge payChargeCompanyCharge = new PayChargeCompanyCharge();
        payChargeCompanyCharge.setCheckOperatorId(request.getCheckOperatorId());
        payChargeCompanyCharge.setId(request.getId());
        payChargeCompanyCharge.setStatus((byte)request.getStatus());
        payChargeCompanyCharge.setMemo(request.getMemo());
        payChargeCompanyCharge.setCheckTime(curretDate);
        payChargeCompanyCharge.setUpdatetime(curretDate);
        payChargeCompanyCharge.setCheckOperatorName(request.getCheckOperatorName());

        int ret = 0;
        switch (chargeCompanyChargeInfo.getStatus()){
            case 0:
                if( request.getStatus() ==  1){ // 审核通过，并充值充值
                    //欣豆商户账户充值
                    BigDecimal money = chargeCompanyChargeInfo.getMoney();
                    String companyId = chargeCompanyChargeInfo.getCompanyId();
                    //校验欣豆商户金额
                    ChargeCompanyInfo chargeCompanyInfo = chargeCompanyServiceFacade.getRecordByPrimaryKey(companyId);
                    //判断当前公司是否冻结
                    if(chargeCompanyInfo.getIsFreeze()==(byte)1){
                        throw new JpfException(JpfErrorInfo.DAL_ERROR, "公司："+chargeCompanyInfo.getCompanyName()+"已经冻结！");
                    }
                   /* if(chargeCompanyInfo.getMoneyCode()==null ||StringUtils.isBlank(chargeCompanyInfo.getMoneyCode())){
                        throw new JpfException(JpfErrorInfo.DAL_ERROR, "金额校验失败，公司："+chargeCompanyInfo.getCompanyName());
                    }*/
                    Boolean flag=true;
                    BigDecimal compare=new BigDecimal("0.00");
                   if(chargeCompanyInfo.getMoney().compareTo(compare)!=0){
                       flag= ToolUtils.ValidateCode(chargeCompanyInfo.getMoneyCode(),companyId,chargeCompanyInfo.getMoney().toString());
                   }
                    if( flag ){
                        BigDecimal companyMoney =chargeCompanyInfo.getMoney();
                        BigDecimal afterMoney = money.add(companyMoney);
                        afterMoney =afterMoney.setScale(2,BigDecimal.ROUND_DOWN);
                        String afterCode = ToolUtils.CreateCode(afterMoney.toString(),companyId);
                        PayChargeCompany payChargeCompany = new PayChargeCompany();
                        payChargeCompany.setMoney(afterMoney);
                        payChargeCompany.setMoneyCode(afterCode);
                        payChargeCompany.setId(companyId);
                        JpfResponseDto jpfResponseDto = chargeCompanyServiceFacade.updateCompanyRecord(payChargeCompany);
                        if(jpfResponseDto.getRetCode().equals("0000")){ //更新成功
                            ret = 1;
                        }

                    }else{
                        throw new JpfException(JpfErrorInfo.DAL_ERROR, "金额校验失败，公司："+chargeCompanyInfo.getCompanyName());
                    }

                }
                if( request.getStatus() ==  2){ // 审核驳回
                    ret = 1;
                }
                break;
            case 1:
                if( request.getStatus() ==  1){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前状态已为审核通过！");
                }else if(request.getStatus() == 2){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前已为审核通过不可拒绝！");
                }

            case 2:
                if(request.getStatus()==2){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前状态已为驳回的状态！");
                }else if(request.getStatus()==1){
                    throw new JpfException(JpfErrorInfo.DAL_ERROR, "当前状态已为驳回的状态不可充值！");
                }
            default:
                throw new JpfException(JpfErrorInfo.DAL_ERROR, "不能更新状态为："+request.getStatus());

        }

        if( ret !=1 ){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "更新失败");
        }

        int count = payChargeCompanyChargeMapper.updateByPrimaryKeySelective(payChargeCompanyCharge);
        if( count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }
        return new JpfResponseDto();
    }

}
