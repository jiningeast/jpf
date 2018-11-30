package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopCouponOrderInfo;
import com.joiest.jpf.common.po.PayShopCouponOrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopCouponOrderInfoMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCouponOrderInfoExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCouponOrderInfoExample example);

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
    int insert(PayShopCouponOrderInfo record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCouponOrderInfo record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCouponOrderInfo> selectByExample(PayShopCouponOrderInfoExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCouponOrderInfo selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCouponOrderInfo record, @Param("example") PayShopCouponOrderInfoExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCouponOrderInfo record, @Param("example") PayShopCouponOrderInfoExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCouponOrderInfo record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCouponOrderInfo record);
}