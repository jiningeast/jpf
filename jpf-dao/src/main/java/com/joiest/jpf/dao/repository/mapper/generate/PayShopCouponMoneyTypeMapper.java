package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopCouponMoneyType;
import com.joiest.jpf.common.po.PayShopCouponMoneyTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopCouponMoneyTypeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCouponMoneyTypeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCouponMoneyTypeExample example);

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
    int insert(PayShopCouponMoneyType record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCouponMoneyType record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCouponMoneyType> selectByExample(PayShopCouponMoneyTypeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCouponMoneyType selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCouponMoneyType record, @Param("example") PayShopCouponMoneyTypeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCouponMoneyType record, @Param("example") PayShopCouponMoneyTypeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCouponMoneyType record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCouponMoneyType record);
}