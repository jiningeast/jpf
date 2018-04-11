package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.entity.RolesInfo;

import java.util.List;

public interface RolesServiceFacade {

    /**
     * 获取用户列表
     * @param name
     * @param status
     * @return
     */
    public List<RolesInfo> getRole(String name, String intro, String status,long pageNo,long pageSize);

    /**
     * 获取用户列表统计
     * @param name
     * @param status
     * @return
     */
    public int getRoleCount(String name,String status);

    /**
     * 添加用户
     * @param name
     * @param intro
     * @return
     */
    public JpfResponseDto addRole(String name, String intro);





}
