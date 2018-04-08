package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.AddMerPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeRequest;
import com.joiest.jpf.dto.GetMerchPayTypeResponse;

import java.util.List;

public interface MerPayTypeServiceFacade {

    public GetMerchPayTypeResponse getMerPayTypes(GetMerchPayTypeRequest request);

    public JpfResponseDto addMerPayType(AddMerPayTypeRequest request);

    public JpfResponseDto deleteMerPayType(List<Long> id);
}
