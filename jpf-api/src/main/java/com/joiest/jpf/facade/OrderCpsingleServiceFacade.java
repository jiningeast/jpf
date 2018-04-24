package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface OrderCpsingleServiceFacade {

    public int getCpsCount();

    public List<OrderCpsingleInfo> getCps(long page, long rows);

    public int insRecord(OrderCpsingleInfo info);

    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo);

    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo);
}
