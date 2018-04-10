package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchantsProduct;
import com.joiest.jpf.common.po.PayMerchantsProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsProductMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsProductExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsProductExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param pid
     */
    int deleteByPrimaryKey(Long pid);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayMerchantsProduct record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchantsProduct record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchantsProduct> selectByExample(PayMerchantsProductExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param pid
     */
    PayMerchantsProduct selectByPrimaryKey(Long pid);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchantsProduct record, @Param("example") PayMerchantsProductExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchantsProduct record, @Param("example") PayMerchantsProductExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchantsProduct record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchantsProduct record);
}