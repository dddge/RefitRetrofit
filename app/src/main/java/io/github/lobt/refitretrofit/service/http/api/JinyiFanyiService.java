package io.github.lobt.refitretrofit.service.http.api;

import java.util.List;

import io.github.lobt.refitretrofit.service.http.converter.QueryKey;
import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author Lobt
 * @version 0.1
 */

public interface JinyiFanyiService {

    @Headers("apikey: 69c54795fd9d132d998fd5dd4a16ec04")
    @QueryKey("jin")
    @GET("jinyifanyi/word")
    Call<HttpModel<List<String>>> jyfyWord(@Query("word") String word);
}

