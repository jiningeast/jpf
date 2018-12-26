package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayChargeOrderExample;
import com.joiest.jpf.common.po.PayShopCouponRemain;
import com.joiest.jpf.common.po.PayShopCouponRemainExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface PayShopCouponRemainCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCouponRemainExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCouponRemainExample example);

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
    int insert(PayShopCouponRemain record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCouponRemain record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCouponRemain> selectByExample(PayShopCouponRemainExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCouponRemain selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCouponRemain record, @Param("example") PayShopCouponRemainExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCouponRemain record, @Param("example") PayShopCouponRemainExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCouponRemain record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCouponRemain record);

    /**
     * 查询可转让总金额
     */
    BigDecimal SaleYesSum(PayShopCouponRemainExample example);

    /**
     * 查询不可转让转让总金额
     */
    BigDecimal SaleNoSum(PayShopCouponRemainExample example);

    /**
     * 扣减欣豆的余额
     * @param map
     * @return
     */
    int subCouponDouNo(Map<String, Object> map);

    /**
     * 扣减欣豆的余额
     * @param map
     * @return
     */
    int subCouponDouYes(Map<String, Object> map);
}