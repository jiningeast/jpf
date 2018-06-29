package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudCompanyAgent;
import com.joiest.jpf.common.po.PayCloudCompanyAgentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudCompanyAgentMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompanyAgentExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompanyAgentExample example);

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
    int insert(PayCloudCompanyAgent record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompanyAgent record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompanyAgent> selectByExample(PayCloudCompanyAgentExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompanyAgent selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompanyAgent record, @Param("example") PayCloudCompanyAgentExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompanyAgent record, @Param("example") PayCloudCompanyAgentExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompanyAgent record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompanyAgent record);
}