package com.joiest.jpf.dto;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.entity.WeixinMpInfo;
import java.util.List;

public class GetWeixinMpResponse extends JpfResponseDto{

    private List<WeixinMpInfo> list;

    private int count;

    public List<WeixinMpInfo> getList() {
        return list;
    }

    public void setList(List<WeixinMpInfo> list) {
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
