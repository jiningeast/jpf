package com.joiest.jpf.dto;

import com.google.common.base.MoreObjects;
import com.joiest.jpf.common.dto.JpfResponseDto;

public class GetValueResponse extends JpfResponseDto{

    /**
     * 系统参数值
     */
    private String paraValue;

    public String getParaValue() {
        return paraValue;
    }

    public void setParaValue(String paraValue) {
        this.paraValue = paraValue;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("paraValue", paraValue)
                .add("super", super.toString())
                .toString();
    }
}
