package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBargainRechargeOrder;
import com.joiest.jpf.common.po.PayShopBargainRechargeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBargainRechargeOrderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBargainRechargeOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBargainRechargeOrderExample example);

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
    int insert(PayShopBargainRechargeOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBargainRechargeOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBargainRechargeOrder> selectByExample(PayShopBargainRechargeOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBargainRechargeOrder selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBargainRechargeOrder record, @Param("example") PayShopBargainRechargeOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBargainRechargeOrder record, @Param("example") PayShopBargainRechargeOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBargainRechargeOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBargainRechargeOrder record);
    
    List<PayShopBargainRechargeOrder> selectByExampleExcel(PayShopBargainRechargeOrderExample e);
}