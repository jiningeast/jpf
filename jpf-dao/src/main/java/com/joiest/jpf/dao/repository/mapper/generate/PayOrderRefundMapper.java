package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderRefund;
import com.joiest.jpf.common.po.PayOrderRefundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderRefundMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderRefundExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderRefundExample example);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayOrderRefund record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderRefund record);

    /**
     *
     * @param example
     */
    List<PayOrderRefund> selectByExampleWithBLOBs(PayOrderRefundExample example);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderRefund> selectByExample(PayOrderRefundExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderRefund record, @Param("example") PayOrderRefundExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") PayOrderRefund record, @Param("example") PayOrderRefundExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderRefund record, @Param("example") PayOrderRefundExample example);
}