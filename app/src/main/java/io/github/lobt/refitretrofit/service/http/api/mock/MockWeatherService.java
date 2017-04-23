package io.github.lobt.refitretrofit.service.http.api.mock;

import java.util.ArrayList;
import java.util.List;

import io.github.lobt.refitretrofit.service.http.api.WeatherService;
import io.github.lobt.refitretrofit.service.http.model.HttpModel;
import io.github.lobt.refitretrofit.service.http.model.WeatherModel;
import retrofit2.Call;

/**
 * @author Lobt
 * @version 0.1
 */

public class MockWeatherService extends MockService<WeatherService> implements WeatherService {
    @Override
    public Call<HttpModel<List<WeatherModel>>> todayWeather() {
       HttpModel<List<WeatherModel>> result = new HttpModel<>();
        result.code = 0;
        result.message = "OK";

        List<WeatherModel> list = new ArrayList<>();
        WeatherModel weather = new WeatherModel();
        weather.dayInfo = "day";
        weather.nightInfo = "night";
        weather.maxTmp = "20";
        weather.minTmp = "10";
        list.add(weather);

        weather = new WeatherModel();
        weather.dayInfo = "day2";
        weather.nightInfo = "night2";
        weather.maxTmp = "120";
        weather.minTmp = "110";
        list.add(weather);

        weather = new WeatherModel();
        weather.dayInfo = "day4";
        weather.nightInfo = "night4";
        weather.maxTmp = "204";
        weather.minTmp = "104";
        list.add(weather);

        result.data = list;

        return delegate().returningResponse(result).todayWeather();
    }
}
