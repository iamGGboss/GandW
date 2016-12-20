package gandw.com.network;

import okhttp3.Interceptor;

/**
 * Author      : GandW
 * Time        : 2016/12/20 15:41
 * E-mail      : wshkwg@163.com
 * Description : 配置超时时长，拦截器，baseUrl等。使用构造者模式创建网络的配置类，以保证用户未配置时也有默认值。
 */

public class NetWorkConfig {

    private static final long DEFAULT_CONNECTION_IMEOUT = 10_000;   //默认链接超时时间
    private static final long DEFAULT_WRITE_TIMEOUT = 10_000;    //默认写入超时时间
    private static final long DEFAULT_READ_TIMEOUT = 10_000; //默认读取超时时间
    private static final boolean DEFAULT_IS_RETRY = false;  //默认是否重试

    private Builder mBuilder;

    private NetWorkConfig() {
    }

    public String getBaseUrl() {
        return mBuilder.baseUrl;
    }

    public long getConnectTimeout() {
        return mBuilder.connectTimeout;
    }

    public long getWriteTimeout() {
        return mBuilder.writeTimeout;
    }

    public long getReadTimeout() {
        return mBuilder.readTimeout;
    }

    public boolean isRetry() {
        return mBuilder.isRetry;
    }

    public Interceptor getInterceptor() {
        return mBuilder.interceptor;
    }

    private NetWorkConfig(Builder builder) {
        mBuilder = builder;
    }

    public static class Builder {
        protected long connectTimeout = DEFAULT_CONNECTION_IMEOUT;
        protected long writeTimeout = DEFAULT_WRITE_TIMEOUT;
        protected long readTimeout = DEFAULT_READ_TIMEOUT;
        protected boolean isRetry = DEFAULT_IS_RETRY;
        protected String baseUrl;
        protected Interceptor interceptor;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder isRetry(boolean retry) {
            isRetry = retry;
            return this;
        }

        public Builder addInterceptor(Interceptor interceptor) {
            this.interceptor = interceptor;
            return this;
        }

        public NetWorkConfig build() {
            return new NetWorkConfig(this);
        }
    }
}
