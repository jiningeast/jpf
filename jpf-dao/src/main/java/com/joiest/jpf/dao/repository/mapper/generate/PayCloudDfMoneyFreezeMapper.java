package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudDfMoneyFreeze;
import com.joiest.jpf.common.po.PayCloudDfMoneyFreezeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudDfMoneyFreezeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudDfMoneyFreezeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudDfMoneyFreezeExample example);

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
    int insert(PayCloudDfMoneyFreeze record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudDfMoneyFreeze record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudDfMoneyFreeze> selectByExample(PayCloudDfMoneyFreezeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudDfMoneyFreeze selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudDfMoneyFreeze record, @Param("example") PayCloudDfMoneyFreezeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudDfMoneyFreeze record, @Param("example") PayCloudDfMoneyFreezeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudDfMoneyFreeze record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudDfMoneyFreeze record);
}