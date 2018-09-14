package com.yahto.hydra.lucky.draw.common.enums;

/**
 * Created by yahto on 2018/9/10 9:55 PM
 *
 * @author yahto
 */
public enum DataCode {
    SUCCESS(200),
    DATABASEERROR(501),
    INVALIDERROR(502),
    SERVICEERROR(503),
    SYSTEMERROR(500),
    NOLOGIN(504);
    private Integer code;

    DataCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
