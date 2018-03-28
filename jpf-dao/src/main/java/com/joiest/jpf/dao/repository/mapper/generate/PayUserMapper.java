package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayUser;
import com.joiest.jpf.common.po.PayUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayUserMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayUserExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayUserExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayUser record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayUser record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayUser> selectByExample(PayUserExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayUser selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayUser record, @Param("example") PayUserExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayUser record, @Param("example") PayUserExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayUser record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayUser record);
}