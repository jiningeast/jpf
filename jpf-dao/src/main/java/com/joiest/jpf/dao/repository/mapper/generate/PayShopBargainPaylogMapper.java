package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBargainPaylog;
import com.joiest.jpf.common.po.PayShopBargainPaylogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBargainPaylogMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBargainPaylogExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBargainPaylogExample example);

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
    int insert(PayShopBargainPaylog record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBargainPaylog record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBargainPaylog> selectByExample(PayShopBargainPaylogExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBargainPaylog selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBargainPaylog record, @Param("example") PayShopBargainPaylogExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBargainPaylog record, @Param("example") PayShopBargainPaylogExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBargainPaylog record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBargainPaylog record);
}