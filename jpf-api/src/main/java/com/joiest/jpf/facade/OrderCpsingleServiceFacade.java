package com.joiest.jpf.facade;

import com.joiest.jpf.entity.OrderCpsingleInfo;

import java.util.List;

public interface OrderCpsingleServiceFacade {

    public int getCpsCount();

    public List<OrderCpsingleInfo> getCps(long page, long rows);
}
