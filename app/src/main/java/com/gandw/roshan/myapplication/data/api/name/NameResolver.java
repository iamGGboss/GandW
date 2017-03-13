package com.gandw.roshan.myapplication.data.api.name;

import gandw.com.network.RetrofitHelper;
import retrofit2.Retrofit;
import rx.Observable;

/**
 * Author      : GandW
 * Time        : 2016/12/22 10:02
 * E-mail      : wshkwg@163.com
 * Description : 数据解析器
 */

public class NameResolver {

    private static NameResolver mInstance;

    private NameResolver() {
    }

    public static NameResolver getInstance() {
        if (null == mInstance) {
            mInstance = new NameResolver();
        }
        return mInstance;
    }

    public Observable<NameResponse> getName() {
        Retrofit retrofit = RetrofitHelper.getRetrofit();
        NameService nameService = retrofit.create(NameService.class);
        Observable<NameResponse> observable = nameService.getJson();
        return observable;
    }

    public Observable<String> getString() {
        Retrofit retrofit = RetrofitHelper.getRetrofit();
        NameService nameService = retrofit.create(NameService.class);
        Observable<String> observable = nameService.getString();
        return observable;
    }
}
