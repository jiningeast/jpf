package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.TdorderRequest;
import com.joiest.jpf.dto.TdorderResponse;
import com.joiest.jpf.entity.TdorderInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface TdorderServiceFacade {
    public int getTdordersCount();

    public TdorderResponse getTdorders(TdorderRequest request);

    public JpfResponseDto checkOk(TdorderRequest tdorderRequest, UserInfo userInfo);

    public JpfResponseDto checkNo(TdorderRequest tdorderRequest, UserInfo userInfo);

    public int isAfter24Hours(TdorderRequest tdorderRequest);
}
