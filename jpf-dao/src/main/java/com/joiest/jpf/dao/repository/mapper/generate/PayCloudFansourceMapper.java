package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudFansource;
import com.joiest.jpf.common.po.PayCloudFansourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudFansourceMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudFansourceExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudFansourceExample example);

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
    int insert(PayCloudFansource record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudFansource record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudFansource> selectByExample(PayCloudFansourceExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudFansource selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudFansource record, @Param("example") PayCloudFansourceExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudFansource record, @Param("example") PayCloudFansourceExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudFansource record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudFansource record);
}