package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PaySystemlog;
import com.joiest.jpf.common.po.PaySystemlogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaySystemlogMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PaySystemlogExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PaySystemlogExample example);

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
    int insert(PaySystemlog record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PaySystemlog record);

    /**
     *
     * @param example
     */
    List<PaySystemlog> selectByExampleWithBLOBs(PaySystemlogExample example);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PaySystemlog> selectByExample(PaySystemlogExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PaySystemlog selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PaySystemlog record, @Param("example") PaySystemlogExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") PaySystemlog record, @Param("example") PaySystemlogExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PaySystemlog record, @Param("example") PaySystemlogExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PaySystemlog record);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(PaySystemlog record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PaySystemlog record);
}