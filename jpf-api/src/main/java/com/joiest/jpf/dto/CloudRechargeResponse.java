package com.joiest.jpf.dto;

import com.joiest.jpf.entity.CloudRechargeInfo;

import java.util.List;

public class CloudRechargeResponse {

    private List<CloudRechargeInfo> list;

    private int count;

    /**
     * 总订单数
     */
    private Long allOrdersCount;

    public List<CloudRechargeInfo> getList() {
        return list;
    }

    public void setList(List<CloudRechargeInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }



}
