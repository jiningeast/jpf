package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayShopCompany;
import com.joiest.jpf.common.po.PayShopCompanyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopCompanyCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCompanyExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCompanyExample example);

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
    int insert(PayShopCompany record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCompany record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCompany> selectByExample(PayShopCompanyExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCompany selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCompany record, @Param("example") PayShopCompanyExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCompany record, @Param("example") PayShopCompanyExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCompany record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCompany record);
}