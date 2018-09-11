package com.joiest.jpf.facade;

import com.joiest.jpf.dto.GetShopStockCardResponse;
import net.sf.json.JSONObject;

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
     * 查找某个用户买过的卡密个数
     */
    public int getBoughtCardCount(String customerId);

    /**
     * 查找某个用户买过的卡密
     */
    public GetShopStockCardResponse getCardM(String customerId);
}
