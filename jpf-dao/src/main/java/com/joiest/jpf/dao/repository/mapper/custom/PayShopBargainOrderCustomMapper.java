package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayShopBargainOrderCustom;
import com.joiest.jpf.common.po.PayShopBargainOrder;
import com.joiest.jpf.common.po.PayShopBargainOrderExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopBargainOrderCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBargainOrderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBargainOrderExample example);

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
    int insert(PayShopBargainOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBargainOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBargainOrder> selectByExample(PayShopBargainOrderExample example);

    /**
     * 自定义条件查询列表
     *
     * @param example
     */
    List<PayShopBargainOrderCustom> selectJoin(PayShopBargainOrderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBargainOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBargainOrder record, @Param("example") PayShopBargainOrderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBargainOrder record, @Param("example") PayShopBargainOrderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBargainOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBargainOrder record);
}