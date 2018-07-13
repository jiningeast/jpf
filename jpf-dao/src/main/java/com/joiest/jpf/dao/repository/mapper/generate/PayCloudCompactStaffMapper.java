package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudCompactStaff;
import com.joiest.jpf.common.po.PayCloudCompactStaffExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudCompactStaffMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompactStaffExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompactStaffExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudCompactStaff record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompactStaff record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompactStaff> selectByExample(PayCloudCompactStaffExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompactStaff selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompactStaff record, @Param("example") PayCloudCompactStaffExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompactStaff record, @Param("example") PayCloudCompactStaffExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompactStaff record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompactStaff record);
}