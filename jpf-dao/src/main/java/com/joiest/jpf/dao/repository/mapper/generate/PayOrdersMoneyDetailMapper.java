package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrdersMoneyDetail;
import com.joiest.jpf.common.po.PayOrdersMoneyDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrdersMoneyDetailMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrdersMoneyDetailExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrdersMoneyDetailExample example);

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
    int insert(PayOrdersMoneyDetail record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrdersMoneyDetail record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrdersMoneyDetail> selectByExample(PayOrdersMoneyDetailExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrdersMoneyDetail selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrdersMoneyDetail record, @Param("example") PayOrdersMoneyDetailExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrdersMoneyDetail record, @Param("example") PayOrdersMoneyDetailExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrdersMoneyDetail record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrdersMoneyDetail record);
}