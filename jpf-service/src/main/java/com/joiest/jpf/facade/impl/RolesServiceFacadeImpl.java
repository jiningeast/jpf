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
import com.joiest.jpf.dto.ModifyRoleRequest;
import com.joiest.jpf.entity.RolesInfo;
import com.joiest.jpf.facade.RolesServiceFacade;
import com.joiest.jpf.common.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;

import javax.management.relation.RoleInfo;
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
            c.andNameLike( "%" + name.trim() + "%" );
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

    @Override
    public RolesInfo getRoleOne(Integer id)
    {
        if ( id == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "id不能为空");
        }
        PayRoles payRoles = payRolesMapper.selectByPrimaryKey(id);
        RolesInfo rolesInfo = new RolesInfo();
        BeanCopier beanCopier = BeanCopier.create(PayRoles.class, RolesInfo.class, false);
        beanCopier.copy(payRoles,rolesInfo,null);
        return rolesInfo;
    }

    @Override
    public JpfResponseDto modifyRole(ModifyRoleRequest request){

        if ( request.getId() == null )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "ID不能为空");
        }
        PayRoles payRoles = payRolesMapper.selectByPrimaryKey(request.getId());
        if ( payRoles == null )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "角色信息不存在");
        }
        PayRoles payRoles1 = new PayRoles();
        BeanCopier beanCopier = BeanCopier.create(ModifyRoleRequest.class,PayRoles.class,false);
        beanCopier.copy(request,payRoles1,null);
        payRoles1.setId(request.getId());
        payRolesMapper.updateByPrimaryKeySelective(payRoles1);
        return new JpfResponseDto();
    }


    public JpfResponseDto ModifyRoleRequest(ModifyRoleRequest request)
    {
        if ( StringUtils.isBlank(request.getName()))
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "角色名不能为空");
        }
        PayRolesExample payRolesExample = new PayRolesExample();
        PayRolesExample.Criteria c = payRolesExample.createCriteria();
        c.andNameEqualTo(request.getName().trim());
        List<PayRoles> payRolesList = payRolesMapper.selectByExample(payRolesExample);
        if ( payRolesList != null && !payRolesList.isEmpty() )
        {
            throw new JpfException(JpfErrorInfo.RECORD_ALREADY_EXIST, "角色名已经存在");
        }
        request.setCreated(new Date());
        byte status = 0;
        String right = "1,2,3";
        request.setStatus(status);
        request.setRights(right);
        PayRoles payRoles = new PayRoles();
        BeanCopier beanCopier = BeanCopier.create(ModifyRoleRequest.class, PayRoles.class, false);
        beanCopier.copy(request,payRoles,null);
        int res = payRolesMapper.insertSelective(payRoles);
        if ( res != 1 )
        {
            throw new JpfException(JpfErrorInfo.DAL_ERROR, "添加失败");
        }
        return  new JpfResponseDto();
    }

    /**
     * 删除角色
     */
    public JpfResponseDto delRole(String id)
    {
        if ( StringUtils.isBlank(id) )
        {
            throw new JpfException(JpfErrorInfo.INVALID_PARAMETER, "ID不能为空");
        }
        PayRoles payRoles = payRolesMapper.selectByPrimaryKey(Integer.valueOf(id));

        if ( payRoles == null )
        {
            throw new JpfException(JpfErrorInfo.RECORD_NOT_FOUND, "角色信息不存在");
        }

        PayRolesExample example = new PayRolesExample();
        PayRolesExample.Criteria c = example.createCriteria();
        c.andIdEqualTo(Integer.valueOf(id));
        PayRoles payRoles1 = new PayRoles();
        Byte status = 1;
        payRoles1.setStatus(status);
        payRolesMapper.updateByExampleSelective(payRoles1,example);
        return new JpfResponseDto();
    }

}
