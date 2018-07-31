package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopSupplier;
import com.joiest.jpf.common.po.PayShopSupplierExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopSupplierMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopSupplierExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopSupplierExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayShopSupplier record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopSupplier record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopSupplier> selectByExample(PayShopSupplierExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopSupplier selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopSupplier record, @Param("example") PayShopSupplierExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopSupplier record, @Param("example") PayShopSupplierExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopSupplier record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopSupplier record);
}