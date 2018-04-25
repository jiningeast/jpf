package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchantsPaytype;
import com.joiest.jpf.common.po.PayMerchantsPaytypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsPaytypeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsPaytypeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsPaytypeExample example);

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
    int insert(PayMerchantsPaytype record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchantsPaytype record);

    /**
     *
     * @param example
     */
    List<PayMerchantsPaytype> selectByExampleWithBLOBs(PayMerchantsPaytypeExample example);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchantsPaytype> selectByExample(PayMerchantsPaytypeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayMerchantsPaytype selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchantsPaytype record, @Param("example") PayMerchantsPaytypeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") PayMerchantsPaytype record, @Param("example") PayMerchantsPaytypeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchantsPaytype record, @Param("example") PayMerchantsPaytypeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchantsPaytype record);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(PayMerchantsPaytype record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchantsPaytype record);
}