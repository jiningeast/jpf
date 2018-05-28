package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayBankCard;
import com.joiest.jpf.dao.repository.mapper.generate.PayBankCardMapper;
import com.joiest.jpf.entity.BankCardInfo;
import com.joiest.jpf.facade.BankCardServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class BankCardServiceFacadeImpl implements BankCardServiceFacade {

    @Autowired
    private PayBankCardMapper payBankCardMapper;

    /**
     * 根据卡号获取
     */
    public BankCardInfo getBankCardByCardNO(String cardNo){

        List<PayBankCard> list = payBankCardMapper.customSelectByCardNo(cardNo);
        PayBankCard payBankCard = list.get(0);

        BankCardInfo bankCardInfo = new BankCardInfo();
        BeanCopier beanCopier = BeanCopier.create(PayBankCard.class, BankCardInfo.class, false);
        beanCopier.copy(payBankCard, bankCardInfo, null);

        return bankCardInfo;
    }

}
