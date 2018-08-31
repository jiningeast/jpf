package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.dto.GetShopCompanyRequest;
import com.joiest.jpf.dto.GetShopCompanyResponse;
import com.joiest.jpf.entity.ShopCompanyInfo;

public interface ShopCompanyServiceFacade {

    /**
     * 公司列表---后台
     */
    public GetShopCompanyResponse getList (GetShopCompanyRequest request);

    /**
     * 公司添加
     */
    public JpfResponseDto addCompany(GetShopCompanyRequest request,int account);

    /**
     * 获取公司单条信息
     */

    public ShopCompanyInfo getCompanyOne(String id);
    /**
     * 公司添加
     */
    public JpfResponseDto editCompany(GetShopCompanyRequest request,int account);

    /**
     * 公司停用和启用
     */
    public JpfResponseDto delCompanyShop(String merchNo,int type);

    /**
     * 公司充值
     */
    public int charge(String companyId,double chargeMoney);

    /**
     * 获取最新的余额校验码
     */
    public String getMoneyCode(String companyId, String money);

    /**
     * 验证金额校验码的准确性
     */
    public boolean checkMoneyCode(String companyId);

    /**
     * 通过企业名称获取企业
     */
    public PayShopCompany getCompanyByName(String companyName);
}
