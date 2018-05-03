package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayVirtual;
import com.joiest.jpf.common.po.PayVirtualExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayVirtualMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayVirtualExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayVirtualExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param catid
     */
    int deleteByPrimaryKey(Byte catid);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayVirtual record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayVirtual record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayVirtual> selectByExample(PayVirtualExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param catid
     */
    PayVirtual selectByPrimaryKey(Byte catid);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayVirtual record, @Param("example") PayVirtualExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayVirtual record, @Param("example") PayVirtualExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayVirtual record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayVirtual record);
}