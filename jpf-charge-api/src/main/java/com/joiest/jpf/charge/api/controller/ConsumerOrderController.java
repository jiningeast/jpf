package com.joiest.jpf.charge.api.controller;

import com.joiest.jpf.facade.ConsumerOrderServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 充值记录下单，获取接口
 */
public class ConsumerOrderController {
    @Autowired
    private ConsumerOrderServiceFacade consumerOrderServiceFacade;
}
