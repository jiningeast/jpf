package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayBankCard;

import java.util.List;

public interface PayBankCardCustomMapper {

    /**
     * 根据银行卡号查找银行
     */
    List<PayBankCard> selectByCardNo(String cardNo);
}
