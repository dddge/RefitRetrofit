package io.github.lobt.refitretrofit.service.http;

import android.util.Log;

import io.github.lobt.refitretrofit.service.http.api.GithubService;
import io.github.lobt.refitretrofit.service.http.api.JinyiFanyiService;
import io.github.lobt.refitretrofit.service.http.api.TngouApiService;
import io.github.lobt.refitretrofit.service.http.converter.gson.GsonConverterFactory;
import io.github.lobt.refitretrofit.service.http.model.HttpModel;
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
    private static volatile JinyiFanyiService jyfyService;

    public static GithubService getGithubApi() {
        if (githubService == null) {
            synchronized (HttpRequest.class) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                githubService = retrofit.create(GithubService.class);
            }
        }
        return githubService;
    }

    public static TngouApiService getTngouApi() {
        if (tgApiService == null) {
            synchronized (HttpRequest.class) {
                if (tgApiService == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.tngou.net/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    tgApiService = retrofit.create(TngouApiService.class);
                }
            }
        }
        return tgApiService;
    }

    public static JinyiFanyiService getJyfyService() {
        if (jyfyService == null) {
            synchronized (HttpRequest.class) {
                if (jyfyService == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://apis.baidu.com/netpopo/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    jyfyService = retrofit.create(JinyiFanyiService.class);
                }
            }
        }
        return jyfyService;
    }

    public static <T> void enqueue(Call<HttpModel<T>> call, final AbsRequestCallback callback) {
        call.enqueue(new Callback<HttpModel<T>>() {
            @Override
            public void onResponse(Call<HttpModel<T>> call, Response<HttpModel<T>> response) {
                if (response.isSuccessful()) {
                    if (callback != null) {
                        // 天狗API返回数据的处理
//                        HttpModel<T> result = response.body();
//                        if (result.status) {
//                            callback.onSuccess(result.tngou);
//                        } else {
//                            callback.onFailure(-1, "data is not valid.");
//                        }
                        // 近义词反义词API返回数据的处理
                        HttpModel<T> result = response.body();
                        if (result.status == ResponseCodeConstant.RESPONSE_SUCCESS) {
                            callback.onSuccess(result.result);
                        } else {
                            callback.onFailure(result.status, result.msg);
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

    private HttpRequest() {
    }
}
