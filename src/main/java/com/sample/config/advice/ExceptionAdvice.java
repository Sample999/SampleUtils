package com.sample.config.advice;

import com.sample.exception.BusinessException;
import com.sample.exception.ForbiddenException;
import com.sample.utils.StringUtils;
import com.sample.web.dto.common.Result;
import com.sample.web.dto.common.ResultEnum;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice(basePackages = "com.sample.web.controller")
public class ExceptionAdvice {

    @ExceptionHandler({BusinessException.class})
    public Result<?> handleBusinessException(BusinessException ex){
        return Result.failed(ex.getMessage());
    }

    @ExceptionHandler({ForbiddenException.class})
    public Result<?> handleForbiddenException(ForbiddenException ex) {
        return Result.failed(ResultEnum.FORBIDDEN);
    }


    /**
     * @param ex 参数校验不通过时抛出的异常处理
     * @return
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Result<?> handlerMethodArgumentNotValidationException(MethodArgumentNotValidException ex){
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder sb = new StringBuilder("校验失败");
        for(FieldError fieldError : bindingResult.getFieldErrors()){
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append("，");
        }
        String msg = sb.toString();
        if(StringUtils.hasText(msg)){
            return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(),msg);
        }
        return Result.failed(ResultEnum.VALIDATE_FAILED);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public Result<?> handlerConstraintViolationException(ConstraintViolationException ex){
        if(StringUtils.hasText(ex.getMessage())){
            return Result.failed(ResultEnum.VALIDATE_FAILED.getCode(), ex.getMessage());
        }
        return Result.failed(ResultEnum.VALIDATE_FAILED);
    }

    /**
     * 顶级异常捕获并统一处理，当其他异常无法处理时候选择使用
     */
    @ExceptionHandler({Exception.class})
    public Result<?> handle(Exception ex) {
        return Result.failed(ex.getMessage());
    }

}
