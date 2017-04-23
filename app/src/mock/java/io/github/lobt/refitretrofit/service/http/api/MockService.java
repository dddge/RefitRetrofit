package io.github.lobt.refitretrofit.service.http.api;

import io.github.lobt.refitretrofit.service.mock.BehaviorDelegate;

/**
 * @author Lobt
 * @version 0.1
 */

public class MockService<T> {

    private BehaviorDelegate<T> delegate;

    public void setDelegate(BehaviorDelegate<T> delegate) {
        this.delegate = delegate;
    }

    protected BehaviorDelegate<T> delegate() {
        return delegate;
    }
}
