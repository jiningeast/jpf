package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopWnproduct;
import com.joiest.jpf.common.po.PayShopWnproductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopWnproductMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopWnproductExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopWnproductExample example);

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
    int insert(PayShopWnproduct record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopWnproduct record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopWnproduct> selectByExample(PayShopWnproductExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopWnproduct selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopWnproduct record, @Param("example") PayShopWnproductExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopWnproduct record, @Param("example") PayShopWnproductExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopWnproduct record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopWnproduct record);
}