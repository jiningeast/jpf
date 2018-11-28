package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBatch;
import com.joiest.jpf.common.po.PayShopBatchExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBatchMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBatchExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBatchExample example);

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
    int insert(PayShopBatch record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBatch record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBatch> selectByExample(PayShopBatchExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBatch selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBatch record, @Param("example") PayShopBatchExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBatch record, @Param("example") PayShopBatchExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBatch record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBatch record);
}