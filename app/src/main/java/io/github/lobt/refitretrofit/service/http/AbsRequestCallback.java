package io.github.lobt.refitretrofit.service.http;

import android.util.Log;

/**
 * @author Lobt
 * @version 0.1
 */

public abstract class AbsRequestCallback<T> implements IRequestCallback<T> {
    private final String TAG = "AbsRequestCallback";

    @Override
    public void onFailure(int code, String message) {
        if (code == ResponseCodeConstant.ERROR_TOKEN_EXPIRED) {
            Log.d(TAG, "token is expired.");
            //goto login activity
            return;
        }
    }
}
