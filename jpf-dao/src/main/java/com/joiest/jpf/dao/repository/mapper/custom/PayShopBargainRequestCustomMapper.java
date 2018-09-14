package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayShopBargainRequestCustom;
import com.joiest.jpf.common.po.PayShopBargainRequest;
import com.joiest.jpf.common.po.PayShopBargainRequestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopBargainRequestCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBargainRequestExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBargainRequestExample example);

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
    int insert(PayShopBargainRequest record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBargainRequest record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBargainRequestCustom> selectByExample(PayShopBargainRequestExample example);

    /**
     *自定义查询列表
     *
     * @param example
     */
    List<PayShopBargainRequestCustom> selectByJoinExample(PayShopBargainRequestExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBargainRequest selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBargainRequest record, @Param("example") PayShopBargainRequestExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBargainRequest record, @Param("example") PayShopBargainRequestExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBargainRequest record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBargainRequest record);
}