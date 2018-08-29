package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopCompanyCharge;
import com.joiest.jpf.common.po.PayShopCompanyChargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopCompanyChargeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopCompanyChargeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopCompanyChargeExample example);

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
    int insert(PayShopCompanyCharge record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopCompanyCharge record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopCompanyCharge> selectByExample(PayShopCompanyChargeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopCompanyCharge selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopCompanyCharge record, @Param("example") PayShopCompanyChargeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopCompanyCharge record, @Param("example") PayShopCompanyChargeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopCompanyCharge record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopCompanyCharge record);
}