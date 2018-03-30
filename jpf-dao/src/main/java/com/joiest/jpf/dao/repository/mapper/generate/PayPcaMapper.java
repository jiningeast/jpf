package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayPca;
import com.joiest.jpf.common.po.PayPcaExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayPcaMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayPcaExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayPcaExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param catid
     */
    int deleteByPrimaryKey(Integer catid);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayPca record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayPca record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayPca> selectByExample(PayPcaExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param catid
     */
    PayPca selectByPrimaryKey(Integer catid);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayPca record, @Param("example") PayPcaExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayPca record, @Param("example") PayPcaExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayPca record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayPca record);
}