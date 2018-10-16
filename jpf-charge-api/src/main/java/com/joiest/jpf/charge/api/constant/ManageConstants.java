package com.joiest.jpf.charge.api.constant;

import java.util.HashMap;
import java.util.Map;

public class ManageConstants {

    /**
     * pay_charge_product 运营商类型
     */
    public static Map<String,String> CHARGEPRODUCTTYPE= new HashMap<String, String>() {
        {
            put("1", "移动");
            put("2", "联通");
            put("3", "电信");

        }
    };

}
