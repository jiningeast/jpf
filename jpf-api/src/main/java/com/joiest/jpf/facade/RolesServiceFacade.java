package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.dto.ModifyRoleRequest;
import com.joiest.jpf.entity.RolesInfo;

import javax.management.relation.Role;
import javax.management.relation.RoleInfo;
import java.util.List;

public interface RolesServiceFacade {

    /**
     * 获取角色列表
     * @return
     */
    public List<RolesInfo> getRole(String name, String intro, String status,long pageNo,long pageSize);

    /**
     * 获取角色列表统计
     * @return
     */
    public int getRoleCount(String name,String status);

    /**
     * 添加角色
     */
    public JpfResponseDto addRole(String name, String intro);

    /**
     * 获取单个角色信息
     */
    public RolesInfo getRoleOne(Integer id);

    /**
     * 编辑单个角色信息
     */
    public JpfResponseDto modifyRole(ModifyRoleRequest request);

    /**
     * 新增角色
     */
    public JpfResponseDto ModifyRoleRequest(ModifyRoleRequest request);

}
