package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.dto.GetChargeCompanyRequest;
import com.joiest.jpf.dto.GetChargeCompanyResponse;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.entity.ChargeOrderInfo;
import net.sf.json.JSONObject;

import java.util.List;

public interface ChargeCompanyServiceFacade {

    /**
     * 获取商户列表
     */
    public GetChargeCompanyResponse getRecords(GetChargeCompanyRequest request);

    /**
     * 查询商户信息
     */
    public ChargeCompanyInfo getOne(ChargeCompanyInfo companyInfo);

    /**
     * 根据主键id获取商户
     */
    public ChargeCompanyInfo getRecordByPrimaryKey(String id);

    /**
     * 根据商户号获取商户
     */
    public ChargeCompanyInfo getRecordByMerchNo(String merchNO);
    /**
     * 添加商户
     */
    public int addRecord(ChargeCompanyInfo chargeCompanyInfo);

    /**
     * 更新商户
     */
    public int updateColumnByPrimaryKey(ChargeCompanyInfo chargeCompanyInfo);

    /**
     * 根据主键更新公司信息
     */
    public JpfResponseDto updateCompanyRecord(PayChargeCompany payChargeCompany);

    /**
     * 充值失败返还商户资金
     * */
    public JSONObject returnComfunds(ChargeOrderInfo orderInfo)  throws Exception;


    /**
     * 用户修改密码
     */
    public JpfResponseDto updatePassword(String merchNo,String oldPass,String newPass);

    /**
     * 获取用户列表
     */

    List<PayChargeCompany> getCompanyList();

    /**
     * 校正商户余额
     * @param company
     */
    void reviseCompanyCharge(PayChargeCompany company);
    /**
        * 退款
     * @param companyInfo
     * @param orderInfo
     */
    void addCompanyMoney(ChargeCompanyInfo companyInfo, ChargeOrderInfo orderInfo) throws Exception;
}
