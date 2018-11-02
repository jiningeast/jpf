package com.joiest.jpf.dto;

import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.entity.ChargeCompanyChargeInfo;
import com.joiest.jpf.entity.ChargeCompanyInfo;

import java.util.List;

public class GetChargeCompanyChargeResponse extends JpfResponseDto {

    private int count;

    private ChargeCompanyInfo  chargeCompanyInfo;

    private List<ChargeCompanyChargeInfo> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ChargeCompanyChargeInfo> getList() {
        return list;
    }

    public void setList(List<ChargeCompanyChargeInfo> list) {
        this.list = list;
    }

    public ChargeCompanyInfo getChargeCompanyInfo() {
        return chargeCompanyInfo;
    }

    public void setChargeCompanyInfo(ChargeCompanyInfo chargeCompanyInfo) {
        this.chargeCompanyInfo = chargeCompanyInfo;
    }
}
