package com.skboy.base.exception;


import com.skboy.base.enums.ResponseCode;

/**
 * @author tangguangwei
 */
public class HttpException extends RuntimeException{
    protected Integer code;

    protected Integer httpStatusCode= 500;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public HttpException(String message) {
        super(message);
        this.code = 500;
    }


    public HttpException(ResponseCode responseCode) {
        super(responseCode.message());
        this.code = responseCode.code();
    }
}
