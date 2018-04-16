package com.joiest.jpf.facade;

import com.joiest.jpf.entity.SystemlogInfo;

import java.util.List;

public interface SystemlogServiceFacade {

    public Integer getCount();

    public List<SystemlogInfo> getLogs(long page, long rows );
}
