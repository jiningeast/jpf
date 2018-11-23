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
     * 根据主键更新公司信息
     */
    public JpfResponseDto updateCompanyRecord(PayShopCompany payShopCompany);

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

    /**
     * 根据用户名密码查询用户信息
     * @param userName
     * @param password
     * @return
     */
    PayShopCompany getCompanyByUserNamnAndPasswd(String userName, String password);

    /**
     * 根据id查询商户信息
     * @param companyId
     * @return
     */
    PayShopCompany getById(String companyId);

    /**
     * 开通商户账号
     * @param id
     */
    void openAccount(String id)  throws Exception;
}
