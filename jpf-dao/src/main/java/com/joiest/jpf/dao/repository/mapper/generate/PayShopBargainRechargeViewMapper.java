package com.joiest.jpf.dao.repository.mapper.generate;

import com.joiest.jpf.common.po.PayShopBargainRechargeView;
import com.joiest.jpf.common.po.PayShopBargainRechargeViewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PayShopBargainRechargeViewMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopBargainRechargeViewExample example);

    /**
     *
     * @param example
     */
    int deleteByExample(PayShopBargainRechargeViewExample example);

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
    int insert(PayShopBargainRechargeView record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopBargainRechargeView record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopBargainRechargeView> selectByExample(PayShopBargainRechargeViewExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopBargainRechargeView selectByPrimaryKey(Long id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopBargainRechargeView record, @Param("example") PayShopBargainRechargeViewExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopBargainRechargeView record, @Param("example") PayShopBargainRechargeViewExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopBargainRechargeView record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopBargainRechargeView record);
}