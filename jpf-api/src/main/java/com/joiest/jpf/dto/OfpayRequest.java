package com.joiest.jpf.dto;

public class OfpayRequest {

    //用户编号
    private String userid;

    //ret_code 充值后状态，1代表成功，9代表撤消
    private String ret_code;

    //订单号
    private String sporder_id;

    //处理时间
    private String ordersuccesstime;

    //err_msg 失败原因(ret_code为1时，该值为空)
    private String err_msg;

    private String sign;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRet_code() {
        return ret_code;
    }

    public void setRet_code(String ret_code) {
        this.ret_code = ret_code;
    }

    public String getSporder_id() {
        return sporder_id;
    }

    public void setSporder_id(String sporder_id) {
        this.sporder_id = sporder_id;
    }

    public String getOrdersuccesstime() {
        return ordersuccesstime;
    }

    public void setOrdersuccesstime(String ordersuccesstime) {
        this.ordersuccesstime = ordersuccesstime;
    }

    public String getErr_msg() {
        return err_msg;
    }

    public void setErr_msg(String err_msg) {
        this.err_msg = err_msg;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
