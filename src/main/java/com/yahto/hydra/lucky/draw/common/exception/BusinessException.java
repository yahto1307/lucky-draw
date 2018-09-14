package com.yahto.hydra.lucky.draw.common.exception;

/**
 * Created by yahto on 2018/9/10 9:57 PM
 *
 * @author yahto
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -1361439924458198354L;
    private String msg;

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
