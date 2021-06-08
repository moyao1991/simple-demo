package com.moyao.demo.interfaces.rpc.common;

import java.io.Serializable;
import lombok.Getter;

@Getter
public class Result<T> implements Serializable{

    private Boolean success;

    private T data;

    private String code;

    private String msg;

    private Result(Boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    private Result(Boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static Result success() {
        return success(null);
    }

    public static <T> Result success(T data) {
        return new Result<>(true, data);
    }

    public  static Result fail() {
        return fail(null);
    }

    public  static Result fail(String msg) {
        return fail(null, msg);
    }

    public  static Result fail(String code, String msg) {
        return fail(code, msg, null);
    }

    public  static <T> Result fail(String code, String msg, T data) {
        Result result = new Result(false, msg);
        result.code = code;
        result.data = data;
        return result;
    }

}
