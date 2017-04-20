package io.github.lobt.refitretrofit.service.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Lobt
 * @version 0.1
 */

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request rawRequest = chain.request();
        Request.Builder builder = rawRequest.newBuilder();
        builder.header("Accept-Language", "zh-cn,zh")
                .header("Accept-Charset", "utf-8")
                .header("Content-type", "application/json")
                .header("apikey", "69c54795fd9d132d998fd5dd4a16ec04");

        HttpUrl url = rawRequest.url();
        HttpUrl.Builder urlBuilder = url.newBuilder();
        urlBuilder.addQueryParameter("common", "common query");

        builder.url(urlBuilder.build());

        return chain.proceed(builder.build());
    }
}
