package com.joiest.jpf.dto;

import com.google.common.base.MoreObjects;
import com.joiest.jpf.common.dto.JpfRequestDto;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class GetValueRequest extends JpfRequestDto {

    @NotEmpty(message = "编码不能为空")
    @Length(max = 30, message = "编码不能超过30位")
    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("super", super.toString())
                .toString();
    }
}
