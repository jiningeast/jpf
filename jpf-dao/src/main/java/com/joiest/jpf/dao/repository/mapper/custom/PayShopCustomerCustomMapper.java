package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayShopCustomer;
import com.joiest.jpf.common.po.PayShopCustomerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopCustomerCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCustomerExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCustomerExample example);

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
    int insert(PayShopCustomer record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCustomer record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCustomer> selectByExample(PayShopCustomerExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCustomer selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCustomer record, @Param("example") PayShopCustomerExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCustomer record, @Param("example") PayShopCustomerExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCustomer record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCustomer record);
}