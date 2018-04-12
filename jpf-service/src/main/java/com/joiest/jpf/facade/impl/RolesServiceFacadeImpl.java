package com.joiest.jpf.facade.impl;

import com.joiest.jpf.common.constant.EnumConstants;
import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.po.PayRoles;
import com.joiest.jpf.common.po.PayRolesExample;
import com.joiest.jpf.common.util.SHA1;
import com.joiest.jpf.dao.repository.mapper.generate.PayRolesMapper;
import com.joiest.jpf.dto.GetValueResponse;
import com.joiest.jpf.dto.LoginVerifyResponse;
import com.joiest.jpf.entity.RolesInfo;
import com.joiest.jpf.facade.RolesServiceFacade;
import com.joiest.jpf.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class RolesServiceFacadeImpl implements RolesServiceFacade {

    private static final Logger logger = LogManager.getLogger(RolesServiceFacadeImpl.class);

    @Autowired
    private PayRolesMapper payRolesMapper;

    @Override
    public List<RolesInfo> getRole(String name, String intro, String status, long pageNo, long pageSize) {
        if (pageNo<=0) {
            pageNo = 1;
        }
        if (pageSize<=0) {
            pageSize = 20;
        }
        PayRolesExample example = new PayRolesExample();
        example.setPageNo(pageNo);
        example.setPageSize(pageSize);
        example.setOrderByClause("id DESC");
        PayRolesExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            c.andNameEqualTo(name);
        }
        List<PayRoles> payUserList = payRolesMapper.selectByExample(example);
        List<RolesInfo> RoleInfoList = new ArrayList<>();
        for (PayRoles PayRoles : payUserList) {
            RolesInfo RolesInfo = new RolesInfo();
            BeanCopier beanCopier = BeanCopier.create(PayRoles.class, RolesInfo.class, false);
            beanCopier.copy(PayRoles, RolesInfo, null);
            RoleInfoList.add(RolesInfo);
        }
        return RoleInfoList;
    }

    @Override
    public int getRoleCount(String name,String status){
        PayRolesExample example = new PayRolesExample();
        PayRolesExample.Criteria c = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            c.andNameEqualTo(name);
        }
        return payRolesMapper.countByExample(example);
    }

    @Override
    public JpfResponseDto addRole(String name, String intro) {
        if (StringUtils.isBlank(name)) {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "角色名不能为空");
        }
        if(StringUtils.isBlank(intro)){
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "简介不能为空");
        }
        PayRolesExample example = new PayRolesExample();
        PayRolesExample.Criteria c = example.createCriteria();
        c.andNameEqualTo(name.trim());
        List<PayRoles> payUserList = payRolesMapper.selectByExample(example);
        if(payUserList!=null&&!payUserList.isEmpty()){
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "角色名已经存在");
        }
        PayRoles PayRoles = new PayRoles();
        PayRoles.setName(name);
        PayRoles.setIntro(SHA1.getInstance().getMySHA1Code(intro));
        int index = payRolesMapper.insertSelective(PayRoles);
        if(index !=1){
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "添加失败");
        }
        return new JpfResponseDto();
    }



}
