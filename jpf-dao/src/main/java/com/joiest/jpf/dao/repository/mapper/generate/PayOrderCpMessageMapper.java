package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderCpMessage;
import com.joiest.jpf.common.po.PayOrderCpMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderCpMessageMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderCpMessageExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderCpMessageExample example);

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
    int insert(PayOrderCpMessage record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderCpMessage record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderCpMessage> selectByExample(PayOrderCpMessageExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderCpMessage selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderCpMessage record, @Param("example") PayOrderCpMessageExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderCpMessage record, @Param("example") PayOrderCpMessageExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderCpMessage record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderCpMessage record);
}