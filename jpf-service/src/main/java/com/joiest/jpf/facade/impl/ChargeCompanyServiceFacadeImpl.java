package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyExample;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.util.ConfigUtil;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.dao.repository.mapper.custom.PayChargeCompanyCustomMapper;
import com.joiest.jpf.dao.repository.mapper.generate.PayChargeCompanyMapper;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChargeCompanyServiceFacadeImpl implements ChargeCompanyServiceFacade {

    @Autowired
    private PayChargeCompanyMapper payChargeCompanyMapper;

    @Autowired
    private PayChargeCompanyCustomMapper payChargeCompanyCustomMapper;

    /**
     * 获取商户列表
     */
    @Override
    public GetChargeCompanyResponse getRecords(GetChargeCompanyRequest request){
        GetChargeCompanyResponse response = new GetChargeCompanyResponse();

        PayChargeCompanyExample e = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = e.createCriteria();
        c.andIsDelEqualTo((byte)0);
        if (StringUtils.isNotBlank(""+request.getPage()) && request.getPage() > 0 ){
            e.setPageNo(request.getPage());
        }else{
            e.setPageNo(1);
        }
        if (StringUtils.isNotBlank(""+request.getRows()) && request.getRows() > 0 ){
            e.setPageSize(request.getRows());
        }else{
            e.setPageSize(10);
        }
        if ( request.getMerchNo() != null && StringUtils.isNotBlank(request.getMerchNo()) ){
            c.andMerchNoEqualTo(request.getMerchNo());
        }
        if ( request.getCompanyName() != null && StringUtils.isNotBlank(request.getCompanyName()) ){
            c.andCompanyNameLike("%"+request.getCompanyName()+"%");
        }
        if ( request.getIsFreeze() != null && request.getIsFreeze() == 1 ){
            c.andIsFreezeEqualTo((byte)0);
        }else if ( request.getIsFreeze() != null && request.getIsFreeze() == 2 ){
            c.andIsFreezeEqualTo((byte)1);
        }
        e.setOrderByClause("id DESC");

        List<PayChargeCompany> list =  payChargeCompanyMapper.selectByExample(e);
        response.setCount(payChargeCompanyMapper.countByExample(e));
        List<ChargeCompanyInfo> infos = new ArrayList<>();
        for (PayChargeCompany one:list){
            ChargeCompanyInfo info = new ChargeCompanyInfo();
            BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
            beanCopier.copy(one,info,null);

            infos.add(info);
        }
        response.setList(infos);

        return response;
    }

    /**
     * 根据商户号获取商户
     * */
    @Override
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo){

        PayChargeCompanyExample example = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = example.createCriteria();
        if(companyInfo.getMerchNo() != null ){
            c.andMerchNoEqualTo(companyInfo.getMerchNo());
        }

        List<PayChargeCompany> list = payChargeCompanyMapper.selectByExample(example);
        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeCompanyInfo info = new ChargeCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
        beanCopier.copy(list.get(0),info,null);
        return info;
    }
    /**
     * 根据商户号获取商户
     * */
    @Override
    public ChargeCompanyInfo getRecordByMerchNo(String merchNO){

        PayChargeCompanyExample example = new PayChargeCompanyExample();
        PayChargeCompanyExample.Criteria c = example.createCriteria();
        if(StringUtils.isNotBlank(merchNO)){
            c.andMerchNoEqualTo(merchNO);
        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "商户号不能为空");
        }

        List<PayChargeCompany> list = payChargeCompanyMapper.selectByExample(example);
        if( list == null || list.size() <=0  ){
            return null;
        }
        ChargeCompanyInfo info = new ChargeCompanyInfo();
        BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
        beanCopier.copy(list.get(0),info,null);
        return info;
    }
    /**
     * 根据主键id获取商户
     */
    @Override
    public ChargeCompanyInfo getRecordByPrimaryKey(String id){
        PayChargeCompany payChargeCompany = payChargeCompanyMapper.selectByPrimaryKey(id);
        ChargeCompanyInfo chargeCompanyInfo = new ChargeCompanyInfo();
        if ( payChargeCompany != null ){
            BeanCopier beanCopier = BeanCopier.create(PayChargeCompany.class,ChargeCompanyInfo.class,false);
            beanCopier.copy(payChargeCompany,chargeCompanyInfo,null);
        }

        return chargeCompanyInfo;
    }

    /**
     * 添加商户
     */
    @Override
    public int addRecord(ChargeCompanyInfo chargeCompanyInfo){
        PayChargeCompany payChargeCompany = new PayChargeCompany();
        if ( chargeCompanyInfo != null ){
            BeanCopier beanCopier = BeanCopier.create(ChargeCompanyInfo.class,PayChargeCompany.class,false);
            beanCopier.copy(chargeCompanyInfo,payChargeCompany,null);
        }
        String merchNo = "MC"+System.currentTimeMillis()+ToolUtils.getRandomInt(100000,999999);     // 生成商户号
        String privateKey = ToolUtils.createPrivateKey(16);     // 生成秘钥
        payChargeCompany.setMerchNo(merchNo);
        payChargeCompany.setPrivateKey(privateKey);
        payChargeCompany.setMoney(new BigDecimal(0));
        payChargeCompany.setIsDel((byte)0);
        payChargeCompany.setAddtime(new Date());
        payChargeCompanyCustomMapper.insertSelective(payChargeCompany);
        int ret = 0;
        if( StringUtils.isNotBlank(payChargeCompany.getId()) ){
            //初始化金额校验码
            String keyStr = ConfigUtil.getValue("MERCH_VALIDE_CODE");
            BigDecimal money = new BigDecimal("0.00");
            String newCode = ToolUtils.CreateCode(money.toString(),payChargeCompany.getId(),keyStr);
            PayChargeCompany chargeCompany = new PayChargeCompany();
            chargeCompany.setMoneyCode(newCode);
            chargeCompany.setId(payChargeCompany.getId());
            int upCou = payChargeCompanyMapper.updateByPrimaryKeySelective(chargeCompany);
            if( upCou != 1 ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作失败");
            }else{
                ret = 1;
            }
        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "操作异常");
        }

        return ret;
    }

    /**
     * 更新商户
     */
    @Override
    public int updateColumnByPrimaryKey(ChargeCompanyInfo chargeCompanyInfo){
        PayChargeCompany payChargeCompany = new PayChargeCompany();
        if ( chargeCompanyInfo != null ){
            BeanCopier beanCopier = BeanCopier.create(ChargeCompanyInfo.class,PayChargeCompany.class,false);
            beanCopier.copy(chargeCompanyInfo,payChargeCompany,null);
        }

        return payChargeCompanyMapper.updateByPrimaryKeySelective(payChargeCompany);
    }
    /**
     * 公司单条记录
     */
    @Override
    public JpfResponseDto updateCompanyRecord(PayChargeCompany payChargeCompany)
    {
        if ( StringUtils.isBlank(payChargeCompany.getId()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        int count = payChargeCompanyMapper.updateByPrimaryKeySelective(payChargeCompany);
        if(count != 1 ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
        }

        return new JpfResponseDto();
    }

    /**
     * 修改用户密码
     */
    @Override
    public JpfResponseDto updatePassword(String merchNo,String oldPass,String newPass)
    {
        if(StringUtils.isNotBlank(merchNo)&& StringUtils.isNotBlank(oldPass)&& StringUtils.isNotBlank(newPass)){
           //修改数据
           PayChargeCompanyExample example=new PayChargeCompanyExample();
           PayChargeCompanyExample.Criteria c=example.createCriteria();
           c.andMerchNoEqualTo(merchNo);
           List<PayChargeCompany> listCom=payChargeCompanyMapper.selectByExample(example);
           if(listCom==null || listCom.isEmpty()){
               throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "此信息不存在");
           }
           PayChargeCompany company=new PayChargeCompany();
           String newpass= Md5Encrypt.md5(newPass);
           company.setPassword(newpass);

           int count=payChargeCompanyMapper.updateByExampleSelective(company,example);

            if(count != 1 ){
                throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "更新失败");
            }

        }else{
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "参数错误");
        }

        return new JpfResponseDto();
    }
}
