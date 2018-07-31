package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopProductType;
import com.joiest.jpf.common.po.PayShopProductTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopProductTypeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopProductTypeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopProductTypeExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param pid
     */
    int deleteByPrimaryKey(Integer pid);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayShopProductType record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopProductType record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopProductType> selectByExample(PayShopProductTypeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param pid
     */
    PayShopProductType selectByPrimaryKey(Integer pid);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopProductType record, @Param("example") PayShopProductTypeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopProductType record, @Param("example") PayShopProductTypeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopProductType record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopProductType record);
}