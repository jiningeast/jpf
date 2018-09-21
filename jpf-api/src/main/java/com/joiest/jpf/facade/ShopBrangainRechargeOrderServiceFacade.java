package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.dto.ModifyBrangainRechargeorderRequest;
import com.joiest.jpf.entity.ModifyBrangainRechargeorderInfo;
import com.joiest.jpf.entity.ShopBargainRechargeViewInfo;

import java.util.List;

public interface ShopBrangainRechargeOrderServiceFacade {

    /**
     * 数据入库
     */
    public JpfResponseDto insertInfo(ModifyBrangainRechargeorderRequest rechargeorderRequest);

    /**
     * 获取未完成订单数据
     * @return
     */
    public List<ShopBargainRechargeViewInfo> getOrderView();

    /**
     * 获取可绑定数据list
     * @return
     */
    public List<ModifyBrangainRechargeorderInfo> getDataList();

    public int doBingOrderData(List<ShopBargainRechargeViewInfo> viewList ,List<ModifyBrangainRechargeorderInfo> orderList);

}
