package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudDfQorderStream;
import com.joiest.jpf.common.po.PayCloudDfQorderStreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudDfQorderStreamMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudDfQorderStreamExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudDfQorderStreamExample example);

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
    int insert(PayCloudDfQorderStream record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudDfQorderStream record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudDfQorderStream> selectByExample(PayCloudDfQorderStreamExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudDfQorderStream selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudDfQorderStream record, @Param("example") PayCloudDfQorderStreamExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudDfQorderStream record, @Param("example") PayCloudDfQorderStreamExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudDfQorderStream record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudDfQorderStream record);
}