package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayUser;
import com.joiest.jpf.common.po.PayUserExample;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.dao.repository.mapper.generate.PayUserMapper;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.dto.ModifyUserRequest;
import com.joiest.jpf.entity.UserInfo;
import com.joiest.jpf.facade.UserServiceFacade;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;

public class UserServiceFacadeImpl implements UserServiceFacade {

    private static final Logger logger = LogManager.getLogger(UserServiceFacadeImpl.class);

    @Autowired
    private PayUserMapper payUserMapper;

    @Override
    public List<UserInfo> getUsers(String userName, String status, long pageNo, long pageSize) {
        if (pageNo<=0) {
            pageNo = 1;
        }
        if (pageSize<=0) {
            pageSize = 20;
        }
        PayUserExample example = new PayUserExample();
        example.setPageNo(pageNo);
        example.setPageSize(pageSize);
        example.setOrderByClause("CREATE_TIME DESC");
        PayUserExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(userName)) {
            c.andUserNameEqualTo(userName);
        }
        if (StringUtils.isNotBlank(status)) {
            c.andStatusEqualTo(status);
        }
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        List<UserInfo> userInfoList = new ArrayList<>();
        for (PayUser payUser : payUserList) {
            UserInfo userInfo = new UserInfo();
            BeanCopier beanCopier = BeanCopier.create(PayUser.class, UserInfo.class, false);
            beanCopier.copy(payUser, userInfo, null);
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

    @Override
    public int getUsersCount(String userName,String status){
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(userName)) {
            c.andUserNameEqualTo(userName);
        }
        if (StringUtils.isNotBlank(status)) {
            c.andStatusEqualTo(status);
        }
        return payUserMapper.countByExample(example);
    }

