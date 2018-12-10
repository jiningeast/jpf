package com.joiest.jpf.entity;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

/**
 * @Auther: admin
 * @Date: 2018/12/5 10:24
 * @Description:
 */

public class PayShopCompanyChargeInfo {
    private Integer total;
    private List<Map<String,Object>> list;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }
}
