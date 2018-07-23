package com.joiest.jpf.cloud.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.CloudCompanyBankInfo;
import com.joiest.jpf.entity.CloudCompanyInfo;
import com.joiest.jpf.entity.CloudEmployeeInfo;
import com.joiest.jpf.facade.CloudCompanyBankServiceFacade;
import com.joiest.jpf.facade.CloudCompanyServiceFacade;
import com.joiest.jpf.facade.CloudEmployeeServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/merch")
public class merchInfoController {

    private static final Logger logger = LogManager.getLogger(merchInfoController.class);

    @Autowired
    private CloudCompanyServiceFacade cloudCompanyServiceFacade;

    @Autowired
    private CloudCompanyBankServiceFacade cloudCompanyBankServiceFacade;

    @Autowired
    private CloudEmployeeServiceFacade cloudEmployeeServiceFacade;

    @RequestMapping(value = "/getMerchantInfo", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getMerchantInfo(HttpServletRequest request) {

        logger.info("token={}", request.getHeader("token"));
        String token = request.getParameter("token");
        CloudEmployeeInfo cloudEmployeeInfo = cloudEmployeeServiceFacade.companyIsLogin(token);
        String merchNo = cloudEmployeeInfo.getMerchNo();
        Map<String, String> merchInfo = new HashMap<String, String>();
        try {
            CloudCompanyInfo companyInfo = cloudCompanyServiceFacade.getMerchInfoByMerchNo(merchNo);
            merchInfo.put("merchNo", companyInfo.getMerchNo());//商户平台ID
            merchInfo.put("name", companyInfo.getName());//企业名称
            merchInfo.put("certificate", companyInfo.getCertificate());//营业执照注册号
            merchInfo.put("bslicense", companyInfo.getBslicense());//营业执照影印件
            merchInfo.put("taxpayertype", companyInfo.getTaxpayertype());//纳税人类型
            merchInfo.put("tin", companyInfo.getTin());//纳税人识别号
            merchInfo.put("address", companyInfo.getAddress());//单位注册地址及电话
            merchInfo.put("serviclinkuser", companyInfo.getServiclinkuser());//客户经理
            merchInfo.put("phone", companyInfo.getLinkphone());//手机号
            merchInfo.put("email", companyInfo.getLinkemail());//邮箱
            CloudEmployeeInfo employeeInfo = cloudEmployeeServiceFacade.getEmployeeInfoByMerchNo(merchNo);
            merchInfo.put("linkname", employeeInfo.getLinkname());//联系人姓名
            merchInfo.put("linkphone", employeeInfo.getLinkphone());//联系人手机号
            merchInfo.put("linkemail", employeeInfo.getLinkemail());//联系人邮箱
             /*联系地址 = 省份 + 城市 + 地址详情*/
            merchInfo.put("province", employeeInfo.getProvince().toString());//省份
            merchInfo.put("city", employeeInfo.getCity().toString());//城市
            merchInfo.put("address", employeeInfo.getAddress());//地址详情
            CloudCompanyBankInfo companyBankInfo = cloudCompanyBankServiceFacade.getCompanyBankInfoByMerchNo(merchNo);
            merchInfo.put("bankname", companyBankInfo.getBankname());//开户名称
            merchInfo.put("bankno", companyBankInfo.getBankno());//对公账户
            merchInfo.put("banksubname", companyBankInfo.getBanksubname());//开户银行
        }catch(Exception e) {
            e.printStackTrace();
        }
        return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.SUCCESS.getCode(), JpfInterfaceErrorInfo.SUCCESS.getDesc(), merchInfo);
    }
}
