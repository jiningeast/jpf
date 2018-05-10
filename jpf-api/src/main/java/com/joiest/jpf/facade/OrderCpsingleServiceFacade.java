package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.OrderCpsingleRequest;
import com.joiest.jpf.dto.OrderCpsingleResponse;
import com.joiest.jpf.dto.UnionPayRefundRequest;
import com.joiest.jpf.entity.OrderCpsingleInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.Map;

public interface OrderCpsingleServiceFacade {

    public int getCpsCount();

    public OrderCpsingleResponse getCps(OrderCpsingleRequest request, UserInfo userInfo, String IP);

    public int insRecord(OrderCpsingleInfo info);

    public JpfResponseDto checkOk(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo, String IP);

    public JpfResponseDto checkNo(OrderCpsingleRequest orderCpsingleRequest, UserInfo userInfo, String IP);

    public Map<String, Object> getPosRequest(String orderid);

    public void unionPayRefund(UnionPayRefundRequest request, String IP);
}
