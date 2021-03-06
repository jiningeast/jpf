package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopStockOrderProduct;
import com.joiest.jpf.common.po.PayShopStockOrderProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopStockOrderProductMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopStockOrderProductExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopStockOrderProductExample example);

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
    int insert(PayShopStockOrderProduct record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopStockOrderProduct record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopStockOrderProduct> selectByExample(PayShopStockOrderProductExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopStockOrderProduct selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopStockOrderProduct record, @Param("example") PayShopStockOrderProductExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopStockOrderProduct record, @Param("example") PayShopStockOrderProductExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopStockOrderProduct record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopStockOrderProduct record);
}