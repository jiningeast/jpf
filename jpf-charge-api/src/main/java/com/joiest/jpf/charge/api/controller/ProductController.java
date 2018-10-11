package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.ToolUtils;
import com.joiest.jpf.entity.ChargeCompanyInfo;
import com.joiest.jpf.facade.ChargeCompanyServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ChargeCompanyServiceFacade chargeCompanyServiceFacade;

    @RequestMapping(value = "/flowProduct",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String flowProduct(HttpServletRequest request, HttpServletResponse response){

        //商户号
        String account = request.getParameter("account");
        //时间戳
        String timestamp = request.getParameter("timestamp");
        //运营商类型 1=移动 2=联通 3=电信 0=所有  为0返回所有运营商的套餐
        int carrier = Integer.parseInt(request.getParameter("carrier"));
        //签名串
        String sign = request.getParameter("sign");

        //参数不合法
        if(StringUtils.isBlank(account) || StringUtils.isBlank(timestamp) || (carrier < 0 || carrier>3 ) ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INVALID_PARAMETER.getCode(), JpfInterfaceErrorInfo.INVALID_PARAMETER.getDesc(), null);
        }
        //缺少签名参数
        if( sign== null || StringUtils.isBlank(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.NO_SIGN.getCode(), JpfInterfaceErrorInfo.NO_SIGN.getDesc(), null);
        }

        //商户密码
        ChargeCompanyInfo record = new ChargeCompanyInfo();
        record.setMerchNo(account);
        ChargeCompanyInfo result = chargeCompanyServiceFacade.getOne(record);
        //商户不存在
        if(result==null ){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getCode(), JpfInterfaceErrorInfo.MER_GETINFO_FAIL.getDesc(), null);
        }
        //商户删除 或者  商户关闭服务

        String  privateKey = result.getPrivateKey();

        //校验来源数据是否合法
        String newSign = Md5Encrypt.md5(account+Md5Encrypt.md5(privateKey)+timestamp+carrier);
        if(!newSign.equals(sign)){
            return ToolUtils.toJsonBase64(JpfInterfaceErrorInfo.INCORRECT_SIGN.getCode(), JpfInterfaceErrorInfo.INCORRECT_SIGN.getDesc(), null);
        }

        //查询商品列表
        PayChargeProduct chargeProduct = new PayChargeProduct();
        chargeProduct.setMobileType((byte)carrier);

        StringBuilder sbf = new StringBuilder();
        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sbf.append("\n\nTime:" + myfmt.format(date));
        sbf.append("\n请求参数："+request.getQueryString());
        sbf.append("\n事件参数："+request.toString());
        sbf.append("\nsign："+sign);
        String fileName = "FlowProduct";
        LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-charge-api/log/", fileName,true);


        return "";
    }
}
