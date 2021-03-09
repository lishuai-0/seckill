package com.bug1024.seckill.domain;

public class RequestResult<T> {

    private Integer code;
    private String msg;
    private T data;
    private String url;

    public RequestResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RequestResult(Integer code, String msg, T data, String url) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestResult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
