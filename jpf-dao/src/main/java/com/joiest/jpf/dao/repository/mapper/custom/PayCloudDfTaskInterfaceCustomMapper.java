package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayCloudDfTask;
import com.joiest.jpf.common.po.PayCloudDfTaskExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayCloudDfTaskInterfaceCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudDfTaskExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudDfTaskExample example);

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
    int insert(PayCloudDfTask record);

    /**
     * 插入数据库记录
     * 返回主键ID---custom
     * @param record
     */
    int insertSelective(PayCloudDfTask record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudDfTask> selectByExample(PayCloudDfTaskExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudDfTask selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudDfTask record, @Param("example") PayCloudDfTaskExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudDfTask record, @Param("example") PayCloudDfTaskExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudDfTask record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudDfTask record);
}