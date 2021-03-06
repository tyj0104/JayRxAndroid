package com.library.jay.jayrxandroid.modle;

import java.util.concurrent.atomic.AtomicBoolean;

import io.reactivex.disposables.Disposable;

/**
 * Disposable 抽象继承类
 * Created by jay on 2017/11/29.
 */
public abstract class AbstractDisposable implements Disposable {
    private final AtomicBoolean unsubscribed = new AtomicBoolean();

    public final boolean isDisposed() {
        return this.unsubscribed.get();
    }

    public final void dispose() {
        if (this.unsubscribed.compareAndSet(false, true)) {
            onDispose();
        }
    }

    protected abstract void onDispose();
}
