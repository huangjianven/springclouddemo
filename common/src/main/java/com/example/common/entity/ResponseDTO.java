package com.example.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


/**
 * Json通用返回类
 *
 * @author huangjianwen
 * @version 2018-05-04 16:30
 */
@Getter
@Setter
@Builder
public class ResponseDTO<T> {

    final static int FAILED_STATUS = 0;
    final static int SUCCESS_STATUS = 1;

    final static String DEFAULT_FAILED_MSG = "失败";
    final static String DEFAULT_SUCCESS_MSG = "成功";
    /**
     * 成功/失败 状态标志位
     */
    @JSONField(ordinal = 1)
    private int status;
    /**
     * 返回码(以http status code 为基础扩充)
     */
    @JSONField(ordinal = 2)
    private int code;
    /**
     * 返回信息
     */
    @JSONField(ordinal = 3)
    private String msg;

    /**
     * 返回数据
     */
    @JSONField(ordinal = 4)
    private T data;

    public ResponseDTO() {
    }

    public ResponseDTO(int status, int code, String msg, T data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 成功返回:默认
     *
     */
    public static ResponseDTO success() {
        return ResponseDTO.builder()
                .code(200)
                .status(SUCCESS_STATUS)
                .msg(DEFAULT_SUCCESS_MSG)
                .build();
    }

    /**
     * 成功返回:自定消息
     */
    public static ResponseDTO success(String msg) {
        return ResponseDTO.builder()
                .code(200)
                .status(SUCCESS_STATUS)
                .msg(msg)
                .build();
    }

    /**
     * 成功返回:自定返回码和消息
     *
     */
    public static ResponseDTO success(int code, String msg) {
        return ResponseDTO.builder()
                .status(SUCCESS_STATUS)
                .code(code)
                .msg(msg)
                .build();
    }

    /**
     * 成功返回:自定返回码,消息,数据
     *
     */
    public static <T> ResponseDTO<T> success(int code, String msg, T data) {
        return ResponseDTO.<T>builder()
                .status(SUCCESS_STATUS)
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 成功返回:自定消息,数据
     *
     */
    public static <T> ResponseDTO<T> success(String msg, T data) {
        return ResponseDTO.<T>builder()
                .code(200)
                .status(SUCCESS_STATUS)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 成功返回:自定数据
     *
     */
    public static <T> ResponseDTO<T> success(T data) {
        return ResponseDTO.success(DEFAULT_SUCCESS_MSG, data);
    }

    /**
     * 失败返回:默认

     */
    public static ResponseDTO failed() {
        return ResponseDTO.builder()
                .status(FAILED_STATUS)
                .msg(DEFAULT_FAILED_MSG)
                .build();
    }

    /**
     * 失败返回:自定消息
     *
     */
    public static ResponseDTO failed(String msg) {
        return ResponseDTO.builder()
                .status(FAILED_STATUS)
                .msg(msg)
                .build();
    }

    /**
     * 失败返回:自定返回码,消息
     */
    public static ResponseDTO failed(int code, String msg) {
        return ResponseDTO.<String>builder()
                .status(FAILED_STATUS)
                .code(code)
                .msg(msg)
                .build();
    }

    /**
     * 失败返回:自定返回码,消息,数据
     */
    public static <T> ResponseDTO<T> failed(int code, String msg, T data) {
        return ResponseDTO.<T>builder()
                .status(FAILED_STATUS)
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }

    /**
     * 失败返回:自定消息,数据
     */
    public static <T> ResponseDTO<T> failed(String msg, T data) {
        return ResponseDTO.<T>builder()
                .status(FAILED_STATUS)
                .msg(msg)
                .data(data)
                .build();
    }

}
