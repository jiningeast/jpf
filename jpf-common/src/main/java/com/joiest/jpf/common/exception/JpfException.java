package com.joiest.jpf.common.exception;

/**
 * 支付交易系统通用异常
 *
 * Created by zjf1650 on 05/07/2017.
 */
public class JpfException extends RuntimeException{

    private String errorCode;
    private String errorMsg;


    public JpfException(String errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public JpfException(JpfErrorInfo errorInfo) {
        super();
        this.errorCode = errorInfo.name();
        this.errorMsg = errorInfo.desc();
    }

    public JpfException(JpfErrorInfo errorInfo, String errorMsg) {
        super();
        this.errorCode = errorInfo.name();
        this.errorMsg = errorMsg;
    }

    public JpfException(String errorCode, String errorMsg, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public JpfException(String errorCode, String errorMsg, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public JpfException(String errorCode, String errorMsg, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    @Override
    public String toString() {
        return com.google.common.base.MoreObjects.toStringHelper(this)
                .add("errorCode", errorCode)
                .add("errorMsg", errorMsg)
                .toString();
    }
}
