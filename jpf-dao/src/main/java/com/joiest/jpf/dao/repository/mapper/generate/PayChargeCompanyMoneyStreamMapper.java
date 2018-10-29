package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeCompanyMoneyStream;
import com.joiest.jpf.common.po.PayChargeCompanyMoneyStreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeCompanyMoneyStreamMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeCompanyMoneyStreamExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeCompanyMoneyStreamExample example);

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
    int insert(PayChargeCompanyMoneyStream record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeCompanyMoneyStream record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeCompanyMoneyStream> selectByExample(PayChargeCompanyMoneyStreamExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeCompanyMoneyStream selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeCompanyMoneyStream record, @Param("example") PayChargeCompanyMoneyStreamExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeCompanyMoneyStream record, @Param("example") PayChargeCompanyMoneyStreamExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeCompanyMoneyStream record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeCompanyMoneyStream record);
}