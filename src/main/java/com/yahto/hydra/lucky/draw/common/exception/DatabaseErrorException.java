package com.yahto.hydra.lucky.draw.common.exception;

/**
 * Created by yahto on 2018/9/10 9:57 PM
 *
 * @author yahto
 */
public class DatabaseErrorException extends RuntimeException {
    private static final long serialVersionUID = 944668950190113275L;
    private String msg;

    public DatabaseErrorException(Throwable throwable) {
        super(throwable);
    }

    public DatabaseErrorException(String msg) {
        super(msg);
    }

    public DatabaseErrorException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
