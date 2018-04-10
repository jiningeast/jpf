package com.joiest.jpf.facade;

import com.joiest.jpf.entity.PcaInfo;

import java.util.List;

public interface PcaServiceFacade {

    /**
     * 获取省市区。不传之默认获取省
     * @param pid
     * @return
     */
    public List<PcaInfo> getPcas(String pid);

    /**
     * 分页获取地区信息
     * @param page
     * @param pageSize
     * @return
     */
    public List<PcaInfo> getPca(long page, long pageSize);

    public Integer getPcaCount();
}
