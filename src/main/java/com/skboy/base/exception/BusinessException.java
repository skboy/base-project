package com.skboy.base.exception;


import com.skboy.base.enums.ResponseCode;

/**
 * 全局业务异常
 *
 * @author tangguangwei
 * @since v1.0.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 4868842805385777275L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public BusinessException() {}

    public BusinessException(String message) {
        super(message);
        this.code = -1;
    }


    public BusinessException(ResponseCode responseCode) {
        super(responseCode.message());
        this.code = responseCode.code();
    }
}