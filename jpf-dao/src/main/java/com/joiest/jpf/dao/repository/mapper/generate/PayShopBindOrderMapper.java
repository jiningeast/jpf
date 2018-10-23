package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBindOrder;
import com.joiest.jpf.common.po.PayShopBindOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBindOrderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBindOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBindOrderExample example);

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
    int insert(PayShopBindOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBindOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBindOrder> selectByExample(PayShopBindOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBindOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBindOrder record, @Param("example") PayShopBindOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBindOrder record, @Param("example") PayShopBindOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBindOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBindOrder record);
}