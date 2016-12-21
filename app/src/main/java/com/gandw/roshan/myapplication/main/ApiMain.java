package com.gandw.roshan.myapplication.main;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author      : GandW
 * Time        : 2016/12/21 10:19
 * E-mail      : wshkwg@163.com
 * Description : 测试网络架构
 */

public interface ApiMain {
    @GET("1")
    Observable<MainResponse> getJson();

    @GET("2")
    Observable<String> getString();
}
