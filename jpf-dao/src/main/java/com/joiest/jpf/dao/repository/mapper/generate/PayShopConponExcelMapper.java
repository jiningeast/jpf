package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopConponExcel;
import com.joiest.jpf.common.po.PayShopConponExcelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopConponExcelMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopConponExcelExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopConponExcelExample example);

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
    int insert(PayShopConponExcel record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopConponExcel record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopConponExcel> selectByExample(PayShopConponExcelExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopConponExcel selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopConponExcel record, @Param("example") PayShopConponExcelExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopConponExcel record, @Param("example") PayShopConponExcelExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopConponExcel record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopConponExcel record);
}