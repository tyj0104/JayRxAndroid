package com.library.jay.jayrxandroid;


import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.library.jay.jayrxandroid.utils.ToastUtils;
import com.library.jay.jayrxandroid.view.ActivityLifecycleLogger;
import com.library.jay.jayrxandroid.view.ActivityManager;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;


/**
 * Created by jay on 2017/11/29.
 */
public class App extends Application {
    private static App instance;
    private RefWatcher refWatcher;
    private boolean multiDexEnabled;

    public App() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        refWatcher = LeakCanary.install(this);
        registerActivityLifecycleCallbacks();
        ToastUtils.init(this);
    }

    private void registerActivityLifecycleCallbacks() {
        //ActivityManage
        registerActivityLifecycleCallbacks(ActivityManager.getInstance());
        //日志
        registerActivityLifecycleCallbacks(new ActivityLifecycleLogger("App"));

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (this.multiDexEnabled) {
            MultiDex.install(this);
        }

    }

    @Override
    public void registerComponentCallbacks(ComponentCallbacks callback) {
        super.registerComponentCallbacks(callback);
    }

    public static App getInstance() {
        return instance;
    }

    protected void setMultiDexEnabled(boolean multiDexEnabled) {
        this.multiDexEnabled = multiDexEnabled;
    }
}
