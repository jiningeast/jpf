package com.joiest.jpf.facade;

import com.joiest.jpf.entity.PcaInfo;

import java.util.List;

public interface PcaServiceFacade {

    public List<PcaInfo> getPcas(String pid);

}
