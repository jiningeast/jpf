package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudStaffBanks;
import com.joiest.jpf.common.po.PayCloudStaffBanksExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudStaffBanksMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudStaffBanksExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudStaffBanksExample example);

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
    int insert(PayCloudStaffBanks record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudStaffBanks record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudStaffBanks> selectByExample(PayCloudStaffBanksExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudStaffBanks selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudStaffBanks record, @Param("example") PayCloudStaffBanksExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudStaffBanks record, @Param("example") PayCloudStaffBanksExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudStaffBanks record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudStaffBanks record);
}