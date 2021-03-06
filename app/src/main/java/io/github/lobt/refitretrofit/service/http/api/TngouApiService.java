package io.github.lobt.refitretrofit.service.http.api;

import java.util.List;

import io.github.lobt.refitretrofit.service.http.model.HotNews;
import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Lobt
 * @version 0.1
 */

public interface TngouApiService {

    @GET("top/list")
    Call<HttpModel<List<HotNews>>> hotNews(@Query("rows")String rows);
}
