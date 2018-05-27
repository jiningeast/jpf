package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.facade.ChinaPayServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
//import com.joiest.jpf.manage.web.controller.OrderCpsingleController;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.Set;

public class ChinaPayServiceFacadeImpl implements ChinaPayServiceFacade{
   // private static final Logger logger = LogManager.getLogger(OrderCpsingleController.class);
   @Autowired
   private YjResponseDto yjResponseDto;

    private String signType = "MD5";
    private String charset = "UTF-8";
    public ChinaPayServiceFacadeImpl(){

    }
    /*
    * 银联发送短信
    * @param map请求参数
    * @param requestUrl 请求地址
    * */
    public YjResponseDto ChinaPaySmsCodeSend(Map<String,Object> map,String requestUrl){

        String privatekey = map.get("privatekey").toString();
        map.remove("privatekey");
        map.put("inputCharset",this.charset);

        String respos = this.signData(map);
        String sign = Md5Encrypt.md5(respos+privatekey);
        String requestParam = respos+"&sign="+sign+"&signType="+this.signType;

        map.put("sign",sign);
        map.put("signType",this.signType);

        //logger.info("接口地址为"+requestUrl);
        String response = OkHttpUtils.postForm(requestUrl,map);

        yjResponseDto.setData(response);

        return yjResponseDto;
    }

    /*
    * 银联退款
    * @param map请求参数
    * @param requestUrl请求地址
    * */
    public YjResponseDto ChinaPayRefund(Map<String,Object> map,String requestUrl){

        String privatekey = map.get("privatekey").toString();
        map.remove("privatekey");
        map.put("inputCharset",this.charset);

        String respos = this.signData(map);
        String sign = Md5Encrypt.md5(respos+privatekey);
        String requestParam = respos+"&sign="+sign+"&signType="+this.signType;

        map.put("sign",sign);
        map.put("signType",this.signType);

        //logger.info("接口地址为"+requestUrl);
        String response = OkHttpUtils.postForm(requestUrl,map);

        yjResponseDto.setData(response);

        return yjResponseDto;
    }
    public String signData(Map<String,Object> map){

        String result="";
        Set<String> keys = map.keySet();

        for(String key :keys){

            result +=key+"="+map.get(key)+"&";
        }
        return result.substring(0,result.length()-1);
    }
}
