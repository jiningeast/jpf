package com.joiest.jpf.dao.repository.mapper.custom;

import com.joiest.jpf.common.custom.PayShopOrderCustom;
import com.joiest.jpf.common.custom.PayShopOrderCustomExample;
import com.joiest.jpf.common.po.PayShopOrder;
import com.joiest.jpf.common.po.PayShopProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PayShopOrderCustomMapper {
    /**
     * 根据条件计数
     *
     * @param example
     */
    int countByExample(PayShopOrderCustomExample example);


    /**
     * 根据条件计数连表
     *
     * @param example
     */
    int countByExampleList(PayShopOrderCustomExample example);
    /**
     *
     * @param example
     */
    int deleteByExample(PayShopOrderCustomExample example);

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
    int insert(PayShopOrder record);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insertSelective(PayShopOrder record);

    /**
     * 根据条件查询列表
     *
     * @param example
     */
    List<PayShopOrder> selectByExample(PayShopOrderCustomExample example);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    PayShopOrder selectByPrimaryKey(String id);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExampleSelective(@Param("record") PayShopOrder record, @Param("example") PayShopOrderCustomExample example);

    /**
     * 选择性更新数据库记录
     *
     * @param record
     * @param example
     */
    int updateByExample(@Param("record") PayShopOrder record, @Param("example") PayShopOrderCustomExample example);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(PayShopOrder record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(PayShopOrder record);


    /**
     * 根据条件连表查询订单数据
     *
     * @param example
     */
    List<PayShopOrderCustom> selectByExampleJoin(PayShopOrderCustomExample example);

    /**
     * 订单列表接口查询
     *
     * @param example
     */
    List<PayShopOrderCustom> selectByExampleInterfaceJoin(PayShopOrderCustomExample example);

    /**
     *
     *获取业务公司连表信息

     */
    PayShopOrderCustom selectOrderAll(PayShopOrderCustomExample example);

    /**
     *
     *获取业务公司连表信息

     */
    PayShopOrderCustom selectOrderInterfaceAll(PayShopOrderCustomExample example);


    List<PayShopOrderCustom> selectByExcelExampleJoin(PayShopOrderCustomExample example);

    /**
     * 查询所有中欣卡订单
     * @param pageNo
     * @param pageSize
     * @param orderNo
     * @param payShopProductList
     * @param status 
     * @return
     */
    List<PayShopOrderCustom> selectJoiestCardProductId(@Param("pageNo")long pageNo,
                                                       @Param("pageSize")long pageSize,
                                                       @Param("orderNo")String orderNo,
                                                       @Param("payShopProductList") List<String> payShopProductList);
}