package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchantsType;
import com.joiest.jpf.common.po.PayMerchantsTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsTypeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsTypeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsTypeExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param catid
     */
    int deleteByPrimaryKey(Integer catid);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayMerchantsType record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchantsType record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchantsType> selectByExample(PayMerchantsTypeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param catid
     */
    PayMerchantsType selectByPrimaryKey(Integer catid);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchantsType record, @Param("example") PayMerchantsTypeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchantsType record, @Param("example") PayMerchantsTypeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchantsType record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchantsType record);
}