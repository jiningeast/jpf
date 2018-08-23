package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopStockCard;
import com.joiest.jpf.common.po.PayShopStockCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopStockCardMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopStockCardExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopStockCardExample example);

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
    int insert(PayShopStockCard record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopStockCard record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopStockCard> selectByExample(PayShopStockCardExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopStockCard selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopStockCard record, @Param("example") PayShopStockCardExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopStockCard record, @Param("example") PayShopStockCardExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopStockCard record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopStockCard record);
}