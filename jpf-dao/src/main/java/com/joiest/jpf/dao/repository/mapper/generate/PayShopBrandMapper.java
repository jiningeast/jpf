package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBrand;
import com.joiest.jpf.common.po.PayShopBrandExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBrandMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBrandExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBrandExample example);

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
    int insert(PayShopBrand record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBrand record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBrand> selectByExample(PayShopBrandExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBrand selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBrand record, @Param("example") PayShopBrandExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBrand record, @Param("example") PayShopBrandExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBrand record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBrand record);
}