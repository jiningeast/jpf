package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayCloudTask;
import com.joiest.jpf.common.po.PayCloudTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayCloudTaskCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudTaskExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudTaskExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudTask record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudTask record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudTask> selectByExample(PayCloudTaskExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudTask selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudTask record, @Param("example") PayCloudTaskExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudTask record, @Param("example") PayCloudTaskExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudTask record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudTask record);
}