package com.joiest.jpf.facade;

import com.joiest.jpf.entity.TdorderInfo;

import java.util.List;

public interface TdorderServiceFacade {
    public int getTdordersCount();

    public List<TdorderInfo> getTdorders(long page, long rows);
}
