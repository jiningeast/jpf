package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudRecharge;
import com.joiest.jpf.common.po.PayCloudRechargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudRechargeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudRechargeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudRechargeExample example);

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
    int insert(PayCloudRecharge record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudRecharge record);

    /**
     *
     * @param example
     */
    List<PayCloudRecharge> selectByExampleWithBLOBs(PayCloudRechargeExample example);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudRecharge> selectByExample(PayCloudRechargeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudRecharge selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudRecharge record, @Param("example") PayCloudRechargeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") PayCloudRecharge record, @Param("example") PayCloudRechargeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudRecharge record, @Param("example") PayCloudRechargeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudRecharge record);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(PayCloudRecharge record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudRecharge record);
}