package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.common.po.PayShopOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopOrderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopOrderExample example);

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
    int insert(PayShopOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopOrder> selectByExample(PayShopOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopOrder record, @Param("example") PayShopOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopOrder record, @Param("example") PayShopOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopOrder record);
}