package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayChargeInterfaceStream;
import com.joiest.jpf.common.po.PayChargeInterfaceStreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayChargeInterfaceStreamMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayChargeInterfaceStreamExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayChargeInterfaceStreamExample example);

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
    int insert(PayChargeInterfaceStream record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayChargeInterfaceStream record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayChargeInterfaceStream> selectByExample(PayChargeInterfaceStreamExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayChargeInterfaceStream selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayChargeInterfaceStream record, @Param("example") PayChargeInterfaceStreamExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayChargeInterfaceStream record, @Param("example") PayChargeInterfaceStreamExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayChargeInterfaceStream record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayChargeInterfaceStream record);
}