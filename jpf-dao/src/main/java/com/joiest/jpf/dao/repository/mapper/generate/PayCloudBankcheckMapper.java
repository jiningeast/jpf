package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudBankcheck;
import com.joiest.jpf.common.po.PayCloudBankcheckExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudBankcheckMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudBankcheckExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudBankcheckExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(BigInteger id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudBankcheck record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudBankcheck record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudBankcheck> selectByExample(PayCloudBankcheckExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudBankcheck selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudBankcheck record, @Param("example") PayCloudBankcheckExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudBankcheck record, @Param("example") PayCloudBankcheckExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudBankcheck record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudBankcheck record);
}