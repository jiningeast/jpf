package com.joiest.jpf.common.exception;

/**
 * 前台接口异常
 */
public class JpfInterfaceException extends RuntimeException{

    private String code;
    private String info;


    public JpfInterfaceException(String code, String info) {
        super();
        this.code = code;
        this.info = info;
    }

    public JpfInterfaceException(JpfInterfaceErrorInfo errorInfo) {
        super();
        this.code = errorInfo.getCode();
        this.info = errorInfo.getDesc();
    }

    public JpfInterfaceException(JpfInterfaceErrorInfo errorInfo, String info) {
        super();
        this.code = errorInfo.name();
        this.info = info;
    }

    public JpfInterfaceException(String code, String info, String message) {
        super(message);
        this.code = code;
        this.info = info;
    }

    public JpfInterfaceException(String code, String info, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.info = info;
    }

    public JpfInterfaceException(String code, String info, Throwable cause) {
        super(cause);
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("code", code)
                .add("info", info)
                .toString();
    }
}
