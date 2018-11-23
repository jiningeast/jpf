package com.joiest.jpf.market.api.util;

import com.joiest.jpf.facade.ShopOrderInfoInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 定时任务工具类
 * @author admin 
 */
@Component("timerTaskUtils")
public class TimerTaskUtils implements Runnable{
    
    @Autowired
    private ShopOrderInfoInterfaceServiceFacade shopOrderInfoInterfaceServiceFacade;
    
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        shopOrderInfoInterfaceServiceFacade.timerDetectShopOrderAndCancel(ToolsUtils.getBeforeHourTimeReturnDate(24));
    }
}
