package com.weibo.meishijie.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class MainApp extends Application {

    private static Context context;
    private static ExecutorService service;
    private static SharedPreferences sp;

    @Override
    public void onCreate() {
        super.onCreate();
        sp = this.getSharedPreferences("meishijie",MODE_PRIVATE);
        context = getApplicationContext();
        service = Executors.newCachedThreadPool();
    }

    public static SharedPreferences getSp(){
        return sp;
    }

    public static void closeSp(){
        if (sp != null){
            sp = null;
        }
    }

    public static ExecutorService getService(){
        return service;
    }

    public static void destroyPool(){
        if (service != null && !service.isShutdown()){
            service.shutdown();
            service = null;
        }
    }

    public static Context getAppContext(){
        return context;
    }
}
