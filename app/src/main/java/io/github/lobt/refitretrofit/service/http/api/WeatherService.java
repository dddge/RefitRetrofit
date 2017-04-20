package io.github.lobt.refitretrofit.service.http.api;

import java.util.List;

import io.github.lobt.refitretrofit.service.http.converter.QueryKey;
import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import io.github.lobt.refitretrofit.service.http.model.WeatherModel;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author Lobt
 * @version 0.1
 */

public interface WeatherService {

    @QueryKey("forecast")
    @GET("weather")
    Call<HttpModel<List<WeatherModel>>> todayWeather();
}
