package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.TdorderRequest;
import com.joiest.jpf.entity.TdorderInfo;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface TdorderServiceFacade {
    public int getTdordersCount();

    public List<TdorderInfo> getTdorders(long page, long rows);

    public JpfResponseDto checkOk(TdorderRequest tdorderRequest);

    public JpfResponseDto checkNo(TdorderRequest tdorderRequest, UserInfo userInfo);
}
