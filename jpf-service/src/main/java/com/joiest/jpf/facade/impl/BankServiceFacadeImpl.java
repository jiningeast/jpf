package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayBank;
import com.joiest.jpf.common.po.PayBankExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayBankMapper;
import com.joiest.jpf.entity.BankInfo;
import com.joiest.jpf.facade.BankServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BankServiceFacadeImpl implements BankServiceFacade {

    @Autowired
    private PayBankMapper payBankMapper;

    /**
     * 获取计数
     * @return
     */
    @Override
    public Integer getBankCount(){
        PayBankExample example = new PayBankExample();
        int count = payBankMapper.countByExample(example);

        return count;
    }

    /**
     * 获取银行列表
     * @param page
     * @param rows
     * @return
     */
    @Override
    public List<BankInfo> getBank( long page, long rows){
        PayBankExample example = new PayBankExample();
        if ( page <= 0 ) {
            page = 1;
        }else
        {
            example.setPageNo(page);
        }

        if ( rows <= 0 ) {
            rows = 10;
        }else
        {
            example.setPageSize(rows);
        }
        List<PayBank> list = payBankMapper.selectByExample(example);
        List<BankInfo> infos = new ArrayList<>();
        for (PayBank payBank : list) {
            BankInfo bankInfo = new BankInfo();
            BeanCopier beanCopier = BeanCopier.create(PayBank.class, BankInfo.class, false);
            beanCopier.copy(payBank, bankInfo, null);
            infos.add(bankInfo);
        }

        return infos;
    }

    @Override
    public JpfResponseDto addBank(String paybankname, String tpid, String bankcode){
        if ( StringUtils.isBlank(paybankname) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "银行名称不能为空");
        }
        if ( StringUtils.isBlank(tpid) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "银行类型不能为空");
        }
        if ( StringUtils.isBlank(bankcode) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "银行编码不能为空");
        }
        PayBank payBank = new PayBank();
        // 银行名称
        payBank.setPaybankname(paybankname);
        // 银行类型 去掉末尾的':'
        tpid = StringUtils.stripEnd(tpid, ":");
        payBank.setTpid(tpid);
        // 银行编码
        payBank.setBankcode(bankcode);
        // 创建日期
        Date d = new Date();
        payBank.setCreated(d);
        payBank.setUpdated(d);

        int index = payBankMapper.insertSelective(payBank);
        if(index !=1){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "添加失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public BankInfo getBankInfo(String id){
        if ( StringUtils.isBlank(id) ){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id参数错误");
        }
        Long primeId = Long.parseLong(id);
        PayBank payBank = payBankMapper.selectByPrimaryKey(primeId);

        BankInfo bankInfo = new BankInfo();
        bankInfo.setId(payBank.getId());
        bankInfo.setPaybankname(payBank.getPaybankname());
        bankInfo.setBankcode(payBank.getBankcode());
        bankInfo.setTpid(payBank.getTpid());
        bankInfo.setCreated(payBank.getCreated());

        return  bankInfo;
    }

    @Override
    public JpfResponseDto editBank(String id, String paybankname, String tpid, String bankcode){
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        if ( StringUtils.isBlank(paybankname) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "银行名称不能为空");
        }
        if ( StringUtils.isBlank(tpid) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "银行类型不能为空");
        }
        if ( StringUtils.isBlank(bankcode) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "银行编码不能为空");
        }

        PayBank payBank = new PayBank();
        Long primeId = Long.parseLong(id);
        payBank.setId(primeId);
        payBank.setPaybankname(paybankname);
        tpid = StringUtils.stripEnd(tpid,":");
        payBank.setTpid(tpid);
        payBank.setBankcode(bankcode);
        Date date = new Date();
        payBank.setUpdated(date);
        payBankMapper.updateByPrimaryKeySelective(payBank);

        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto delBank(String id){
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }

        PayBankExample e = new PayBankExample();
        PayBankExample.Criteria c = e.createCriteria();
        Long primeId = Long.parseLong(id);
        c.andIdEqualTo(primeId);
        List<PayBank> list = payBankMapper.selectByExample(e);
        if ( list==null||list.isEmpty() )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "此记录不存在");
        }
        payBankMapper.deleteByPrimaryKey(primeId);

        return new JpfResponseDto();
    }
}
