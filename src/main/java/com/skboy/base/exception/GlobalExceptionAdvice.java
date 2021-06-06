package com.skboy.base.exception;


import com.skboy.base.enums.ResponseCode;
import com.skboy.base.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义异常处理类
 * @author skboy
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {



    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<?> handleException(HttpServletRequest req, BusinessException ex){
        Map<String, Object> error = new HashMap<>(2);
        error.put("code",ex.getCode());
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(code= HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseResult handleException(HttpServletRequest req, Exception e){

        log.info(e.getMessage());
        return new ResponseResult(ResponseCode.SYSTEM_INNER_ERROR);
    }

    @ExceptionHandler(value = HttpException.class)
    public ResponseEntity<?> handleHttpException(HttpServletRequest req, HttpException ex){

        Map<String, Object> error = new HashMap<>(2);
        error.put("code",ex.getCode());
        error.put("message", ex.getMessage());
        HttpStatus httpStatus = HttpStatus.resolve(ex.getHttpStatusCode());
        return new ResponseEntity<>(error, httpStatus);
    }

    //监听验证Validate抛出的exception
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ResponseResult handleBeanValidation(HttpServletRequest req, MethodArgumentNotValidException e) {


        List<ObjectError> errors = e.getBindingResult().getAllErrors();
        String message = this.formatAllErrorMessages(errors);

        return new ResponseResult(ResponseCode.PARAM_ERROR, message);
    }

    //监听验证url参数抛出的exception
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code= HttpStatus.BAD_REQUEST)
    public ResponseResult handleConstraintException(HttpServletRequest req, ConstraintViolationException e){

        String message = e.getMessage();
        return new ResponseResult(ResponseCode.PARAM_ERROR, message);
    }

    private String formatAllErrorMessages(List<ObjectError> errors) {
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(error ->
                errorMsg.append(error.getDefaultMessage()).append(';')
        );
        return errorMsg.toString();
    }
}
