package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudBankcheck;
import com.joiest.jpf.common.po.PayCloudBankcheckExample;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudBankcheckMapper;
import com.joiest.jpf.entity.CloudBankcheckInfo;
import com.joiest.jpf.facade.CloudBankcheckServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CloudBankcheckServiceFacadeImpl implements CloudBankcheckServiceFacade {

    /**
     * 获取银行鉴权信息通过银行卡号
     * */
    @Autowired
    private PayCloudBankcheckMapper payCloudBankcheckMapper;
    public CloudBankcheckInfo getBankcheckByAccountNo(String accountNo,String type){

        PayCloudBankcheckExample example = new PayCloudBankcheckExample();

        PayCloudBankcheckExample.Criteria c = example.createCriteria();
        c.andAccountnoEqualTo(accountNo);
        c.andTypeEqualTo(new Byte(type));

        List<PayCloudBankcheck> getCloudBankcheck = payCloudBankcheckMapper.selectByExample(example);

        if(getCloudBankcheck == null ||getCloudBankcheck.isEmpty()) return null;

        PayCloudBankcheck payCloudBankcheck = getCloudBankcheck.get(0);

        CloudBankcheckInfo cloudBankcheckInfo = new CloudBankcheckInfo();

        BeanCopier beanCopier = BeanCopier.create(PayCloudBankcheck.class,CloudBankcheckInfo.class,false);

        beanCopier.copy(payCloudBankcheck,cloudBankcheckInfo,null);


        return cloudBankcheckInfo;
    }

    /*
     * 更新鉴权信息
     * */
    public int updateCloudBankcheckById(Map<String,String> bankcheck, BigInteger id){

        PayCloudBankcheck payCloudBankcheck = new PayCloudBankcheck();


        payCloudBankcheck.setId(id);
        payCloudBankcheck.setAccountno(bankcheck.get("accountNo"));
        payCloudBankcheck.setNum(bankcheck.get("num"));
        payCloudBankcheck.setMobile(bankcheck.get("mobile"));
        payCloudBankcheck.setName(bankcheck.get("name"));
        payCloudBankcheck.setType(new Byte(bankcheck.get("type")));
        payCloudBankcheck.setStatus(new Byte(bankcheck.get("status")));
        payCloudBankcheck.setCount(new Integer(bankcheck.get("count")));
        payCloudBankcheck.setRequestparam(bankcheck.get("requestParam"));
        payCloudBankcheck.setResponseparam(bankcheck.get("responseParam"));
        payCloudBankcheck.setApiparam(bankcheck.get("apiParam"));
        payCloudBankcheck.setUpdatetime(new Date());

        return payCloudBankcheckMapper.updateByPrimaryKeySelective(payCloudBankcheck);
        /*
        *
        *
        PayCloudIdenauth idenAuthUp = new PayCloudIdenauth();

        idenAuthUp.setId(id);
        idenAuthUp.setStatus(new Byte(idenAuth.get("status")));
        idenAuthUp.setCount(new Integer(idenAuth.get("count")));
        idenAuthUp.setResponseparam(idenAuth.get("responseParam"));
        idenAuthUp.setApiparam(idenAuth.get("apiParam"));
        idenAuthUp.setUpdatetime(new Date());

        return payCloudIdenauthMapper.updateByPrimaryKeySelective(idenAuthUp);*/
    }

    /*
     * 新增鉴权信息
     * */
    public int addCloudBankcheck(Map<String,String> bankcheck){

        PayCloudBankcheck payCloudBankcheck = new PayCloudBankcheck();

        payCloudBankcheck.setAccountno(bankcheck.get("accountNo"));
        payCloudBankcheck.setNum(bankcheck.get("num"));
        payCloudBankcheck.setMobile(bankcheck.get("mobile"));
        payCloudBankcheck.setName(bankcheck.get("name"));
        payCloudBankcheck.setType(new Byte(bankcheck.get("type")));
        payCloudBankcheck.setStatus(new Byte(bankcheck.get("status")));
        payCloudBankcheck.setCount(new Integer(bankcheck.get("count")));
        payCloudBankcheck.setRequestparam(bankcheck.get("requestParam"));
        payCloudBankcheck.setResponseparam(bankcheck.get("responseParam"));
        payCloudBankcheck.setApiparam(bankcheck.get("apiParam"));
        payCloudBankcheck.setAddtime(new Date());

        //return 1;
        return payCloudBankcheckMapper.insertSelective(payCloudBankcheck);
    }

}

