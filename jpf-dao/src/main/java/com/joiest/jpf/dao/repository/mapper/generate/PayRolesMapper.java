package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayRoles;
import com.joiest.jpf.common.po.PayRolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayRolesMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayRolesExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayRolesExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayRoles record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayRoles record);

    /**
     *
     * @param example
     */
    List<PayRoles> selectByExampleWithBLOBs(PayRolesExample example);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayRoles> selectByExample(PayRolesExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayRoles selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayRoles record, @Param("example") PayRolesExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") PayRoles record, @Param("example") PayRolesExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayRoles record, @Param("example") PayRolesExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayRoles record);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(PayRoles record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayRoles record);
}