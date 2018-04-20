package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayTdorder;
import com.joiest.jpf.common.po.PayTdorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayTdorderMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayTdorderExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayTdorderExample example);

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
    int insert(PayTdorder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayTdorder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayTdorder> selectByExample(PayTdorderExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayTdorder selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayTdorder record, @Param("example") PayTdorderExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayTdorder record, @Param("example") PayTdorderExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayTdorder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayTdorder record);
}