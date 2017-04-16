package io.github.lobt.refitretrofit.service.http;

/**
 * @author Lobt
 * @version 0.1
 */

public interface IRequestCallback<T> {

    void onSuccess(T data);
    void onFailure(int code, String message);
}
