package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderCpsingle;
import com.joiest.jpf.common.po.PayOrderCpsingleExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderCpsingleMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderCpsingleExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderCpsingleExample example);

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
    int insert(PayOrderCpsingle record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderCpsingle record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderCpsingle> selectByExample(PayOrderCpsingleExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderCpsingle selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderCpsingle record, @Param("example") PayOrderCpsingleExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderCpsingle record, @Param("example") PayOrderCpsingleExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderCpsingle record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderCpsingle record);
}