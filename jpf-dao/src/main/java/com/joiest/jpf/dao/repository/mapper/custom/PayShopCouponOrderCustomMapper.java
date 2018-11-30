package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayShopCouponOrder;
import com.joiest.jpf.common.po.PayShopCouponOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopCouponOrderCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCouponOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCouponOrderExample example);

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
    int insert(PayShopCouponOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCouponOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCouponOrder> selectByExample(PayShopCouponOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCouponOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCouponOrder record, @Param("example") PayShopCouponOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCouponOrder record, @Param("example") PayShopCouponOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCouponOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCouponOrder record);
}