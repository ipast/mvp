package com.ipast.mvp;

import android.app.Application;

import com.ipast.utils.ecrash.CrashHandler;

/**
 * @author gang.cheng
 * @description :
 * @date :2021/7/21
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this,true);
    }
}
