package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayOrderPayMessage;
import com.joiest.jpf.common.po.PayOrderPayMessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayOrderPayMessageCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderPayMessageExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderPayMessageExample example);

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
    int insert(PayOrderPayMessage record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderPayMessage record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderPayMessage> selectByExample(PayOrderPayMessageExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderPayMessage selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderPayMessage record, @Param("example") PayOrderPayMessageExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderPayMessage record, @Param("example") PayOrderPayMessageExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderPayMessage record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderPayMessage record);
}