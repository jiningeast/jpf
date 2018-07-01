package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudStaffBanksInfo;

import java.math.BigInteger;

public interface CloudStaffBanksServiceFacade {

    /**
     * 通过银行卡号、员工号获取员工银行卡信息
     * */
    public CloudStaffBanksInfo getStaffBankByNumSid(String num, BigInteger id);



}
