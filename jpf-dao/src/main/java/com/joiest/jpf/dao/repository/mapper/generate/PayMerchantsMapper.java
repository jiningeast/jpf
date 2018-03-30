package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayMerchants;
import com.joiest.jpf.common.po.PayMerchantsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayMerchantsMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayMerchantsExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayMerchantsExample example);

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
    int insert(PayMerchants record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayMerchants record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayMerchants> selectByExample(PayMerchantsExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayMerchants selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayMerchants record, @Param("example") PayMerchantsExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayMerchants record, @Param("example") PayMerchantsExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayMerchants record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayMerchants record);
}