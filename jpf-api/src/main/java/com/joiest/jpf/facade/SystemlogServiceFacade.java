package com.joiest.jpf.facade;

import com.joiest.jpf.entity.SystemlogInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface SystemlogServiceFacade {

    public Integer getCount();

    public List<SystemlogInfo> getLogs(long page, long rows );

    /**
     * @param logtype
     * @return
     */
    public void sysLog(Integer logtype, UserInfo userInfo, String ip, String ip1, Integer clients, String tablename, String action, String content );
}
