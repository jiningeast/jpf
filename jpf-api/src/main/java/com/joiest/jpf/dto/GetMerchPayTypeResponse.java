package com.joiest.jpf.dto;

import com.joiest.jpf.common.dto.JpfRequestDto;
import com.joiest.jpf.entity.MerchantPayTypeInfo;

import java.util.List;

public class GetMerchPayTypeResponse extends JpfRequestDto{

    private List<MerchantPayTypeInfo> payTypeInfos;

    private int count;

    public List<MerchantPayTypeInfo> getPayTypeInfos() {
        return payTypeInfos;
    }

    public void setPayTypeInfos(List<MerchantPayTypeInfo> payTypeInfos) {
        this.payTypeInfos = payTypeInfos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
