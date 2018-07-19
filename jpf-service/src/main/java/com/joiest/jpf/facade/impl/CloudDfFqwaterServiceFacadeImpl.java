package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PayCloudDfQorderStream;
import com.joiest.jpf.dao.repository.mapper.generate.PayCloudDfQorderStreamMapper;
import com.joiest.jpf.facade.CloudDfFqwaterServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class CloudDfFqwaterServiceFacadeImpl implements CloudDfFqwaterServiceFacade {

    @Autowired
    private PayCloudDfQorderStreamMapper payCloudDfQorderStreamMapper;
    /**
     * 添加查询流水
     * */
    public int addCloudDfFqwater(Map<String,String> map){


        PayCloudDfQorderStream payCloudDfFqwater = new PayCloudDfQorderStream();

        payCloudDfFqwater.setRequestOrderid(map.get("request_orderid"));
        payCloudDfFqwater.setOrderid(map.get("orderid"));
        payCloudDfFqwater.setTranno(map.get("tranNo"));
        payCloudDfFqwater.setTranamt(new BigDecimal(map.get("tranAmt")));
        payCloudDfFqwater.setOrderstatus(map.get("orderStatus"));
        payCloudDfFqwater.setRequestparam(map.get("requestParam"));
        payCloudDfFqwater.setResponseparam(map.get("responseParam"));
        payCloudDfFqwater.setCreated(new Date());

        return payCloudDfQorderStreamMapper.insertSelective(payCloudDfFqwater);
    }
}
