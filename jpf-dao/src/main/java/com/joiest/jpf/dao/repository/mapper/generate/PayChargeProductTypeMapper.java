package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeProductType;
import com.joiest.jpf.common.po.PayChargeProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeProductTypeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeProductTypeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeProductTypeExample example);

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
    int insert(PayChargeProductType record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeProductType record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeProductType> selectByExample(PayChargeProductTypeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeProductType selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeProductType record, @Param("example") PayChargeProductTypeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeProductType record, @Param("example") PayChargeProductTypeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeProductType record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeProductType record);
}