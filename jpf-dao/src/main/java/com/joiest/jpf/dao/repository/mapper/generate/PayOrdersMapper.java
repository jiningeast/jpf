package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrders;
import com.joiest.jpf.common.po.PayOrdersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrdersMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrdersExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrdersExample example);

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
    int insert(PayOrders record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrders record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrders> selectByExample(PayOrdersExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrders selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrders record, @Param("example") PayOrdersExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrders record, @Param("example") PayOrdersExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrders record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrders record);
}