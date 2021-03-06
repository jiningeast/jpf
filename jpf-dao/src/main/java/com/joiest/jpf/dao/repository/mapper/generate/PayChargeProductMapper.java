package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.common.po.PayChargeProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeProductMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeProductExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeProductExample example);

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
    int insert(PayChargeProduct record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeProduct record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeProduct> selectByExample(PayChargeProductExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeProduct selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeProduct record, @Param("example") PayChargeProductExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeProduct record, @Param("example") PayChargeProductExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeProduct record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeProduct record);
}