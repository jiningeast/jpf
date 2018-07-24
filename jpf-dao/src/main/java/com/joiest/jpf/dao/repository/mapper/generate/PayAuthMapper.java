package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayAuth;
import com.joiest.jpf.common.po.PayAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayAuthMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayAuthExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayAuthExample example);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayAuth record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayAuth record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayAuth> selectByExample(PayAuthExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayAuth record, @Param("example") PayAuthExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayAuth record, @Param("example") PayAuthExample example);
}