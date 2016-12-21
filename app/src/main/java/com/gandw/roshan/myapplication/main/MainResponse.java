package com.gandw.roshan.myapplication.main;

import gandw.com.network.ApiResponse;

/**
 * Author      : GandW
 * Time        : 2016/12/21 17:48
 * E-mail      : wshkwg@163.com
 * Description :
 */

public class MainResponse {

    public int status;

    public String msg;

    public Name data;

    public static class Name {
        public String firstName;
        public String lastName;
    }
}