    @Override
    public JpfResponseDto addUser(String userName, String pwd) {
        if (StringUtils.isBlank(userName)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名不能为空");
        }
        if(StringUtils.isBlank(pwd)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "密码不能为空");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList!=null&&!payUserList.isEmpty()){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "用户已经存在");
        }
        PayUser payUser = new PayUser();
        payUser.setUserName(userName);
        payUser.setPassword(SHA1.getInstance().getMySHA1Code(pwd));
        payUser.setStatus(EnumConstants.UserStatus.normal.value());
        int index = payUserMapper.insertSelective(payUser);
        if(index !=1){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "添加失败");
        }
        return new JpfResponseDto();
    }

    @Override
    public LoginVerifyResponse loginVerify(String userName, String pwd) {
        if (StringUtils.isBlank(userName)) {
            logger.debug("请求参数错误：用户名不能为空");
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名或密码错误！");
        }
        if (StringUtils.isBlank(pwd)) {
            logger.debug("请求参数错误：密码不能为空");
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名或密码错误！");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList==null||payUserList.isEmpty()){
            logger.debug("用户信息不存在userName={}",userName);
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "用户不存在！");
        }
        PayUser payUser = payUserList.get(0);
        if(!SHA1.getInstance().getMySHA1Code(pwd).equals(payUser.getPassword())){
            logger.debug("密码错误userName={}",userName);
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名或密码错误！");
        }
        if(!EnumConstants.UserStatus.normal.value().equals(payUser.getStatus())){
            logger.debug("状态异常status={}",payUser.getStatus());
            throw new JpfException(JpfErrorInfo.STATUS_ERROR, "用户状态异常！");
        }
        UserInfo userInfo = new UserInfo();
        BeanCopier beanCopier = BeanCopier.create(PayUser.class, UserInfo.class, false);
        beanCopier.copy(payUser,userInfo,null);

        LoginVerifyResponse loginVerifyResponse= new LoginVerifyResponse();
        loginVerifyResponse.setUserInfo(userInfo);
        return loginVerifyResponse;
    }

    @Override
    public JpfResponseDto modifyPwd(String userName, String oldPwd, String newPwd) {
        if (StringUtils.isBlank(userName)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名不能为空");
        }
        if(StringUtils.isBlank(oldPwd)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "原密码不能为空");
        }
        if (StringUtils.isBlank(newPwd)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "新密码不能为空");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList==null||payUserList.isEmpty()){
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "用户不存在！");
        }
        PayUser payUser = payUserList.get(0);
        if(!SHA1.getInstance().getMySHA1Code(oldPwd).equals(payUser.getPassword())){
            throw new JpfException(JpfErrorInfo.PASSWORD_ERROR, "密码不正确");
        }
        if(!EnumConstants.UserStatus.normal.value().equals(payUser.getStatus())){
            throw new JpfException(JpfErrorInfo.STATUS_ERROR, "状态异常不可以操作");
        }

        PayUser user = new PayUser();
        user.setId(payUser.getId());
        user.setPassword(SHA1.getInstance().getMySHA1Code(newPwd));
        payUserMapper.updateByPrimaryKeySelective(user);
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto resetPwd(String userName){
        if (StringUtils.isBlank(userName)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名不能为空");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName.trim());
        List<PayUser> payUserList = payUserMapper.selectByExample(example);
        if(payUserList==null||payUserList.isEmpty()){
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "用户不存在！");
        }
        PayUser payUser = payUserList.get(0);
        if(!EnumConstants.UserStatus.normal.value().equals(payUser.getStatus())){
            throw new JpfException(JpfErrorInfo.STATUS_ERROR, "状态异常不可以操作");
        }

        PayUser user = new PayUser();
        user.setId(payUser.getId());
        user.setPassword(SHA1.getInstance().getMySHA1Code("abc123"));
        payUserMapper.updateByPrimaryKeySelective(user);
        return new JpfResponseDto();
    }

    @Override
    public JpfResponseDto alterStatus(String userName, String status) {
        if (StringUtils.isBlank(userName)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "用户名不能为空");
        }
        if (StringUtils.isBlank(status)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "状态不能为空");
        }
        if(!EnumConstants.UserStatus.normal.value().equals(status)&&!EnumConstants.UserStatus.forbid.value().equals(status)){
            throw new JpfException(JpfErrorInfo.STATUS_ERROR, "状态不匹配");
        }
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andUserNameEqualTo(userName);
        PayUser user = new PayUser();
        user.setStatus(status);
        payUserMapper.updateByExampleSelective(user,example);
        return new JpfResponseDto();
    }

    @Override
    public UserInfo getUserOne(Integer id) {

        if ( id == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayUser payUser = payUserMapper.selectByPrimaryKey(id);
        UserInfo userInfo = new UserInfo();
        BeanCopier beanCopier = BeanCopier.create(PayUser.class, UserInfo.class, false);
        beanCopier.copy(payUser,userInfo,null);
        return userInfo;
    }

    /**
     * 密码修改
     * @param oldPwd
     * @return
     */
    public JpfResponseDto modifyUserPwd(ModifyUserRequest request){

        if ( request.getId() == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "ID不能为空");
        }
        if ( request.getPassword()== null || request.getPassword().length() <6 )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "密码不能为空且长度不小于6位");
        }

        if ( request.getPassword().equals( request.getConfirmPassword()) == false )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "密码不一样");
        }

        PayUser payUser = payUserMapper.selectByPrimaryKey(request.getId());
        if ( payUser == null )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "客户信息不存在");
        }

        /*//已下代码如果修改表单数据名 会导致数据一起修改   有漏洞
        PayUser payUser1 = new PayUser();
        BeanCopier beanCopier = BeanCopier.create(ModifyUserRequest.class,PayUser.class,false);
        beanCopier.copy(request,payUser1,null);
        payUser1.setId(request.getId());
        payUserMapper.updateByPrimaryKeySelective(payUser1);*/

        //修改指定数据表字段
        PayUserExample example = new PayUserExample();
        PayUserExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(request.getId());
        PayUser payUser1 = new PayUser();
        payUser1.setPassword(SHA1.getInstance().getMySHA1Code(request.getPassword()));
        payUserMapper.updateByExampleSelective(payUser1,example);

        return new JpfResponseDto();
    }

}
