package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchantsShop;
import com.joiest.jpf.common.po.PayMerchantsShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsShopMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsShopExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsShopExample example);

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
    int insert(PayMerchantsShop record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchantsShop record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchantsShop> selectByExample(PayMerchantsShopExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayMerchantsShop selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchantsShop record, @Param("example") PayMerchantsShopExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchantsShop record, @Param("example") PayMerchantsShopExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchantsShop record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchantsShop record);
}