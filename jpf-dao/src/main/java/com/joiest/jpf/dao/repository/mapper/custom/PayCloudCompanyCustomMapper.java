package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayCloudCompanyCustom;
import com.joiest.jpf.common.po.PayCloudCompany;
import com.joiest.jpf.common.po.PayCloudCompanyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayCloudCompanyCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompanyExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompanyExample example);

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
    int insert(PayCloudCompany record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompany record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompany> selectByExample(PayCloudCompanyExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompany selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompany record, @Param("example") PayCloudCompanyExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompany record, @Param("example") PayCloudCompanyExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompany record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompany record);

    /**
     *
     *
     * @param example
     */
    List<PayCloudCompanyCustom> selectCompanyAgent(PayCloudCompanyExample example);

    /**
     *
     *

     */
    List<PayCloudCompanyCustom> selectCompanySales(PayCloudCompanyExample example);

}