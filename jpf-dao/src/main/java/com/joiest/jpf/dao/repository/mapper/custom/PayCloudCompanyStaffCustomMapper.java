package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.po.PayCloudCompanyStaff;
import com.joiest.jpf.common.po.PayCloudCompanyStaffExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayCloudCompanyStaffCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudCompanyStaffExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudCompanyStaffExample example);

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
    int insert(PayCloudCompanyStaff record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudCompanyStaff record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudCompanyStaff> selectByExample(PayCloudCompanyStaffExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudCompanyStaff selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudCompanyStaff record, @Param("example") PayCloudCompanyStaffExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudCompanyStaff record, @Param("example") PayCloudCompanyStaffExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudCompanyStaff record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudCompanyStaff record);

    /**
     * 根据姓名、银行卡号、身份证号、手机号查询一个人的鉴权记录
     */
    PayCloudCompanyStaff selectOneStaff(PayCloudCompanyStaff payCloudCompanyStaff);
}