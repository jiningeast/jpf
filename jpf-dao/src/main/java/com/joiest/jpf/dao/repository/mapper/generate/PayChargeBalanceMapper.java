package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeBalance;
import com.joiest.jpf.common.po.PayChargeBalanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeBalanceMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeBalanceExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeBalanceExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayChargeBalance record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeBalance record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeBalance> selectByExample(PayChargeBalanceExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeBalance selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeBalance record, @Param("example") PayChargeBalanceExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeBalance record, @Param("example") PayChargeBalanceExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeBalance record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeBalance record);
}