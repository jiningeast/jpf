package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayWeixinMp;
import com.joiest.jpf.common.po.PayWeixinMpExample;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinMpMapper;
import com.joiest.jpf.entity.WeixinMapInfo;
import com.joiest.jpf.facade.WeixinMapServiceFacade;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeixinMapServiceFacadeImpl implements WeixinMapServiceFacade {

    @Autowired
    private PayWeixinMpMapper payWeixinMpMapper;

    /**
     * 固定地址
     * */
    public static final String HTTPS_URL = "https://api.weixin.qq.com/";

    public String curTime = null;
    public Date dateTime = null;
    public WeixinMapServiceFacadeImpl(){

        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curTime = myfmt.format(date);
        dateTime = date;
    }

    /*
     * 公众号信息
     * */
    public WeixinMapInfo getWeixinMpByEncrypt(String encrypt){

        PayWeixinMpExample example = new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c = example.createCriteria();
        c.andEncryptEqualTo(encrypt);

        List<PayWeixinMp> getPayWeixinMap = payWeixinMpMapper.selectByExample(example);

        if(getPayWeixinMap.isEmpty()) return null;

        PayWeixinMp payWeixinMp = getPayWeixinMap.get(0);

        WeixinMapInfo weixinMapInfo = new WeixinMapInfo();

        BeanCopier beanCopier = BeanCopier.create(PayWeixinMp.class,WeixinMapInfo.class,false);

        beanCopier.copy(payWeixinMp,weixinMapInfo,null);

        return weixinMapInfo;
    }
    /*
     * 更新公众号信息
     * */
    public int upWeixinMpByEncrypt(String encrypt, Map<String,String> res){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        PayWeixinMpExample example = new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c =  example.createCriteria();
        c.andEncryptEqualTo(encrypt);


        System.out.println("失效更新时间："+res.get("tokenExpires"));

        PayWeixinMp payWeixinMp = new PayWeixinMp();
        payWeixinMp.setAccesstoken(res.get("accessToken"));
        try {
            new Date("2018-08-08 13:15:26");
            payWeixinMp.setTokenexpires(sdf.parse(res.get("tokenExpires")));
            System.out.println("失效更新时间格式："+sdf.parse(res.get("tokenExpires")));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return payWeixinMpMapper.updateByExampleSelective(payWeixinMp,example);
    }

    /**
     * 获取access_token
     * @param weixinMapInfo 公众号信息
     * */
    public String getAccessToken(WeixinMapInfo weixinMapInfo){

        String token = null;
        JSONObject res = new JSONObject();
        //组装数据库数据
        Map<String,String> weixinMp = new HashMap<>();
        if (weixinMapInfo.getTokenexpires() != null && dateTime.before(weixinMapInfo.getTokenexpires())){
            //表示str1date小于str2date

            token  = weixinMapInfo.getAccesstoken();
        }else{
            //调取接口获取access_token

            Map<String, Object> map = new HashMap<>();
            String url = HTTPS_URL+"cgi-bin/token?grant_type=client_credential&appid="+weixinMapInfo.getAppid()+"&secret="+weixinMapInfo.getAppsecret();
            res = JSONObject.fromObject(OkHttpUtils.postForm(url,map));

            token = res.get("access_token").toString();

            Date date = new Date();
            SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            System.out.println("获取到的有效秒数："+res.get("expires_in"));
            System.out.println("当前时间："+myfmt.format(date));

            //计算失效时间时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, new Integer(res.get("expires_in").toString()));
            Date expiresTime = calendar.getTime();

            System.out.println("失效时间："+myfmt.format(expiresTime));

            weixinMp.put("accessToken",token);
            weixinMp.put("tokenExpires",myfmt.format(expiresTime));

            //更新对应公众号信息
            upWeixinMpByEncrypt(weixinMapInfo.getEncrypt(),weixinMp);
        }
        return token;
    }
}
