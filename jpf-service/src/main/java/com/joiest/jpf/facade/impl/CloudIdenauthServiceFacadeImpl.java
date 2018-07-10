package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudIdenauth;
import com.joiest.jpf.common.po.PayCloudIdenauthExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudIdenauthMapper;
import com.joiest.jpf.entity.CloudIdenauthInfo;
import com.joiest.jpf.facade.CloudIdenauthServiceFacade;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CloudIdenauthServiceFacadeImpl implements CloudIdenauthServiceFacade {

    @Autowired
    private PayCloudIdenauthMapper payCloudIdenauthMapper;

    /*
     * 新增认证信息
     * */
    public int addCloudIdenauth(Map<String,String> idAuth){

        PayCloudIdenauth idenAuth = new PayCloudIdenauth();

        idenAuth.setName(idAuth.get("name"));
        idenAuth.setNum(idAuth.get("num"));
        idenAuth.setStatus(new Byte(idAuth.get("status")));
        idenAuth.setCount(new Integer(idAuth.get("count")));
        idenAuth.setResponseparam(idAuth.get("responseParam"));
        idenAuth.setApiparam(idAuth.get("apiParam"));
        idenAuth.setAddtime(new Date());

        //return 1;
        return payCloudIdenauthMapper.insertSelective(idenAuth);
    }
    /*
     * 更新增认证信息
     * */
    public int updateCloudIdenauthById(Map<String,String> idenAuth,BigInteger id){

        PayCloudIdenauth idenAuthUp = new PayCloudIdenauth();

        idenAuthUp.setId(id);
        idenAuthUp.setStatus(new Byte(idenAuth.get("status")));
        idenAuthUp.setCount(new Integer(idenAuth.get("count")));
        idenAuthUp.setResponseparam(idenAuth.get("responseParam"));
        idenAuthUp.setApiparam(idenAuth.get("apiParam"));
        idenAuthUp.setUpdatetime(new Date());

        return payCloudIdenauthMapper.updateByPrimaryKeySelective(idenAuthUp);
    }

    /*
     * 查询认证信息通过身份证号、姓名
     * */
    public CloudIdenauthInfo getCloudIdenauthByNumName(String name,String num){

        PayCloudIdenauthExample example = new PayCloudIdenauthExample();

        PayCloudIdenauthExample.Criteria c = example.createCriteria();
        c.andNameEqualTo(name);
        c.andNumEqualTo(num);

        List<PayCloudIdenauth> getIdenauthInfo = payCloudIdenauthMapper.selectByExample(example);
        if (getIdenauthInfo == null || getIdenauthInfo.isEmpty()){ return null; }

        PayCloudIdenauth payCloudIdenauth  = getIdenauthInfo.get(0);

        CloudIdenauthInfo cloudIdenauthInfo = new CloudIdenauthInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudIdenauth.class,CloudIdenauthInfo.class,false);

        beanCopier.copy(payCloudIdenauth,cloudIdenauthInfo,null);

        return cloudIdenauthInfo;
    }
}
