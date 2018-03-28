package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.entity.UserInfo;

import java.util.List;

public interface UserServiceFacade {

    /**
     * 获取用户列表
     * @param userName
     * @param status
     * @return
     */
    public List<UserInfo> getUsers(String userName,String status);

    /**
     * 添加用户
     * @param userName
     * @param pwd
     * @return
     */
    public JpfResponseDto addUser(String userName, String pwd);

    /**
     * 登录校验
     * @param userName
     * @param pwd
     * @return
     */
    public LoginVerifyResponse loginVerify(String userName, String pwd);

    /**
     * 重置密码
     * @param userName
     * @param oldPwd
     * @param newPwd
     * @return
     */
    public JpfResponseDto resetPwd(String userName,String oldPwd,String newPwd);

    /**
     * 变更状态（0：正常,1:禁用）
     * @param userName
     * @param status
     * @return
     */
    public JpfResponseDto alterStatus(String userName,String status);

}
