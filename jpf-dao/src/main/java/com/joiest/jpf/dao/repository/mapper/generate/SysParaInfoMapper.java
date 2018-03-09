package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.SysParaInfo;
import com.joiest.jpf.common.po.SysParaInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysParaInfoMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(SysParaInfoExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(SysParaInfoExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param paraId
     */
    int deleteByPrimaryKey(Long paraId);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(SysParaInfo record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(SysParaInfo record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<SysParaInfo> selectByExample(SysParaInfoExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param paraId
     */
    SysParaInfo selectByPrimaryKey(Long paraId);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") SysParaInfo record, @Param("example") SysParaInfoExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") SysParaInfo record, @Param("example") SysParaInfoExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(SysParaInfo record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(SysParaInfo record);
}