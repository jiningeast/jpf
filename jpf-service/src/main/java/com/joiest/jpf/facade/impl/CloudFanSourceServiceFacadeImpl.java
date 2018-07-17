package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudFansource;
import com.joiest.jpf.common.po.PayCloudFansourceExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudFansourceMapper;
import com.joiest.jpf.entity.CloudFanSourceInfo;
import com.joiest.jpf.facade.CloudFanSourceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CloudFanSourceServiceFacadeImpl implements CloudFanSourceServiceFacade {


    @Autowired
    private PayCloudFansourceMapper payCloudFansourceMapper;
    /**
     * 添加粉丝来源
     * */
    public int addFanSource(Map<String,String> map){

        PayCloudFansource payCloudFansource = new PayCloudFansource();

        payCloudFansource.setCatid(new Integer(map.get("catId")));
        payCloudFansource.setCat(map.get("cat"));
        payCloudFansource.setMobile(map.get("mobile"));
        payCloudFansource.setName(map.get("name"));
        payCloudFansource.setCreated(new Date());

        return payCloudFansourceMapper.insertSelective(payCloudFansource);
    }


    /**
     * 获取粉丝信息通过手机号
     * */
    public CloudFanSourceInfo getFanSourceByMobile(String mobile){

        PayCloudFansourceExample example = new PayCloudFansourceExample();
        PayCloudFansourceExample.Criteria c= example.createCriteria();
        c.andMobileEqualTo(mobile);

        List<PayCloudFansource> getPayCloudFansources = payCloudFansourceMapper.selectByExample(example);
        if(getPayCloudFansources == null || getPayCloudFansources.isEmpty()) return null;

        PayCloudFansource payCloudFansource = getPayCloudFansources.get(0);

        CloudFanSourceInfo cloudFanSourceInfo = new CloudFanSourceInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudFansource.class,CloudFanSourceInfo.class,false);

        beanCopier.copy(payCloudFansource,cloudFanSourceInfo,null);

        return cloudFanSourceInfo;
    }
}
