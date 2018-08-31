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

}
