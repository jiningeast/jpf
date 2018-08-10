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
        String url = "http://apitest.ofpay.com/onlineorder.do";
        Map<String,Object> requestMap = new LinkedHashMap<>();
        requestMap.put("userid","A08566");      // 商户号
        requestMap.put("userpws","4c625b7861a92c7971cd2029c2fd3c4a");   // 商户密码
        requestMap.put("cardid","140101");      // 快充140101，慢充170101
        requestMap.put("cardnum","50");         // 充值金额
        // requestMap.put("mctype","48");          // 如果是慢充商品必须传如48 表示48小时到账
        requestMap.put("sporder_id", ToolUtils.createOrderid());
        requestMap.put("sporder_time", DateUtils.format(new Date(),DateUtils.Date_FORMAT_YMDHMS));
        requestMap.put("game_userid","15810063151");       // 手机号码
        requestMap.put("md5_str",getOfPaySign(requestMap));       // 签名串
        requestMap.put("ret_url","http://www.baidu.com");    // 返回地址
        requestMap.put("version","6.0");
        requestMap.put("buyNum","5");

        return OkHttpUtils.postForm(url,requestMap);
    }

    public String getOfPaySign(Map<String,Object> map){
//        String myPackage = map.get("userid").toString() +'+'+ map.get("userpws") +'+'+ map.get("cardid") +'+'+ map.get("cardnum") +'+'+ map.get("sporder_id") +'+'+ map.get("sporder_time") +'+'+ map.get("game_userid") +'+'+ "OFCARD";
        String myPackage = map.get("userid").toString() + map.get("userpws") + map.get("cardid") + map.get("cardnum") + map.get("sporder_id") + map.get("sporder_time") + map.get("game_userid") + "OFCARD";
        String sign = Md5Encrypt.md5(myPackage).toUpperCase();

        return sign;
    }
}
