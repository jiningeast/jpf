package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayCloudInterfaceStream;
import com.joiest.jpf.common.po.PayCloudInterfaceStreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayCloudInterfaceStreamMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayCloudInterfaceStreamExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayCloudInterfaceStreamExample example);

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
    int insert(PayCloudInterfaceStream record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayCloudInterfaceStream record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayCloudInterfaceStream> selectByExample(PayCloudInterfaceStreamExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayCloudInterfaceStream selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayCloudInterfaceStream record, @Param("example") PayCloudInterfaceStreamExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayCloudInterfaceStream record, @Param("example") PayCloudInterfaceStreamExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayCloudInterfaceStream record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayCloudInterfaceStream record);
}