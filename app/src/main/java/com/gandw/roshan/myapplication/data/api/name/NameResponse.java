package com.gandw.roshan.myapplication.data.api.name;

/**
 * Author      : GandW
 * Time        : 2016/12/22 10:22
 * E-mail      : wshkwg@163.com
 * Description :
 */

public class NameResponse {
    public int status;
    public String msg;
    public Name data;

    class Name {
        public String firstName;
        public String lastName;
    }
}
