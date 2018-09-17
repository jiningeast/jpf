package com.joiest.jpf.facade;

import com.joiest.jpf.entity.BankCardInfo;

public interface BankCardServiceFacade {

    /**
     * 根据卡号获取
     */
    public BankCardInfo getBankCardByCardNO(String cardNo);

    /**
     * 根据卡号获取
     */
    public BankCardInfo cloudBankCardByCardNO(String cardNo);
}
