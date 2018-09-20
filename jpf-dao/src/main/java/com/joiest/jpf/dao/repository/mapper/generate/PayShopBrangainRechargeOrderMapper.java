package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBrangainRechargeOrder;
import com.joiest.jpf.common.po.PayShopBrangainRechargeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBrangainRechargeOrderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBrangainRechargeOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBrangainRechargeOrderExample example);

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
    int insert(PayShopBrangainRechargeOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBrangainRechargeOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBrangainRechargeOrder> selectByExample(PayShopBrangainRechargeOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBrangainRechargeOrder selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBrangainRechargeOrder record, @Param("example") PayShopBrangainRechargeOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBrangainRechargeOrder record, @Param("example") PayShopBrangainRechargeOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBrangainRechargeOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBrangainRechargeOrder record);
}