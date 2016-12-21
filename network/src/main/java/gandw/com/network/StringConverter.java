package gandw.com.network;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Author      : GandW
 * Time        : 2016/12/21 13:52
 * E-mail      : wshkwg@163.com
 * Description : 自定义字符串转换器
 */

public class StringConverter implements Converter<ResponseBody, String> {
    @Override
    public String convert(ResponseBody value) throws IOException {
        return value.string();
    }
}
