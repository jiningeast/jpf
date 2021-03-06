package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudEmployee;
import com.joiest.jpf.common.po.PayCloudEmployeeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudEmployeeMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudEmployeeExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudEmployeeExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param uid
     */
    int deleteByPrimaryKey(Integer uid);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayCloudEmployee record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudEmployee record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudEmployee> selectByExample(PayCloudEmployeeExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param uid
     */
    PayCloudEmployee selectByPrimaryKey(Integer uid);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudEmployee record, @Param("example") PayCloudEmployeeExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudEmployee record, @Param("example") PayCloudEmployeeExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudEmployee record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudEmployee record);
}