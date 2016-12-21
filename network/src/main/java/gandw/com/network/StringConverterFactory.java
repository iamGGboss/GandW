package gandw.com.network;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Author      : GandW
 * Time        : 2016/12/21 13:35
 * E-mail      : wshkwg@163.com
 * Description :
 */

public class StringConverterFactory extends Converter.Factory {
    /**
     * @return 返回自身实例
     */
    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    /**
     * @param type        数据类型
     * @param annotations 数据注释
     * @param retrofit
     * @return 返回string的转换器
     */
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (type == String.class) {
            //如果是字符串，返回
            return new StringConverter();
        }
        return null;
    }
}
