package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayWeixinUser;
import com.joiest.jpf.common.po.PayWeixinUserExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinUserMapper;
import com.joiest.jpf.entity.WeixinUserInfo;
import com.joiest.jpf.facade.WeixinUserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeixinUserServiceFacadeImpl implements WeixinUserServiceFacade {

    @Autowired
    private PayWeixinUserMapper payWeixinUserMapper;

    /**
     * 获取微信用户基本信息by openid
     * */
    public WeixinUserInfo getWeixinUserByOpenid(String openid,Long id){

        PayWeixinUserExample example = new PayWeixinUserExample();
        PayWeixinUserExample.Criteria c = example.createCriteria();
        c.andOpenidEqualTo(openid);
        c.andMpidEqualTo(id);

        List<PayWeixinUser> getPayWeixinUser = payWeixinUserMapper.selectByExample(example);

        if (getPayWeixinUser.isEmpty()) return null;

        PayWeixinUser payWeixinUser = getPayWeixinUser.get(0);

        WeixinUserInfo weixinUserInfo = new WeixinUserInfo();

        BeanCopier beanCopier = BeanCopier.create(PayWeixinUser.class,WeixinUserInfo.class,false);

        beanCopier.copy(payWeixinUser,weixinUserInfo,null);

        return weixinUserInfo;
    }

    /**
     *添加微信用户基本信息
     * */
    public int addWeixinUser(Map<String ,String> wei){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PayWeixinUser payWeixinUser = new PayWeixinUser();

        payWeixinUser.setSubscribe(new Byte(wei.get("subscribe")));
        payWeixinUser.setMpid(new Long(wei.get("mpid")));
        payWeixinUser.setOpenid(wei.get("openid"));
        payWeixinUser.setNickname(wei.get("nickname"));
        payWeixinUser.setNicknameencode(wei.get("nicknameEncode"));
        payWeixinUser.setSex(new Byte(wei.get("sex")));
        payWeixinUser.setLanguage(wei.get("language"));
        payWeixinUser.setCity(wei.get("city"));
        payWeixinUser.setProvince(wei.get("province"));
        payWeixinUser.setCountry(wei.get("country"));
        payWeixinUser.setHeadimgurl(wei.get("headimgurl"));
        payWeixinUser.setServerheadimg(wei.get("serverheadimg"));

        try {
            payWeixinUser.setSubscribetime(sdf.parse(wei.get("subscribeTime")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        payWeixinUser.setUnionid(wei.get("unionid"));
        payWeixinUser.setRemark(wei.get("remark"));
        payWeixinUser.setGroupid(new Byte(wei.get("groupid")));
        payWeixinUser.setTagidList(wei.get("tagid_list"));
        payWeixinUser.setSubscribeScene(wei.get("subscribe_scene"));;
        payWeixinUser.setQrScene(wei.get("qr_scene"));
        payWeixinUser.setQrSceneStr(wei.get("qr_scene_str"));
        payWeixinUser.setCreated(new Date());

        return payWeixinUserMapper.insertSelective(payWeixinUser);
    }
    /**
     *更新微信用户基本信息通过 openid mpid
     * */
    public int upWeixinUserById(Map<String ,String> wei,Long id){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PayWeixinUser payWeixinUser = new PayWeixinUser();

        payWeixinUser.setId(id);
        payWeixinUser.setSubscribe(new Byte(wei.get("subscribe")));
        payWeixinUser.setNickname(wei.get("nickname"));
        payWeixinUser.setNicknameencode(wei.get("nicknameEncode"));
        payWeixinUser.setSex(new Byte(wei.get("sex")));
        payWeixinUser.setLanguage(wei.get("language"));
        payWeixinUser.setCity(wei.get("city"));
        payWeixinUser.setProvince(wei.get("province"));
        payWeixinUser.setCountry(wei.get("country"));
        payWeixinUser.setHeadimgurl(wei.get("headimgurl"));
        payWeixinUser.setServerheadimg(wei.get("serverheadimg"));

        try {
            payWeixinUser.setSubscribetime(sdf.parse(wei.get("subscribeTime")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        payWeixinUser.setUnionid(wei.get("unionid"));
        payWeixinUser.setRemark(wei.get("remark"));
        payWeixinUser.setGroupid(new Byte(wei.get("groupid")));
        payWeixinUser.setTagidList(wei.get("tagid_list"));
        payWeixinUser.setSubscribeScene(wei.get("subscribe_scene"));;
        payWeixinUser.setQrScene(wei.get("qr_scene"));
        payWeixinUser.setQrSceneStr(wei.get("qr_scene_str"));
        payWeixinUser.setUpdated(new Date());

        return payWeixinUserMapper.updateByPrimaryKeySelective(payWeixinUser);
    }
    /**
     *更新微信用户基本信息通过 openid mpid
     * */
    public int upWeixinUserPartById(Map<String ,String> wei,Long id){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        PayWeixinUser payWeixinUser = new PayWeixinUser();
        payWeixinUser.setId(id);
        payWeixinUser.setSubscribe(new Byte(wei.get("subscribe")));

        return payWeixinUserMapper.updateByPrimaryKeySelective(payWeixinUser);
    }
}
