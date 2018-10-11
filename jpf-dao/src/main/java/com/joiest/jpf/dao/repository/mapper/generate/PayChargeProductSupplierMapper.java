package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeProductSupplier;
import com.joiest.jpf.common.po.PayChargeProductSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeProductSupplierMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeProductSupplierExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeProductSupplierExample example);

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
    int insert(PayChargeProductSupplier record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeProductSupplier record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeProductSupplier> selectByExample(PayChargeProductSupplierExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeProductSupplier selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeProductSupplier record, @Param("example") PayChargeProductSupplierExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeProductSupplier record, @Param("example") PayChargeProductSupplierExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeProductSupplier record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeProductSupplier record);
}