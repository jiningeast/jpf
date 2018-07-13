package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudBankcheckInfo;

import java.math.BigInteger;
import java.util.Map;

public interface CloudBankcheckServiceFacade {

    /**
     * 获取银行鉴权信息通过银行卡号
     * */
    public CloudBankcheckInfo getBankcheckByAccountNo(String accountNo,String type);


    /*
     * 更新鉴权信息
     * */
    public int updateCloudBankcheckById(Map<String,String> bankcheck, BigInteger id);

    /*
     * 新增鉴权信息
     * */
    public int addCloudBankcheck(Map<String,String> bankcheck);


}
