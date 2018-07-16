package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.entity.CloudCompanyBankInfo;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudCompanyBankServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/merch")
public class merchInfoController {

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private CloudCompanyBankServiceFacade cloudCompanyBankServiceFacade;

    @Autowired
    private CloudEmployeeServiceFacade cloudEmployeeServiceFacade;

    @RequestMapping(value = "/getMerchantInfo", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public JSONObject getMerchantInfo(HttpServletRequest request) {
        String merchNo = request.getParameter("merchNo");
        JSONObject jsonObj = new JSONObject();

        CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getMerchInfoByMerchNo(merchNo);
        if( companyInfo == null){
            jsonObj.put("retCode","9999");
            jsonObj.put("retMsg","获取商户信息失败");
            return jsonObj;
        }
        jsonObj.put("merchNo", companyInfo.getMerchNo());//商户平台ID
        jsonObj.put("name", companyInfo.getName());//企业名称
        jsonObj.put("certificate", companyInfo.getCertificate());//营业执照注册号
        jsonObj.put("bslicense", companyInfo.getBslicense());//营业执照影印件
        jsonObj.put("taxpayertype", companyInfo.getTaxpayertype());//纳税人类型
        jsonObj.put("tin", companyInfo.getTin());//纳税人识别号
        jsonObj.put("address", companyInfo.getAddress());//单位注册地址及电话
        jsonObj.put("serviclinkuser", companyInfo.getServiclinkuser());//客户经理
        jsonObj.put("linkphone", companyInfo.getLinkphone());//手机号
        jsonObj.put("linkemail", companyInfo.getLinkemail());//邮箱

        CloudEmployeeInfo employeeInfo = cloudEmployeeServiceFacade.getEmployeeInfoByMerchNo(merchNo);
        if(employeeInfo == null){
            return jsonObj;
        }
        jsonObj.put("linkname", employeeInfo.getLinkname());//联系人姓名
        jsonObj.put("linkphone", employeeInfo.getLinkphone());//联系人手机号
        jsonObj.put("linkemail", employeeInfo.getLinkemail());//联系人邮箱
        /*联系地址 = 省份 + 城市 + 地址详情*/
        jsonObj.put("province", employeeInfo.getProvince());//省份
        jsonObj.put("city", employeeInfo.getCity());//城市
        jsonObj.put("address", employeeInfo.getAddress());//地址详情

        CloudCompanyBankInfo companyBankInfo = cloudCompanyBankServiceFacade.getCompanyBankInfoByMerchNo(merchNo);
        if(companyBankInfo == null){
            return jsonObj;
        }
        jsonObj.put("bankname", companyBankInfo.getBankname());//开户名称
        jsonObj.put("bankno", companyBankInfo.getBankno());//对公账户
        jsonObj.put("banksubname", companyBankInfo.getBanksubname());//开户银行

        jsonObj.put("retCode", "0000");//返回码
        jsonObj.put("retMsg", "查询成功");//返回信息
        return jsonObj;
    }
}
