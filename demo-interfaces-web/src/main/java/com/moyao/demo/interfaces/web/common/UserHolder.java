package com.moyao.demo.interfaces.web.common;

public class UserHolder {

    private static ThreadLocal<Long> userIdHolder = new ThreadLocal<>();

    public static void set(Long id) {
        userIdHolder.set(id);
    }

    public static void remove() {
        userIdHolder.remove();
    }

    public static Long get() {
        return userIdHolder.get();
    }

}
