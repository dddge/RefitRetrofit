package io.github.lobt.refitretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.github.lobt.refitretrofit.service.http.AbsRequestCallback;
import io.github.lobt.refitretrofit.service.http.HttpRequest;
import io.github.lobt.refitretrofit.service.http.model.Contributor;
import io.github.lobt.refitretrofit.service.http.model.HotNews;
import io.github.lobt.refitretrofit.service.http.model.WeatherModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void fetchData(View view) {
        switch (view.getId()) {
            case R.id.btn_contributors:
                printContributors();
                break;
            case R.id.btn_hotnews:
                printHotNews();
                break;
            case R.id.btn_today_weather:
                printTodayWeather();
                break;
        }
    }

    private void printContributors() {

        Call<List<Contributor>> call = HttpRequest.getGithubApi().contributors("square", "retrofit");

        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response == null) {
                    Log.d(TAG, "response is null");
                    return;
                }

                List<Contributor> contributors = response.body();
                for (Contributor contributor : contributors) {
                    Log.d(TAG, contributor.login + " (" + contributor.contributions + ")");
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Log.d(TAG, Log.getStackTraceString(t));
            }
        });
    }

    private void printHotNews() {
        HttpRequest.enqueue(HttpRequest.getTngouApi().hotNews("10"), new AbsRequestCallback<List<HotNews>>() {
            @Override
            public void onSuccess(List<HotNews> data) {
                if (data == null || data.size() == 0) return;
                for (HotNews news : data) {
                    Log.d(TAG, news.title + " (" + news.fromname + ")");
                }
            }
        });
    }

    private void printTodayWeather() {
        HttpRequest.enqueue(HttpRequest.getWeatherApi().todayWeather(), new AbsRequestCallback<List<WeatherModel>>() {
            @Override
            public void onSuccess(List<WeatherModel> data) {
                if (data == null || data.size() == 0) return;

                for (WeatherModel w : data) {
                    StringBuilder strBuilder = new StringBuilder("当日天气情况如下： \n");
                    strBuilder.append("当日温度（ ").append(w.minTmp).append(", ").append(w.maxTmp).append(" )\n");
                    strBuilder.append("当日天气： 白天").append(w.dayInfo).append(", 夜晚").append(w.nightInfo);
                    Log.d(TAG, strBuilder.toString());
                }
            }
        });
    }
}
