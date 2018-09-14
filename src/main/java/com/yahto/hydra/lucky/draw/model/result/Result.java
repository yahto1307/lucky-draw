package com.yahto.hydra.lucky.draw.model.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.yahto.hydra.lucky.draw.common.enums.DataCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yahto on 2018/9/10 9:56 PM
 *
 * @author yahto
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    private Meta meta;
    private Map<String, Object> data = new HashMap<>();
    private Integer dataCode;

    public Result success() {
        this.meta = new Meta(true, "操作成功");
        this.dataCode = DataCode.SUCCESS.getCode();
        return this;
    }

    public Result success(Map<String, Object> data) {
        this.meta = new Meta(true, "操作成功");
        this.dataCode = DataCode.SUCCESS.getCode();
        this.data.putAll(data);
        return this;
    }

    public Result add(String key, Object data) {
        this.data.put(key, data);
        return this;
    }

    public Result fail(String msg, DataCode dataCode) {
        this.meta = new Meta(false, msg);
        this.dataCode = dataCode.getCode();
        return this;
    }

    public Result exception(String msg, Throwable ex) {
        this.meta = new Meta(false, msg);
        this.data.put("cause", ex.getLocalizedMessage());
        this.dataCode = DataCode.SYSTEMERROR.getCode();
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public Object getData() {
        return data;
    }

    public Integer getDataCode() {
        return dataCode;
    }

    public class Meta {

        private boolean success;
        private String message;

        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
