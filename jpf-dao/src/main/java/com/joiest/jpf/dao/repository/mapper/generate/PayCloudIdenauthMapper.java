package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudIdenauth;
import com.joiest.jpf.common.po.PayCloudIdenauthExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudIdenauthMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudIdenauthExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudIdenauthExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(BigInteger id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudIdenauth record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudIdenauth record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudIdenauth> selectByExample(PayCloudIdenauthExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudIdenauth selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudIdenauth record, @Param("example") PayCloudIdenauthExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudIdenauth record, @Param("example") PayCloudIdenauthExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudIdenauth record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudIdenauth record);
}