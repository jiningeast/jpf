package com.joiest.jpf.facade;

import com.joiest.jpf.entity.SystemlogInfo;

import java.util.List;

public interface SystemlogServiceFacade {

    public Integer getCount();

    public List<SystemlogInfo> getLogs(long page, long rows );

    /**
     * @param logtype
     * @return
     */
    public void sysLog( Integer logtype, Integer operator_uid, String operator_name, String ip, String ip1, Integer clients, String tablename, Integer record, String action, String content );
}
