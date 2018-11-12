package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayChargePullOrder;
import com.joiest.jpf.common.po.PayChargePullOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayChargePullOrderCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargePullOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargePullOrderExample example);

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
    int insert(PayChargePullOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargePullOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargePullOrder> selectByExample(PayChargePullOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargePullOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargePullOrder record, @Param("example") PayChargePullOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargePullOrder record, @Param("example") PayChargePullOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargePullOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargePullOrder record);
}