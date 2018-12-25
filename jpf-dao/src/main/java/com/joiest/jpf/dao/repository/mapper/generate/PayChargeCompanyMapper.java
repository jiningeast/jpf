package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeCompany;
import com.joiest.jpf.common.po.PayChargeCompanyExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PayChargeCompanyMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeCompanyExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeCompanyExample example);

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
    int insert(PayChargeCompany record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeCompany record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeCompany> selectByExample(PayChargeCompanyExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeCompany selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeCompany record, @Param("example") PayChargeCompanyExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeCompany record, @Param("example") PayChargeCompanyExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeCompany record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeCompany record);

    /**
     * 扣减金额
     * @param map
     * @return
     */
    int updateCompanyMoneySub(Map<String, Object> map);

    /**
     * 退款金额
     * @param map
     * @return
     */
    int updateCompanyMoneyAdd(Map<String, Object> map);
}