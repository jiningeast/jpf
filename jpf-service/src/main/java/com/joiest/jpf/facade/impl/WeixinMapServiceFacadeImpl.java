package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayWeixinMp;
import com.joiest.jpf.common.po.PayWeixinMpExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayWeixinMpMapper;
import com.joiest.jpf.entity.WeixinMapInfo;
import com.joiest.jpf.facade.WeixinMapServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.List;

public class WeixinMapServiceFacadeImpl implements WeixinMapServiceFacade {

    @Autowired
    private PayWeixinMpMapper payWeixinMpMapper;
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
}
