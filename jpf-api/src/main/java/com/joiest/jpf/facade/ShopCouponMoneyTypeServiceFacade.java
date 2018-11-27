package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ShopCouponMoneyTypeServiceFacade {

    /**
     * 查询接口
     * @param payShopCouponMoneyType
     * @param page
     * @param rows
     * @return
     */
    List<PayShopCouponMoneyType> getList(PayShopCouponMoneyType payShopCouponMoneyType, long page, long rows);

    /**
     * 查询总的数量
     * @param payShopCouponMoneyType
     * @return
     */
    int getCount(PayShopCouponMoneyType payShopCouponMoneyType);

    /**
     * 新增接口
     * @param payShopCouponMoneyType
     */
    void add(PayShopCouponMoneyType payShopCouponMoneyType);

    /**
     * 根据金额查询
     * @param payShopCouponMoneyType
     * @return
     */
    List<PayShopCouponMoneyType> getByMoney(PayShopCouponMoneyType payShopCouponMoneyType);

    /**
     * 根据id 查询记录
     * @param id
     * @return
     */
    PayShopCouponMoneyType getById(String id);

    /**
     *  编辑金额
     * @param payShopCouponMoneyType
     */
    void edit(PayShopCouponMoneyType payShopCouponMoneyType);

    /**
     * 查询数据金额，按照金额大小排序
     * @return
     */
    List<BigDecimal> getMoneyList();

    /**
     * 查询金额的接口
     * @return
     */
    List<Map<String,Object>> getMoneyToMap();
}
