package com.joiest.jpf.dto;

import com.joiest.jpf.entity.TdorderInfo;
import java.util.List;

public class TdorderResponse {

    private List<TdorderInfo> list;

    private int count;

    public List<TdorderInfo> getList() {
        return list;
    }

    public void setList(List<TdorderInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
