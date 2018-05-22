package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderCp;
import com.joiest.jpf.common.po.PayOrderCpExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderCpMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderCpExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderCpExample example);

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
    int insert(PayOrderCp record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderCp record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderCp> selectByExample(PayOrderCpExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderCp selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderCp record, @Param("example") PayOrderCpExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderCp record, @Param("example") PayOrderCpExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderCp record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderCp record);
}