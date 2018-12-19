package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("test")
    @ResponseBody
    public void test(){

    }


    //
    @RequestMapping(value = "testOrder",method = RequestMethod.POST,produces = "text/plain;charset=utf-8")
    public String telPlaceOrder(){

//        for (int i = 0; i <20000 ; i++) {
//
//            String orderno = "ZY"+ ToolUtils.createOrderid();
//
//            Map<String, Object> rechargeMap = new HashMap<>();
//            TreeMap<String, Object> actTreeParam = new TreeMap<>();
//            rechargeMap.put("merchNo", "123123");
//            rechargeMap.put("service", "placeOrderVal");
//            rechargeMap.put("outOrderNo", orderno);
//            rechargeMap.put("phone", "13466784752");
//            rechargeMap.put("dateTime", "20181205173300");
//            rechargeMap.put("notifyUrl", "xxx");
//            rechargeMap.put("productId", "1000");
//
//
//            actTreeParam.putAll(rechargeMap);
//
//            String respos = ToolUtils.mapToUrl(actTreeParam);
//            String selfSign = Md5Encrypt.md5(respos+"648BD6026FCE9E593C85E8298C958ACC").toUpperCase();
//            rechargeMap.put("sign", selfSign);
//            String phone_requestUrl = "http://localhost:8083/charge-api/flowRecharge/telPlaceOrder";
//            String resultXml = OkHttpUtils.postForm(phone_requestUrl,rechargeMap);
//
//        }

        return "";
    }

}
