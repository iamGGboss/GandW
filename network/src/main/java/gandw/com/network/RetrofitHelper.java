package gandw.com.network;


import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author      : GandW
 * Time        : 2016/12/20 15:17
 * E-mail      : wshkwg@163.com
 * Description : 网络的帮助类
 */

public class RetrofitHelper {

    private static Retrofit mRetrofit;

    private RetrofitHelper() {
    }

    public static void init(@NonNull NetWorkConfig config) {
        String baseUrl = config.getBaseUrl();
        if (TextUtils.isEmpty(baseUrl)) {
            //这里判断baseUrl有没有被初始化，如果没有，抛出异常
            throw new IllegalArgumentException("base url must be init");
        }
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(config.getWriteTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(config.isRetry());
        Interceptor interceptor = config.getInterceptor();
        if (null != interceptor) {
            builder.addInterceptor(interceptor);
        }
        OkHttpClient okHttpClient = builder.build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                /**
                 * 转换器的转换时是有序的，按从上到下的顺序
                 * 当第一个转换器未解析出来时，就按下面的转换器解析
                 * 一般只需两个就可以了，用来应对后台数据格式变化的情况
                 */
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(StringConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static Retrofit getRetrofit() {
        return mRetrofit;
    }

}
