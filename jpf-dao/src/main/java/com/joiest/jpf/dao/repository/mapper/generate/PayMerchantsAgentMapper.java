package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchantsAgent;
import com.joiest.jpf.common.po.PayMerchantsAgentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsAgentMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsAgentExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsAgentExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayMerchantsAgent record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchantsAgent record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchantsAgent> selectByExample(PayMerchantsAgentExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayMerchantsAgent selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchantsAgent record, @Param("example") PayMerchantsAgentExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchantsAgent record, @Param("example") PayMerchantsAgentExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchantsAgent record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchantsAgent record);
}