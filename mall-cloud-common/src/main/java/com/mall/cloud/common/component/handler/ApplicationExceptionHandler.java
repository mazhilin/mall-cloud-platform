package com.mall.cloud.common.component.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.cloud.common.restful.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>封装Qicloud项目Application类.<br></p>
 * <p>//TODO...<br></p>
 *
 * @author Powered by marklin 2020-10-29 00:56
 * @version 1.0.0
 * <p>Copyright © 2018-2020 Pivotal Cloud Technology Systems Incorporated. All rights reserved.<br></p>
 */
@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {
    @Autowired
    private ObjectMapper objectMapper;

    // ---------- 参数校验 ----------

    /**
     * 忽略参数异常处理器
     *
     * @param e 忽略参数异常
     * @return ResultObject
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseResult parameterMissingExceptionHandler(MissingServletRequestParameterException e,
                                                           HttpServletRequest request) {
        ResponseResult response =new ResponseResult();
        printLog(e, request);
        return response.setError("请求参数 " + e.getParameterName() + " 不能为空");
    }

    /**
     * 媒体类型不支持异常处理器
     *
     * @param e 类型不匹配异常
     * @return resultObject
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseResult HttpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e,
                                                                    HttpServletRequest request) {
        ResponseResult response =new ResponseResult();
        printLog(e, request);
        return response.setError("请求类型错误，请检查conten-type是否正确");
    }

    /**
     * 缺少请求体异常处理器
     *
     * @param e 缺少请求体异常
     * @return ResultObject
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseResult parameterBodyMissingExceptionHandler(HttpMessageNotReadableException e,
                                                               HttpServletRequest request) {
        ResponseResult response =new ResponseResult();
        printLog(e, request);
        return response.setError("参数体校验错误");
    }

    /**
     * Bean Validation参数校验异常处理器
     * @param e 参数验证异常
     * @return ResultObject
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult parameterExceptionHandler(MethodArgumentNotValidException e,
                                                                HttpServletRequest request) {
        ResponseResult response =new ResponseResult();
        printLog(e, request);
        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 这里列出了全部错误参数，这里用List传回
        List<String> fieldErrorMsg = new ArrayList<>();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                errors.forEach(msg -> fieldErrorMsg.add(msg.getDefaultMessage()));
                return response.setError("请求参数校验错误", fieldErrorMsg);
            }
        }
        fieldErrorMsg.add("未知异常");
        return response.setError("请求参数校验错误", fieldErrorMsg);
    }

    /**
     * 参数校验过程中发生的异常
     * @param e 参数校验异常
     * @return resultObject
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ValidationException.class)
    public ResponseResult validationExceptionHandler(ValidationException e,
                                                           HttpServletRequest request) {
        ResponseResult response =new ResponseResult();
        printLog(e, request);
        String message = e.getCause().getMessage();
        if(message != null) {
            return response.setError(message);
        }
        return response.setError("请求参数校验错误");
    }

    // --------- 业务逻辑异常 ----------

    /**
     * 自定义异常，捕获程序逻辑中的错误，业务中出现异常情况直接抛出异常即可
     * @param e 自定义异常
     * @return ResultObject
     */
/*    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({GlobalException.class})
    public ResultObject<String> paramExceptionHandler(GlobalException e,
                                                      HttpServletRequest request) {
        printLog(e, request);
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (!StringUtils.isEmpty(e.getMessage())) {
            return ResultObject.createByErrorMessage(e.getMessage());
        }
        return ResultObject.createByErrorMessage("程序出错，捕获到一个未知异常");
    }*/

    // ---------- 全局通用异常 ----------

    /**
     * 通用异常处理
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Throwable.class)
    public ResponseResult exceptionHandler(Throwable e,
                                                 HttpServletRequest request,
                                                 HttpServletResponse response) {
        ResponseResult responseResult =new ResponseResult();
        printLog(e, request);
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return responseResult.setError("服务器异常");
    }

    /**
     * 打印日志
     * @param e Throwable
     * @param request HttpServletRequest
     */
    private void printLog(Throwable e, HttpServletRequest request) {
        log.error("【method】: {}【uri】: {}【errMsg】: {}【params】:{}",
                request.getMethod(), request.getRequestURI(), e.getMessage(), buildParamsStr(request), e);
    }

    /**
     * 请求的参数拼接str
     * @param request HttpServletRequest
     * @return 请求参数
     */
    private String buildParamsStr(HttpServletRequest request) {
        try {
            return objectMapper.writeValueAsString(request.getParameterMap());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
