package com.gandw.common;

/**
 * Author      : GandW
 * Time        : 2016/12/20 10:51
 * E-mail      : wshkwg@163.com
 * Description : 使用之前的检测类
 */

public class Preconditions {
    private Preconditions() {
    }

    public static <T> T checkNotNull(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        return value;
    }

    public static <T> T checkNotNull(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
        return value;
    }

}
