package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudCompanyBank;
import com.joiest.jpf.common.po.PayCloudCompanyBankExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudCompanyBankMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompanyBankExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompanyBankExample example);

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
    int insert(PayCloudCompanyBank record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompanyBank record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompanyBank> selectByExample(PayCloudCompanyBankExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompanyBank selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompanyBank record, @Param("example") PayCloudCompanyBankExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompanyBank record, @Param("example") PayCloudCompanyBankExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompanyBank record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompanyBank record);
}