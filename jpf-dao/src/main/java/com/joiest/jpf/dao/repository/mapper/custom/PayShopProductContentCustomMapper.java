package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayShopProductContent;
import com.joiest.jpf.common.po.PayShopProductContentExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopProductContentCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopProductContentExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopProductContentExample example);

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
    int insert(PayShopProductContent record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopProductContent record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopProductContent> selectByExample(PayShopProductContentExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopProductContent selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopProductContent record, @Param("example") PayShopProductContentExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopProductContent record, @Param("example") PayShopProductContentExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopProductContent record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopProductContent record);
}