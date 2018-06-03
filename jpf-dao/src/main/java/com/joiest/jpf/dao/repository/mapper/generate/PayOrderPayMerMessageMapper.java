package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayOrderPayMerMessage;
import com.joiest.jpf.common.po.PayOrderPayMerMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayOrderPayMerMessageMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayOrderPayMerMessageExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayOrderPayMerMessageExample example);

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
    int insert(PayOrderPayMerMessage record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayOrderPayMerMessage record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayOrderPayMerMessage> selectByExample(PayOrderPayMerMessageExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayOrderPayMerMessage selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayOrderPayMerMessage record, @Param("example") PayOrderPayMerMessageExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayOrderPayMerMessage record, @Param("example") PayOrderPayMerMessageExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayOrderPayMerMessage record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayOrderPayMerMessage record);
}