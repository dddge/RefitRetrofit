package io.github.lobt.refitretrofit.service.http.api;

import java.util.List;

import io.github.lobt.refitretrofit.service.http.model.Contributor;
import retrofit2.Call;
import retrofit2.http.Path;

/**
 * @author Lobt
 * @version 0.1
 */

public class MockGithubService extends MockService<GithubService> implements GithubService {
    @Override
    public Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo) {
        return null;
    }
}
