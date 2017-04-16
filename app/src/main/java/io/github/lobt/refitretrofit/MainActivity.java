package io.github.lobt.refitretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;

import io.github.lobt.refitretrofit.service.http.HttpRequest;
import io.github.lobt.refitretrofit.service.http.model.Contributor;
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
}