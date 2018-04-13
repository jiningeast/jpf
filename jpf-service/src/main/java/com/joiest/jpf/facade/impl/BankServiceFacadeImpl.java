package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayBank;
import com.joiest.jpf.common.po.PayBankExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayBankMapper;
import com.joiest.jpf.entity.BankInfo;
import com.joiest.jpf.facade.BankServiceFacade;
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
            BankInfo pcaInfo = new BankInfo();
            BeanCopier beanCopier = BeanCopier.create(PayBank.class, BankInfo.class, false);
            beanCopier.copy(payBank, pcaInfo, null);
            infos.add(pcaInfo);
        }

        return infos;
    }

    @Override
    public JpfResponseDto addBank(String paybankname, String tpid, String bankcode){
        PayBankExample example = new PayBankExample();

        PayBank payBank = new PayBank();
        payBank.setPaybankname(paybankname);
        payBank.setTpid(tpid);
        payBank.setBankcode(bankcode);
        Date d = new Date();
        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateNowStr = sdf.format(d);*/
        payBank.setCreated(d);

        int index = payBankMapper.insertSelective(payBank);
        if(index !=1){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "添加失败");
        }
        return new JpfResponseDto();
    }
}
