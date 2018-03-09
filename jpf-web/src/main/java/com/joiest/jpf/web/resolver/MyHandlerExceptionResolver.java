package com.joiest.jpf.web.resolver;


import com.joiest.jpf.common.dto.JpfResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfException;
import com.joiest.jpf.common.util.JsonUtils;
import com.joiest.jpf.web.util.ServletUtils;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;


public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
	/**
	 * Logger for this class
	 */
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(MyHandlerExceptionResolver.class);

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		JpfResponseDto jpfRet = new JpfResponseDto();
		if (ex instanceof BindException) {
			logger.error(ex.getMessage());
			BindException bindException = (BindException) ex;
			String errorMsg = getBindExceptionErrorMsg(bindException);
			jpfRet.setResponseError(JpfErrorInfo.INVALID_PARAMETER.name(), errorMsg);
		} else if (ex instanceof JpfException) {
			JpfException ptsException = (JpfException) ex;
			jpfRet.setResponseError(ptsException.getErrorCode(), ptsException.getErrorMsg());
		} else if (ex instanceof SQLException) {
			logger.error(ex.getMessage(), ex);
			jpfRet.setResponseError(JpfErrorInfo.DAL_ERROR.name(), "数据库访问异常");
		} else {
			logger.error(ex.getMessage(), ex);
			jpfRet.setResponseError(JpfErrorInfo.SYSTEM_ERROR.name(), "系统未知异常");
		}
		String requestUrl = request.getRequestURL().toString();
		// 写请求参数日志
		String requestParas = ServletUtils.getRequestParameter(request);
		logger.error("remoteHost:{}, requestUrl:{}, requestParas:{}, retCode:{} , retMsg:{}",
				request.getRemoteHost(), requestUrl, requestParas, jpfRet.getRetCode(), jpfRet.getRetMsg());
		String retJson = JsonUtils.toJson(jpfRet);
		request.setAttribute("retDetail", retJson);
		return new ModelAndView("/error");
	}


	private String getBindExceptionErrorMsg(BindException bindException) {
		List<FieldError> fieldErrors = bindException.getBindingResult().getFieldErrors();
		StringBuilder sb = new StringBuilder();
		int index = 0;
		for (FieldError fieldError : fieldErrors) {
			index++;
			sb.append(fieldError.getField())
					.append(":")
					.append(fieldError.getDefaultMessage());
			if (index < fieldErrors.size()) {
				sb.append("|");
			}
		}
		return sb.toString();
	}
}
