package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.dto.ModifyRoleRequest;
import com.joiest.jpf.entity.VirtualInfo;

import java.util.List;

public interface VirtualServiceFacade {

    /**
     * 获取虚拟类列表
     * @return
     */
    public List<VirtualInfo> getVirtual(String cat, String intro, long pageNo,long pageSize);

    /**
     * 获取列表统计
     * @return
     */
    public int getVirtualCount(String cat);

    /**
     *
     * 虚拟功能添加
     */
    public JpfResponseDto addVirtual(String cat, String intro);

}
