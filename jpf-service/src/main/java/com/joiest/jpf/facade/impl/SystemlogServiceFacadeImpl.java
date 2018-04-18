package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.po.PaySystemlog;
import com.joiest.jpf.common.po.PaySystemlogExample;
import com.joiest.jpf.dao.repository.mapper.generate.PaySystemlogMapper;
import com.joiest.jpf.entity.SystemlogInfo;
import com.joiest.jpf.facade.SystemlogServiceFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import com.joiest.jpf.facade.MerTypeServiceFacade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SystemlogServiceFacadeImpl implements SystemlogServiceFacade {

    @Autowired
    private PaySystemlogMapper paySystemlogMapper;

    @Autowired
    private MerTypeServiceFacade type;

    @Override
    public Integer getCount(){
        PaySystemlogExample e = new PaySystemlogExample();
        Integer count =paySystemlogMapper.countByExample(e);

        return count;
    }

    @Override
    public List<SystemlogInfo> getLogs( long page, long rows ){
        // 查询系统日志
        PaySystemlogExample e = new PaySystemlogExample();
        e.setPageNo(page);
        e.setPageSize(rows);
        List<PaySystemlog> list = paySystemlogMapper.selectByExampleWithBLOBs(e);
        List<SystemlogInfo> infos = new ArrayList<>();
        for (PaySystemlog paySystemlog : list) {
            SystemlogInfo info = new SystemlogInfo();
            BeanCopier beanCopier = BeanCopier.create(PaySystemlog.class, SystemlogInfo.class,false);
            beanCopier.copy( paySystemlog, info, null );

            infos.add(info);
        }

        return infos;
    }

    /**
     *
     * @param logtype 0：前台 1：后台
     * @param operator_uid 用户uid
     * @param operator_name 用户姓名
     * @param ip 用户ip
     * @param ip1 用户唯一识别码，如：微信登录时的第三方识别码
     * @param clients 用户客户端id， 32：PC 33：IOS 34：android
     * @param tablename 表名
     * @param record 暂时存储用户uid
     * @param action 操作类型，如：删除数据，修改数据，添加数据等
     * @param content sql语句合集
     */
    @Override
    public void sysLog( Integer logtype, Integer operator_uid, String operator_name, String ip, String ip1, Integer clients, String tablename, Integer record, String action, String content) {

        PaySystemlog paySystemlog = new PaySystemlog();
        paySystemlog.setLogtype(logtype);
        paySystemlog.setOperatorUid(operator_uid);
        paySystemlog.setOperatorName(operator_name);
        paySystemlog.setIp(ip);
        paySystemlog.setIp1(ip1);
        paySystemlog.setClients(clients);
        paySystemlog.setTablename(tablename);
        String recordStr = Integer.toString(record);
        paySystemlog.setRecord(recordStr);
        paySystemlog.setAction(action);
        paySystemlog.setContent(content);
        Date date = new Date();
        paySystemlog.setCreated(date);

        paySystemlogMapper.insertSelective(paySystemlog);
    }
}
