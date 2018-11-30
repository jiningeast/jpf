package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayShopBatchCoupon;
import com.joiest.jpf.common.po.PayShopBatchCouponExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopBatchCouponCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBatchCouponExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBatchCouponExample example);

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
    int insert(PayShopBatchCoupon record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBatchCoupon record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBatchCoupon> selectByExample(PayShopBatchCouponExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBatchCoupon selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBatchCoupon record, @Param("example") PayShopBatchCouponExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBatchCoupon record, @Param("example") PayShopBatchCouponExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBatchCoupon record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBatchCoupon record);

    /**
     * 批量插入数据
     * @param payShopBatchCouponList
     */
    void batchInsert(List<PayShopBatchCoupon> payShopBatchCouponList);
}