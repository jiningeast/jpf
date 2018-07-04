package com.joiest.jpf.common.dto;

import com.google.common.base.MoreObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * 前台页面返回code
 */
public class YjResponseDto {

    /**
     * 状态码 默认"10000"为正常的响应码, 代表操作完成, 具体交易状态, 以交易业务状态码为准
     */
    private String Code = "10000";

    /**
     * 返回消息 默认正常响应下为"SUCCESS"
     */
    private String Info = "SUCCESS";

    private String desc= "Success";

    private Object Data;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public Object getData() {
        return Data;
    }



    public void setData(Object data) {
        this.Data = data;
    }
    public void clearData() {
        this.Data = null;
    }

    public void clear(){
        Code = null;
        Info = null;
        Data = null;
    }

    /**
     * Interface 设置错误代码和信息
     * @param errorCode
     * @param errorMsg
     */
    public void setInterfaceResponseError(String errorCode, String errorMsg) {
        this.setCode(errorCode);
        this.setInfo(errorMsg);
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("Code", Code)
                .add("Info", Info)
                .add("data", Data)
                .toString();
    }
}
