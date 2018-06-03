package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderRefundMessage;
import com.joiest.jpf.common.po.PayOrderRefundMessageExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderRefundMessageMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderRefundMessageExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderRefundMessageExample example);

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
    int insert(PayOrderRefundMessage record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderRefundMessage record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderRefundMessage> selectByExample(PayOrderRefundMessageExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderRefundMessage selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderRefundMessage record, @Param("example") PayOrderRefundMessageExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderRefundMessage record, @Param("example") PayOrderRefundMessageExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderRefundMessage record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderRefundMessage record);
}