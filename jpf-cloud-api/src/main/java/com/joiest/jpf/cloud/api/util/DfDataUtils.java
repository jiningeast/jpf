package com.joiest.jpf.cloud.api.util;

import com.joiest.jpf.dto.GetCloudMoneyDfResponse;
import com.joiest.jpf.entity.CloudDfMoneyInterfaceInfo;
import com.joiest.jpf.facade.CloudDfMoneyServiceFacade;

import com.joiest.jpf.facade.CloudDfOrderInterfaceServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

//数据获取写入
public class DfDataUtils extends Thread{

    private String batchid;

    private String dfid;

    //df_money list
    private List<CloudDfMoneyInterfaceInfo> list;

    private String batchid_self;

    private CloudDfOrderInterfaceServiceFacade cloudDfOrderInterfaceServiceFacade;

    volatile boolean keepRunning = true;

    public DfDataUtils() {


    }


    public DfDataUtils(String batchid, String dfid, List<CloudDfMoneyInterfaceInfo> list, String batchid_self) {

        this.batchid = batchid;
        this.dfid = dfid;
        this.list = list;
        int flag = 0;
        this.batchid_self = batchid_self;

        ApplicationContext beanFactory= SpringContextUtil.getApplicationContext();
        cloudDfOrderInterfaceServiceFacade = beanFactory.getBean(CloudDfOrderInterfaceServiceFacade.class);
        System.out.println("33333333333333333 ");
    }

    @Override
    public void run() {

        int count = 0;

        System.out.println("【" + getName() + "】 : 开始执行");
        while ( keepRunning )
        {
//            MySQL mysql = new MySQL();
//            ResultSet result = null;
//            Statement state = mysql.MysqlOpen();
            try {
                int res_add = 0;
                while (keepRunning)
                {
                    //添加数据
                    res_add = cloudDfOrderInterfaceServiceFacade.addOrder(batchid, dfid, list,batchid_self);
                    if ( res_add == 1)
                    {
                        keepRunning = false;
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
//            } finally {
//                try {
//
//                    result.close();
//                    state.close();
//                    MySQL.con.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "执行： " + count + " 次");
        }
        super.run();
    }

    public static void main(String[] args) {
        System.out.println("开始。。。。。。。。。。。。。。。。。");
        List<CloudDfMoneyInterfaceInfo> list = new ArrayList<>();
//        Thread dfDataUtils = new DfDataUtils("131","29",list);
//        dfDataUtils.setName("测试--线程");
//        dfDataUtils.start();
        System.out.println("结束。。。。。。。。。。。。。。。。。");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束111111111。。。。。。。。。。。。。。。。。");


    }

}
