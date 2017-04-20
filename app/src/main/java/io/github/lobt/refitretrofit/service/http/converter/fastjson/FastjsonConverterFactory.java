package io.github.lobt.refitretrofit.service.http.converter.fastjson;

import android.text.TextUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import io.github.lobt.refitretrofit.service.http.converter.QueryKey;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @author Lobt
 * @version 0.1
 */

public final class FastjsonConverterFactory extends Converter.Factory {

    public static FastjsonConverterFactory create() {
        return new FastjsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (annotations.length > 1) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof QueryKey) {
                    String queryKey = ((QueryKey) annotation).value();
                    if (!TextUtils.isEmpty(queryKey)) {
                        return new FastjsonResponseBodyConverter<>(type, queryKey);
                    }
                }
            }
        }
        return new FastjsonResponseBodyConverter<>(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastjsonRequestBodyConverter<>();
    }
}
