package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopCouponActive;
import com.joiest.jpf.common.po.PayShopCouponActiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopCouponActiveMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCouponActiveExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCouponActiveExample example);

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
    int insert(PayShopCouponActive record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCouponActive record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCouponActive> selectByExample(PayShopCouponActiveExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCouponActive selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCouponActive record, @Param("example") PayShopCouponActiveExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCouponActive record, @Param("example") PayShopCouponActiveExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCouponActive record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCouponActive record);
}