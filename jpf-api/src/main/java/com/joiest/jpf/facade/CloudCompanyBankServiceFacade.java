package com.joiest.jpf.facade;

import com.joiest.jpf.entity.CloudCompanyBankInfo;

public interface CloudCompanyBankServiceFacade {
    /**
     * 商户后台
     * 根据商户编号获取云账户企业对公账户信息表的信息
     */
    public CloudCompanyBankInfo getCompanyBankInfoByMerchNo(String merchNo);
}
