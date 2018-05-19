package com.joiest.jpf.common.dto;

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

    private Object data;

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
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
