package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayWeixinUser;
import com.joiest.jpf.common.po.PayWeixinUserExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinUserMapper;
import com.joiest.jpf.entity.WeixinUserInfo;
import com.joiest.jpf.facade.WeixinUserServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class WeixinUserServiceFacadeImpl implements WeixinUserServiceFacade {

    @Autowired
    private PayWeixinUserMapper payWeixinUserMapper;

    /**
     * 获取微信用户基本信息by openid
     * */
    public WeixinUserInfo getWeixinMapByOpenid(String openid){

        PayWeixinUserExample example = new PayWeixinUserExample();
        PayWeixinUserExample.Criteria c = example.createCriteria();
        c.andOpenidEqualTo(openid);

        List<PayWeixinUser> getPayWeixinUser = payWeixinUserMapper.selectByExample(example);

        if (getPayWeixinUser.isEmpty()) return null;

        PayWeixinUser payWeixinUser = getPayWeixinUser.get(0);

        WeixinUserInfo weixinUserInfo = new WeixinUserInfo();

        BeanCopier beanCopier = BeanCopier.create(PayWeixinUser.class,WeixinUserInfo.class,false);

        beanCopier.copy(payWeixinUser,weixinUserInfo,null);

        return weixinUserInfo;
    }

    /**
     *
     * */
}
