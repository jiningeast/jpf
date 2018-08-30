package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayShopStockLogCustom;
import com.joiest.jpf.common.po.PayShopStockLog;
import com.joiest.jpf.common.po.PayShopStockLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopStockLogCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopStockLogExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopStockLogExample example);

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
    int insert(PayShopStockLog record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopStockLog record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopStockLog> selectByExample(PayShopStockLogExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopStockLog selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopStockLog record, @Param("example") PayShopStockLogExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopStockLog record, @Param("example") PayShopStockLogExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopStockLog record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopStockLog record);

    /**
     * 自定义条件查询列表
     *
     * @param example
     */
    List<PayShopStockLogCustom> selectCustom(PayShopStockLogExample example);

    /**
     * 根据条件查出一条数据
     *
     * @param example
     */
    List<PayShopStockLogCustom> selectCustomAsc(PayShopStockLogExample example);



}