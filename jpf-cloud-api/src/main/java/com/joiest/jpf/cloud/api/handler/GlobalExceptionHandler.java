package com.joiest.jpf.cloud.api.handler;


import com.joiest.jpf.common.dto.YjResponseDto;
import com.joiest.jpf.common.exception.JpfErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceErrorInfo;
import com.joiest.jpf.common.exception.JpfInterfaceException;
import com.joiest.jpf.cloud.api.util.ServletUtils;
import com.joiest.jpf.common.util.ToolUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

/**
 * 统一异常处理
 * Created by zjf1650 on 09/08/2017.
 */
@ControllerAdvice(basePackages = {"com.joiest.jpf.cloud.api"})
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest request, Exception ex) throws Exception {
        YjResponseDto response = new YjResponseDto();
        if (ex instanceof BindException) {
            logger.error(ex.getMessage());
            BindException bindException = (BindException) ex;
            String errorMsg = getBindExceptionErrorMsg(bindException);
            response.setInterfaceResponseError(JpfErrorInfo.INVALID_PARAMETER.name(), errorMsg);
        } else if (ex instanceof JpfInterfaceException) {
            JpfInterfaceException ptsException = (JpfInterfaceException) ex;
            response.setInterfaceResponseError(ptsException.getCode(), ptsException.getInfo());
        } else if (ex instanceof SQLException) {
            logger.error(ex.getMessage(), ex);
            response.setInterfaceResponseError(JpfErrorInfo.DAL_ERROR.name(), "数据库访问异常");
        } else {
            logger.error(ex.getMessage(), ex);
            response.setInterfaceResponseError(JpfErrorInfo.SYSTEM_ERROR.name(), "系统未知异常");
        }
        String requestUrl = request.getRequestURL().toString();
        // 写请求参数日志
        String requestParas = ServletUtils.getRequestParameter(request);
        logger.error("remoteHost:{}, requestUrl:{}, requestParas:{}, retCode:{} , retMsg:{}",
                request.getRemoteHost(), requestUrl, requestParas, response.getCode(), response.getInfo());

        return ToolUtils.toJsonBase64(response.getCode(), response.getInfo(), "");
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
