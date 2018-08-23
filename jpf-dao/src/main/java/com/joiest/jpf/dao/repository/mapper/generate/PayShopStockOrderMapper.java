package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopStockOrder;
import com.joiest.jpf.common.po.PayShopStockOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopStockOrderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopStockOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopStockOrderExample example);

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
    int insert(PayShopStockOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopStockOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopStockOrder> selectByExample(PayShopStockOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopStockOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopStockOrder record, @Param("example") PayShopStockOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopStockOrder record, @Param("example") PayShopStockOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopStockOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopStockOrder record);
}