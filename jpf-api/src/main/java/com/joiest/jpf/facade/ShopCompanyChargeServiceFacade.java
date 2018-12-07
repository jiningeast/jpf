package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.dto.GetShopCompanyChargeRequest;
import com.joiest.jpf.dto.GetShopCompanyChargeResponse;
import com.joiest.jpf.entity.ShopCompanyChargeInfo;

import java.util.List;
import java.util.Map;

public interface ShopCompanyChargeServiceFacade {

    /**
     * 公司列表---后台
     */
    public GetShopCompanyChargeResponse getList(GetShopCompanyChargeRequest request);

    /**
     * 公司列表---后台
     */
    public ShopCompanyChargeInfo getOne(String order_no);

    /**
     * 公司列表---后台
     */
    public JpfResponseDto add(GetShopCompanyChargeRequest request);

    /**
     * 财务审核充值---后台
     */
    public JpfResponseDto auditCompanyCharge(GetShopCompanyChargeRequest request);

    /**
     * 财务审核充值---后台
     */
    public JpfResponseDto caiWuCompanyCharge(GetShopCompanyChargeRequest request);

    /**
     * 查询可以使用的企业合同3
     * @param companyId
     * @return
     */
    List<PayShopCompanyCharge> getListByCompanyId(String companyId);

    /**
     * 根据id查询合同
     * @param contractId
     * @return
     */
    PayShopCompanyCharge getById(String contractId);

    /**
     * 分页查询可用合同
     * @param map
     * @return
     */
    List<PayShopCompanyCharge> getListByCompanyIdByPage(Map<String, Object> map);

    /**
     * 查询可用合同总数
     * @param map
     * @return
     */
    Integer getTotal(Map<String, Object> map);
}
