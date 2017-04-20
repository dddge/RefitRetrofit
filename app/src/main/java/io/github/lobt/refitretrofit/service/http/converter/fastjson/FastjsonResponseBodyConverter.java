package io.github.lobt.refitretrofit.service.http.converter.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.lang.reflect.Type;

import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * @author Lobt
 * @version 0.1
 */
final class FastjsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type clazz;
    private final String queryKey;

    FastjsonResponseBodyConverter(Type type) {
        this.clazz = type;
        this.queryKey = null;
    }

    FastjsonResponseBodyConverter(Type type, String queryKey) {
        this.clazz = type;
        this.queryKey = queryKey;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        if (queryKey != null) {

            HttpModel<JSONObject> httpModel = JSON.parseObject(value.string(), new TypeReference<HttpModel<JSONObject>>() {
            });

            HttpModel result = new HttpModel();
            result.code = httpModel.code;
            result.message = httpModel.message;
            result.data = httpModel.data.get(queryKey);

            value = ResponseBody.create(value.contentType(), JSON.toJSONString(result));

        }
        return JSON.parseObject(value.string(), clazz);
    }
}
