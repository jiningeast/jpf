package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopCouponMoneyType;

import java.util.List;

public interface ShopCouponMoneyTypeServiceFacade {

    List<PayShopCouponMoneyType> getList(String money, byte status, long page, long rows);

    int getCount(String money, byte status);

    JpfResponseDto add(PayShopCouponMoneyType payShopCouponMoneyType);


}
