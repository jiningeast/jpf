package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderYinjiaApi;
import com.joiest.jpf.common.po.PayOrderYinjiaApiExample;
import java.math.BigInteger;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderYinjiaApiMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderYinjiaApiExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderYinjiaApiExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(BigInteger id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayOrderYinjiaApi record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderYinjiaApi record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderYinjiaApi> selectByExample(PayOrderYinjiaApiExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderYinjiaApi selectByPrimaryKey(BigInteger id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderYinjiaApi record, @Param("example") PayOrderYinjiaApiExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderYinjiaApi record, @Param("example") PayOrderYinjiaApiExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderYinjiaApi record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderYinjiaApi record);
}