package com.joiest.jpf.common.dto;

import com.google.common.base.MoreObjects;

/**
 * 接口返回基类
 */
public class JpfResponseDto implements java.io.Serializable
{
	/**
	 * 系统编码
	 */
	private static final String JPF_SYSTEM_CODE = "JPF";

	/**
	 * 状态码 默认"0000"为正常的响应码, 代表操作完成, 具体交易状态, 以交易业务状态码为准
	 */
	private String retCode = "0000";

	/**
	 * 返回消息 默认正常响应下为"操作完成"
	 */
	private String retMsg = "操作完成"  ;

	/**
	 *
	 */
	private String remark;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setResponseError(String errorCode, String errorMsg) {
		this.setRetCode(JPF_SYSTEM_CODE + "." + errorCode);
		this.setRetMsg(errorMsg);
	}

	/**
	 * Interface 设置错误代码和信息
	 * @param errorCode
	 * @param errorMsg
	 */
	public void setInterfaceResponseError(String errorCode, String errorMsg) {
		this.setRetCode(errorCode);
		this.setRetMsg(errorMsg);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("retCode", retCode)
				.add("retMsg", retMsg)
				.add("remark", remark)
				.toString();
	}
}
