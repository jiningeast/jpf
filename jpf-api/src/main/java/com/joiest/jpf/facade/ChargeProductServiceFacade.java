package com.joiest.jpf.facade;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.po.PayChargeProduct;
import com.joiest.jpf.dto.ChargeProductInterfaceRequest;
import com.joiest.jpf.dto.GetChargeProductRequest;
import com.joiest.jpf.dto.GetChargeProductResponse;
import com.joiest.jpf.entity.ChargeProductInfo;

import java.util.List;

public interface ChargeProductServiceFacade {

    /**
     * 查询商品列表信息
     */
    public List<ChargeProductInfo> getList(PayChargeProduct record);

    /**
    * 获取商品信息 单个 通过商品id
    * */
    public ChargeProductInfo getProductById(String id);

    /**
     * shop_product list
     * @param request
     * @return
     * 查询商品列表信息，并返回数量
     */
    public GetChargeProductResponse getProductList(GetChargeProductRequest request);

    /**
     * 获取商品 单个
     */
    public ChargeProductInfo getChargeProduct(String id);

    /**
     * 添加商品
     */
    public int addChargeProduct(ChargeProductInfo info);

    /**
     * 编辑商品
     */
    public JpfResponseDto modifyChargeProduct(ChargeProductInfo chargeProductInfo);

    /**
     * 编辑商品 上游价格
     */
    public int upChargeProduct(ChargeProductInfo chargeProductInfo);

    /**
     * 接口商品列表
     */
    public GetChargeProductResponse getProductListInterface(ChargeProductInterfaceRequest request);

}
