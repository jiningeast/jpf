package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeProductBrand;
import com.joiest.jpf.common.po.PayChargeProductBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeProductBrandMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeProductBrandExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeProductBrandExample example);

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
    int insert(PayChargeProductBrand record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeProductBrand record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeProductBrand> selectByExample(PayChargeProductBrandExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeProductBrand selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeProductBrand record, @Param("example") PayChargeProductBrandExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeProductBrand record, @Param("example") PayChargeProductBrandExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeProductBrand record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeProductBrand record);
}