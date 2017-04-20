package io.github.lobt.refitretrofit.service.http;

import android.util.Log;

import io.github.lobt.refitretrofit.service.http.api.GithubService;
import io.github.lobt.refitretrofit.service.http.api.TngouApiService;
import io.github.lobt.refitretrofit.service.http.api.WeatherService;
import io.github.lobt.refitretrofit.service.http.converter.fastjson.FastjsonConverterFactory;
import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @author Lobt
 * @version 0.1
 */

public class HttpRequest {

    private static volatile GithubService githubService;
    private static volatile TngouApiService tgApiService;
    private static volatile WeatherService weatherService;

    public static GithubService getGithubApi() {
        if (githubService == null) {
            synchronized (HttpRequest.class) {
                githubService = createApiService("https://api.github.com/", GithubService.class);
            }
        }
        return githubService;
    }

    public static TngouApiService getTngouApi() {
        if (tgApiService == null) {
            synchronized (HttpRequest.class) {
                if (tgApiService == null) {
                    tgApiService = createApiService("http://www.tngou.net/api/", TngouApiService.class);
                }
            }
        }
        return tgApiService;
    }

    public static WeatherService getWeatherApi() {
        if (weatherService == null) {
            synchronized (HttpRequest.class) {
                if (weatherService == null) {
                    weatherService = createApiService("http://rap.taobao.org/mockjsdata/17471/refitretrofit/", WeatherService.class);
                }
            }
        }
        return weatherService;
    }


    public static <T> void enqueue(Call<HttpModel<T>> call, final AbsRequestCallback callback) {
        call.enqueue(new Callback<HttpModel<T>>() {
            @Override
            public void onResponse(Call<HttpModel<T>> call, Response<HttpModel<T>> response) {
                if (response.isSuccessful()) {
                    if (callback != null) {
                        HttpModel<T> result = response.body();
                        if (result.code == ResponseCodeConstant.RESPONSE_SUCCESS) {
                            callback.onSuccess(result.data);
                        } else {
                            callback.onFailure(result.code, result.message);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<HttpModel<T>> call, Throwable t) {
                if (callback != null) {
                    callback.onFailure(-1, Log.getStackTraceString(t));
                }
            }
        });
    }

    private static <T> T createApiService(String baseUrl, Class<T> service) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(buildHttpClient())
                .addConverterFactory(FastjsonConverterFactory.create())
                .build();
        return retrofit.create(service);
    }

    private static OkHttpClient buildHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(new RequestInterceptor())
                .addInterceptor(loggingInterceptor);
        return builder.build();
    }

    private HttpRequest() {
    }
}
