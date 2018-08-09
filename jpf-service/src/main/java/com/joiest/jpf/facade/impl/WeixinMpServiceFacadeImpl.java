package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayWeixinMp;
import com.joiest.jpf.common.po.PayWeixinMpExample;
import com.joiest.jpf.common.util.LogsCustomUtils;
import com.joiest.jpf.common.util.OkHttpUtils;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinMpMapper;
import com.joiest.jpf.entity.WeixinMpInfo;
import com.joiest.jpf.facade.WeixinMpServiceFacade;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WeixinMpServiceFacadeImpl implements WeixinMpServiceFacade {

    @Autowired
    private PayWeixinMpMapper payWeixinMpMapper;

    /**
     * 固定地址
     * */
    public static final String HTTPS_URL = "https://api.weixin.qq.com/";

    public String curTime = null;
    public Date dateTime = null;
    public WeixinMpServiceFacadeImpl(){

        Date date = new Date();
        SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        curTime = myfmt.format(date);
        dateTime = date;
    }

    /*
     * 公众号信息
     * */
    public WeixinMpInfo getWeixinMpByEncrypt(String encrypt){

        PayWeixinMpExample example = new PayWeixinMpExample();
        PayWeixinMpExample.Criteria c = example.createCriteria();
        c.andEncryptEqualTo(encrypt);

        List<PayWeixinMp> getPayWeixinMap = payWeixinMpMapper.selectByExample(example);

        if(getPayWeixinMap.isEmpty()) return null;

        PayWeixinMp payWeixinMp = getPayWeixinMap.get(0);

        WeixinMpInfo weixinMapInfo = new WeixinMpInfo();

        BeanCopier beanCopier = BeanCopier.create(PayWeixinMp.class,WeixinMpInfo.class,false);

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


        PayWeixinMp payWeixinMp = new PayWeixinMp();
        payWeixinMp.setAccesstoken(res.get("accessToken"));
        try {

            payWeixinMp.setTokenexpires(sdf.parse(res.get("tokenExpires")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return payWeixinMpMapper.updateByExampleSelective(payWeixinMp,example);
    }
    /**
     * 获取access_token
     * @param weixinMapInfo 公众号信息
     * */
    public String getAccessToken(WeixinMpInfo weixinMapInfo){

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

            //计算失效时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.SECOND, new Integer(res.get("expires_in").toString()));
            Date expiresTime = calendar.getTime();

            weixinMp.put("accessToken",token);
            weixinMp.put("tokenExpires",myfmt.format(expiresTime));

            StringBuilder sbf = new StringBuilder();
            sbf.append("\n\nTime:" + curTime);
            sbf.append("\n请求类型：微信公众号获取access_token");
            sbf.append("\n请求地址："+url);
            sbf.append("\n公众号参数："+JSONObject.fromObject(weixinMapInfo)).toString();
            sbf.append("\n获取access_token接口参数："+res);
            String fileName = "WeixinAccessTokenLog";
            LogsCustomUtils.writeIntoFile(sbf.toString(),"/logs/jpf-cloud-api/log/", fileName,true);


            //更新对应公众号信息
            upWeixinMpByEncrypt(weixinMapInfo.getEncrypt(),weixinMp);
        }
        return token;
    }
}
