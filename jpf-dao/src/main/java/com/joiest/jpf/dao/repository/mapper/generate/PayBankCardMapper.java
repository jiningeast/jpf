package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayBankCard;
import com.joiest.jpf.common.po.PayBankCardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayBankCardMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayBankCardExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayBankCardExample example);

    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(PayBankCard record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayBankCard record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayBankCard> selectByExample(PayBankCardExample example);

    /**
     * 自定义查找
     */
    List<PayBankCard> customSelectByCardNo(String cardNo);
    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayBankCard selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayBankCard record, @Param("example") PayBankCardExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayBankCard record, @Param("example") PayBankCardExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayBankCard record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayBankCard record);
}