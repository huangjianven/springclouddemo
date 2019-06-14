package com.tengyun.exception;

import com.tengyun.entity.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 异常统一处理 监听所有Controller，抛出异常后执行
 *
 * @author huangjianwen
 */

@RestControllerAdvice
@Slf4j
public class RestExceptionProcessorAdvice {

    /**
     * 业务异常
     */
    @ResponseStatus(HttpStatus.EXPECTATION_FAILED)
    @ExceptionHandler(BusinessException.class)
    public ResponseDTO handleBusinessException(BusinessException e) {
        log.warn("业务异常:{}", e.getErrorMessage());
        return ResponseDTO.failed(e.getErrorCode(), e.getErrorMessage());
    }


    /**
     * 路径无对应的handler异常
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseDTO handleNoHandlerFoundException(NoHandlerFoundException e) {
        String errorMsg = "无法找到资源:" + e.getRequestURL();
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_NOT_FOUND, errorMsg);
    }

    /**
     * 缺少请求参数
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseDTO handleMissingRequestParameterException(MissingServletRequestParameterException e) {
        String errorMsg = "缺少请求参数:" + e.getParameterName();
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

    /**
     * 参数解析失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseDTO handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        String errorMsg = "参数解析失败:" + e.getMessage();
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

    /**
     * 参数验证失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String errorMsg = "参数验证失败:" + String.format("%s:%s", field, code);
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

    /**
     * 参数绑定失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseDTO handleBindException(BindException e) {
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String errorMsg = "参数绑定失败:" + String.format("%s:%s", field, code);
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }


    /**
     * 参数验证失败
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseDTO handleServiceException(ConstraintViolationException e) {

        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String errorMsg = "参数验证失败:" + violation.getMessage();
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }


    /**
     * 不支持当前请求方法
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseDTO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        String errorMsg = "不支持当前请求方法:" + e.getMethod();
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_METHOD_NOT_ALLOWED, errorMsg);
    }

    /**
     * 不支持的媒体类型
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseDTO handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        String errorMsg = "媒体类型不支持:" + e.getContentType();
        log.warn(errorMsg, e);
        return ResponseDTO.failed(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, errorMsg);
    }

    /**
     * 未知系统异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ResponseDTO handleThrowableException(Throwable e) {
        String errorMsg = "系统出错!请联系管理员.";
        log.error("系统出错:" + e.getMessage(), e);
        return ResponseDTO.failed(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMsg);
    }
}
