package com.joiest.jpf.market.api.controller;

import com.joiest.jpf.common.util.DateUtils;
import com.joiest.jpf.common.util.Md5Encrypt;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.common.util.ToolUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping("demo")
public class DemoController {

    @RequestMapping("/test")
    @ResponseBody
    public String demo()
    {
       return "111111";
    }

    @RequestMapping("/chargePhone")
    @ResponseBody
    public String chargePhone(){
        int testEnv = 1;
        String url = "";
        Map<String,Object> requestMap = new LinkedHashMap<>();

        if ( testEnv == 1 ){
            // 测试环境
            url = "http://apitest.ofpay.com/onlineorder.do";
            requestMap.put("userid","A08566");      // 商户号
            requestMap.put("userpws","4c625b7861a92c7971cd2029c2fd3c4a");   // 商户密码
            requestMap.put("cardid","140101");      // 快充140101，慢充170101
            requestMap.put("cardnum","50");         // 充值金额
            // requestMap.put("mctype","48");          // 如果是慢充商品必须传如48 表示48小时到账
            requestMap.put("sporder_id", ToolUtils.createOrderid());
            requestMap.put("sporder_time", DateUtils.format(new Date(),DateUtils.Date_FORMAT_YMDHMS));
            requestMap.put("game_userid","15810063151");       // 手机号码
            requestMap.put("md5_str",getPhoneSign(requestMap));       // 签名串
            requestMap.put("ret_url","http://www.baidu.com");    // 返回地址
            requestMap.put("version","6.0");
            requestMap.put("buyNum","5");
        }else{
            // 正式环境
            url = "http://api2.ofpay.com/onlineorder.do";
            requestMap.put("userid","A1408311");      // 商户号
            requestMap.put("userpws",Md5Encrypt.md5("Xinxiang#13579").toLowerCase());   // 商户密码
            requestMap.put("cardid","140101");      // 快充140101，慢充170101
            requestMap.put("cardnum","100");         // 充值金额
            // requestMap.put("mctype","48");          // 如果是慢充商品必须传如48 表示48小时到账
            requestMap.put("sporder_id", ToolUtils.createOrderid());
            requestMap.put("sporder_time", DateUtils.format(new Date(),DateUtils.Date_FORMAT_YMDHMS));
            requestMap.put("game_userid","15810063151");       // 手机号码
            requestMap.put("md5_str",getPhoneSign(requestMap));       // 签名串
            requestMap.put("ret_url","http://www.baidu.com");    // 返回地址
            requestMap.put("version","6.0");
            requestMap.put("buyNum","5");
        }

        return OkHttpUtils.postForm(url,requestMap);
    }

    /**
     * 获取手机充值签名
     */
    public String getPhoneSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + "OFCARD";
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }

    @RequestMapping("chargeOil")
    @ResponseBody
    public String chargeGas(){
        String url = "http://apitest.ofpay.com/onlineorder.do";
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid","A08566");      // 商户号
        requestMap.put("userpws","4c625b7861a92c7971cd2029c2fd3c4a");   // 商户密码
        requestMap.put("cardid","64127500");      // 快充140101，慢充170101
        requestMap.put("cardnum","1");         // 任意充需要待充值面值（1的整数倍) 2.卡充充值这里表示数量
        requestMap.put("sporder_id", ToolUtils.createOrderid());
        requestMap.put("sporder_time", DateUtils.format(new Date(),DateUtils.Date_FORMAT_YMDHMS));
        requestMap.put("game_userid","1000113200018313897");       // 加油卡号（充值账号）中石化：以100011开头共19位、中石油：以90开头共16位
        requestMap.put("chargeType","1");       // 加油卡类型 （1:中石化、2:中石油；默认为1）
        requestMap.put("md5_str",getOilSign(requestMap));       // 签名串
        requestMap.put("ret_url","http://www.baidu.com");    // 返回地址
        requestMap.put("version","6.0");

        return OkHttpUtils.postForm(url,requestMap);
    }

    /**
     * 获取油卡充值签名
     */
    public String getOilSign(Map<String,Object> map){
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + "OFCARD";
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }
}
