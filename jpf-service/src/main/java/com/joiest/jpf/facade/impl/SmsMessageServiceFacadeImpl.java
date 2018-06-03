package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PaySmsMessage;
import com.joiest.jpf.dao.repository.mapper.generate.PaySmsMessageMapper;
import com.joiest.jpf.entity.SmsMessageInfo;
import com.joiest.jpf.facade.SmsMessageServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.Date;

public class SmsMessageServiceFacadeImpl implements SmsMessageServiceFacade {

    @Autowired
    private PaySmsMessageMapper paySmsMessageMapper;

    @Override
    public int insRecord(SmsMessageInfo smsMessageInfo){
        smsMessageInfo.setAddtime(new Date());
        PaySmsMessage paySmsMessage = new PaySmsMessage();
        BeanCopier beanCopier = BeanCopier.create(SmsMessageInfo.class, PaySmsMessage.class, false);
        beanCopier.copy(smsMessageInfo,paySmsMessage,null);

        return paySmsMessageMapper.insertSelective(paySmsMessage);
    }
}
