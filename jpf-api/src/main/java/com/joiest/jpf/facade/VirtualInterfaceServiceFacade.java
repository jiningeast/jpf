package com.joiest.jpf.facade;


import com.joiest.jpf.entity.VirtualInfo;

public interface VirtualInterfaceServiceFacade {

    /**
     * 根据关联ID获取分期费率信息
     */
    public VirtualInfo getInfoByRelateId(Integer relateId);
}
