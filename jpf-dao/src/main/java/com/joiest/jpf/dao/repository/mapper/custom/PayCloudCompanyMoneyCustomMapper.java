package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayCloudCompanyMoneyCustom;
import com.joiest.jpf.common.po.PayCloudCompanyMoney;
import com.joiest.jpf.common.po.PayCloudCompanyMoneyExample;
import com.joiest.jpf.common.po.PayOrderExample;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface PayCloudCompanyMoneyCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompanyMoneyExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompanyMoneyExample example);

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
    int insert(PayCloudCompanyMoney record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompanyMoney record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompanyMoney> selectByExample(PayCloudCompanyMoneyExample example);



    /**
     * 根据条件连表查询公司信息
     *
     * @param example
     */
    List<PayCloudCompanyMoneyCustom> selectByExampleandCompany(PayCloudCompanyMoneyExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompanyMoney selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompanyMoney record, @Param("example") PayCloudCompanyMoneyExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompanyMoney record, @Param("example") PayCloudCompanyMoneyExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompanyMoney record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompanyMoney record);

    /**
     * 查询订单总金额
     */
    BigDecimal allMoney(PayCloudCompanyMoneyExample example);
    /**
     * 查询订单总服务费
     */
    BigDecimal allServiceMoney(PayCloudCompanyMoneyExample example);
    /**
     * 查询订单总增值税
     */
    BigDecimal addedMoney(PayCloudCompanyMoneyExample example);
    /**
     * 查询订单总增值税附加
     */
    BigDecimal addedMoneyPay(PayCloudCompanyMoneyExample example);
    /**
     * 查询订单总毛利
     */
    BigDecimal totalGross(PayCloudCompanyMoneyExample example);



}