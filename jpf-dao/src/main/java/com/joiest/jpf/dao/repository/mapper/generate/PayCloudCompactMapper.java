package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudCompact;
import com.joiest.jpf.common.po.PayCloudCompactExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudCompactMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompactExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompactExample example);

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
    int insert(PayCloudCompact record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompact record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompact> selectByExample(PayCloudCompactExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompact selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompact record, @Param("example") PayCloudCompactExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompact record, @Param("example") PayCloudCompactExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompact record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompact record);
}