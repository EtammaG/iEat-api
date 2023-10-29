package com.etammag.ieat.exception;

import com.etammag.ieat.entity.dto.Result;
import com.etammag.ieat.exception.bean.CustomException;
import com.etammag.ieat.exception.bean.OverTimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public Result<String> handleUnknownException(Throwable e) {
        log.warn("Unknown Exception:", e);
        return Result.error("未知错误");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public Result<String> handleAccessDeniedException(AccessDeniedException e) {
        return Result.error("拒绝访问");
    }

    @ExceptionHandler(OverTimeException.class)
    public Result<String> handleOverTimeException(OverTimeException e) {
        return Result.error("尝试次数过多，请稍后尝试");
    }

    @ExceptionHandler(CustomException.class)
    public Result<String> handleCustomException(CustomException e) {
        return Result.error(e.getMessage());
    }

}
