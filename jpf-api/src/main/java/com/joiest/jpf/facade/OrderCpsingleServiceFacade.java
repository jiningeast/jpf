package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.dto.OrderCpsingleResponse;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.entity.UserInfo;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface OrderCpsingleServiceFacade {

    public int getCpsCount();

    public OrderCpsingleResponse getCps(OrderCpsingleRequest request);

    public int insRecord(OrderCpsingleInfo info);

    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo);

    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo);

    public Map<String, Object> getPosRequest(String orderid);
}
