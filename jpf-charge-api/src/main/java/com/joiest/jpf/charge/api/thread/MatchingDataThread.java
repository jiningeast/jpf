package com.joiest.jpf.charge.api.thread;

import com.joiest.jpf.charge.api.controller.OrderQueryController;
import com.joiest.jpf.charge.api.util.SpringContextUtil;
import com.joiest.jpf.dto.MarchingDataRequest;
import com.joiest.jpf.facade.ChargePullOrderServiceFacade;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletResponse;

public class MatchingDataThread implements Runnable {

    private ChargePullOrderServiceFacade chargePullOrderServiceFacade;

    private MarchingDataRequest marchingDataRequest;

    private HttpServletResponse httpResponse;

    private static final Logger logger = LogManager.getLogger(OrderQueryController.class);

    public MatchingDataThread(MarchingDataRequest marchingDataRequest, HttpServletResponse httpResponse){
        this.marchingDataRequest = marchingDataRequest;
        this.httpResponse = httpResponse;

        ApplicationContext beanFactory= SpringContextUtil.getApplicationContext();
        chargePullOrderServiceFacade = beanFactory.getBean(ChargePullOrderServiceFacade.class);
    }

    public void run(){
        // 输出当前线程名称
        String threadName = Thread.currentThread().getName();
        logger.info("=================线程"+threadName+"开始处理=================");

        chargePullOrderServiceFacade.matchingDataByThread(marchingDataRequest,httpResponse);

        logger.info("=================线程"+threadName+"处理结束=================");
    }
}
