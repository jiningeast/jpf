package com.joiest.jpf.manage.web.thread;

import com.joiest.jpf.dto.ShopBatchRequest;
import com.joiest.jpf.facade.ShopBatchServiceFacade;
import com.joiest.jpf.manage.web.controller.CloudTaskController;
import com.joiest.jpf.manage.web.util.SpringContextUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletResponse;

public class AddBatchThread implements Runnable {

    private ShopBatchServiceFacade shopBatchServiceFacade;

    private ShopBatchRequest shopBatchRequest;

    private HttpServletResponse httpResponse;

    private static final Logger logger = LogManager.getLogger(CloudTaskController.class);

    public AddBatchThread(ShopBatchRequest shopBatchRequest, HttpServletResponse httpResponse){
        this.shopBatchRequest = shopBatchRequest;
        this.httpResponse = httpResponse;

        ApplicationContext beanFactory= SpringContextUtil.getApplicationContext();
        shopBatchServiceFacade = beanFactory.getBean(ShopBatchServiceFacade.class);
    }

    public void run(){
        // 输出当前线程名称
        String threadName = Thread.currentThread().getName();
        logger.info("=================线程"+threadName+"开始处理=================");

        shopBatchServiceFacade.addBatchCoupon(shopBatchRequest,httpResponse);

        logger.info("=================线程"+threadName+"处理结束=================");
    }
}
