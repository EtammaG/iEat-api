package com.etammag.ieat.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果类
 *
 * @author Eiji
 */
@ApiModel(value = "Result", description = "统一返回结果")
@Data
public class Result<T> {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "提示信息")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T data;

    @ApiModelProperty(value = "动态数据")
    private Map<String, Object> map;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.map = new HashMap<>();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(1, "success", data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<>(0, msg, null);
    }

    public Result<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}