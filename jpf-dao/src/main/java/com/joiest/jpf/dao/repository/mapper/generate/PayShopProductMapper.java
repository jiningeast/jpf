package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopProduct;
import com.joiest.jpf.common.po.PayShopProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopProductMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopProductExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopProductExample example);

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
    int insert(PayShopProduct record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopProduct record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopProduct> selectByExample(PayShopProductExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopProduct selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopProduct record, @Param("example") PayShopProductExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopProduct record, @Param("example") PayShopProductExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopProduct record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopProduct record);
}