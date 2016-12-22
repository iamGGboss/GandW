package com.gandw.roshan.myapplication.data.api.name;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Author      : GandW
 * Time        : 2016/12/21 10:19
 * E-mail      : wshkwg@163.com
 * Description : 测试网络架构
 */

public interface NameService {
    @GET("1")
    Observable<NameResponse> getJson();

    @GET("2")
    Observable<String> getString();
}
