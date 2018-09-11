package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopStockCardResponse;
import com.joiest.jpf.entity.ShopProductInterfaceInfo;
import com.joiest.jpf.entity.ShopStockCardInfo;
import net.sf.json.JSONObject;

import java.util.Map;

public interface ShopStockCardServiceFacade {

    /**
     * 添加采购商品
     * */
    public int addShopStockCard(JSONObject stockCard,JSONObject param) throws Exception;

    /***
     *根据订单更新商品库存
     * */
    public int upProductStockByOrderNo(JSONObject param) throws Exception;


    /**
     * 查询商品的卡密
     * */
    public ShopStockCardInfo getShopCard(String productId,Byte status);

    /**
     * 通过主键更新卡密信息
     * */
    public int upShopCardById(Map<String,String> cardInfo);


    /**
     * *根据pay_shop_product更新商品库存
     * */
    public int upProductStockByPid(Map<String,String> cardInfo , ShopProductInterfaceInfo productInfo, ShopStockCardInfo shopStockCardInfo) throws Exception;

    /**
     * 查找某个用户买过的卡密个数
     */
    public int getBoughtCardCount(String customerId);

    /**
     * 查找某个用户买过的卡密
     */
    public GetShopStockCardResponse getCardM(String customerId);
}
