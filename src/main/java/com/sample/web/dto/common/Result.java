package com.sample.web.dto.common;

import com.alibaba.fastjson.serializer.JSONSerializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)//转JSON的时候去掉null值
public class Result <T>{
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(T data){
        return success(ResultEnum.SUCCESS.getMessage(), data);
    }

    public static <T> Result<T> success(String message,T data){
        return new Result<>(
                ResultEnum.SUCCESS.getCode(),
                message,
                data);
    }

    public static Result<?> failed(){
        return failed(ResultEnum.COMMON_FAILED.getMessage());
    }
    public static Result<?> failed(Integer code, String message) {
        return new Result<>(
                code,
                message,
                null
        );
    }
    public static Result<?> failed(String message){
        return failed(ResultEnum.COMMON_FAILED.getCode(),message);

    }

    public static Result<?> failed(IResult errorResult){
        return new Result<>(errorResult.getCode(), errorResult.getMessage(),null);
    }


    public static <T> Result<T> instance(Integer code, String message,T data){
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }



}
