package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopInterfaceStream;
import com.joiest.jpf.common.po.PayShopInterfaceStreamExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopInterfaceStreamMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopInterfaceStreamExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopInterfaceStreamExample example);

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
    int insert(PayShopInterfaceStream record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopInterfaceStream record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopInterfaceStream> selectByExample(PayShopInterfaceStreamExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopInterfaceStream selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopInterfaceStream record, @Param("example") PayShopInterfaceStreamExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopInterfaceStream record, @Param("example") PayShopInterfaceStreamExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopInterfaceStream record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopInterfaceStream record);
}