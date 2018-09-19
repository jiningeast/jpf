package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayShopBargainRequest;
import com.joiest.jpf.dto.GetShopBargainOrderRequest;
import com.joiest.jpf.dto.GetShopBargainRequestRequest;
import com.joiest.jpf.dto.GetShopBargainRequestResponse;
import com.joiest.jpf.entity.ShopBargainRequestInfo;

import java.util.List;

import java.util.List;

public interface ShopBargainRequestServiceFacade {
    /**
     * 收购列表---后台
     */
    public GetShopBargainRequestResponse getList (GetShopBargainRequestRequest request);


    /**
     * 收购开启和关闭
     */
    public JpfResponseDto delShopBargain(String merchNo, int type);

    /**
     * 获取买家转让单信息通过主键id
     * */
    public ShopBargainRequestInfo getBargainById(String id);

    /**
     * 获取买家信息
     * */
    public List<ShopBargainRequestInfo> getBuyInfo();

    /*
     * 添加买家回收信息
     * */
    public JpfResponseDto add(GetShopBargainRequestRequest request);

    /*
     * 查询当前买家 用户发布信息
     * */
    public ShopBargainRequestInfo getOne(GetShopBargainRequestRequest request);

}
