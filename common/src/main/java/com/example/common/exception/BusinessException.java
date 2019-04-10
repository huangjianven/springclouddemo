package com.example.common.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletResponse;

/**
 * 业务异常类，表示发生业务异常
 *
 * @author huangjianwen 2017-08-27
 */
@Getter
@Setter
@Builder
public class BusinessException extends RuntimeException {

    private Integer errorCode;
    /**
     * 返回给ui的信息，给用户看的
     */
    private String errorMessage;
    /**
     * 错误的一些详细信息，例如：传入的参数列表
     */
    private Object data;

    public static BusinessException message(String msg) {
        return message(msg, HttpServletResponse.SC_EXPECTATION_FAILED);
    }

    public static BusinessException message(String msg, Integer errorCode) {
        return BusinessException.builder()
                .errorMessage(msg)
                .errorCode(errorCode)
                .build();
    }
}