package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopProductInfo;
import com.joiest.jpf.common.po.PayShopProductInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopProductInfoMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopProductInfoExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopProductInfoExample example);

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
    int insert(PayShopProductInfo record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopProductInfo record);

    /**
     *
     * @param example
     */
    List<PayShopProductInfo> selectByExampleWithBLOBs(PayShopProductInfoExample example);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopProductInfo> selectByExample(PayShopProductInfoExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopProductInfo selectByPrimaryKey(Integer id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopProductInfo record, @Param("example") PayShopProductInfoExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleWithBLOBs(@Param("record") PayShopProductInfo record, @Param("example") PayShopProductInfoExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopProductInfo record, @Param("example") PayShopProductInfoExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopProductInfo record);

    /**
     *
     * @param record
     */
    int updateByPrimaryKeyWithBLOBs(PayShopProductInfo record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopProductInfo record);
}