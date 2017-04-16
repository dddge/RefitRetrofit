package io.github.lobt.refitretrofit.service.http;

import io.github.lobt.refitretrofit.service.http.api.GithubService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Lobt
 * @version 0.1
 */

public class HttpRequest {

    private static final String BASE_URL = "https://api.github.com/";

    private static volatile  GithubService githubService;

    private static  Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static GithubService getGithubApi() {
        if(githubService == null) {
            synchronized (HttpRequest.class) {
                githubService = retrofit.create(GithubService.class);
            }
        }
        return githubService;
    }

    private HttpRequest(){}
}
