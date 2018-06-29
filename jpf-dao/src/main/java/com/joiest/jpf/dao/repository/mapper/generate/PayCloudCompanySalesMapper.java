package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudCompanySales;
import com.joiest.jpf.common.po.PayCloudCompanySalesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudCompanySalesMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompanySalesExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompanySalesExample example);

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
    int insert(PayCloudCompanySales record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompanySales record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompanySales> selectByExample(PayCloudCompanySalesExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompanySales selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompanySales record, @Param("example") PayCloudCompanySalesExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompanySales record, @Param("example") PayCloudCompanySalesExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompanySales record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompanySales record);
}