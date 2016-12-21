package com.gandw.roshan.myapplication;

import android.app.Application;

import gandw.com.network.NetWorkConfig;
import gandw.com.network.RetrofitHelper;

/**
 * Author      : GandW
 * Time        : 2016/12/21 10:03
 * E-mail      : wshkwg@163.com
 * Description :
 */

public class GandwApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        NetWorkConfig config = new NetWorkConfig.Builder()
                .baseUrl("http://192.168.140.186:3000/")
                .build();
        RetrofitHelper.init(config);
    }
}
