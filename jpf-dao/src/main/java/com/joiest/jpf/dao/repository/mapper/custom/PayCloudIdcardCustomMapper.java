package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayCloudIdcard;
import com.joiest.jpf.common.po.PayCloudIdcardExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayCloudIdcardCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudIdcardExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudIdcardExample example);

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
    int insert(PayCloudIdcard record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudIdcard record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudIdcard> selectByExample(PayCloudIdcardExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudIdcard selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudIdcard record, @Param("example") PayCloudIdcardExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudIdcard record, @Param("example") PayCloudIdcardExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudIdcard record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudIdcard record);
}