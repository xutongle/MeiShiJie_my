package com.weibo.meishijie.util;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class OkUtil {

    private static class RetrofitHelper{
        private static final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.DATA_URL)
                .build();
    }

    public static Retrofit createRetrofit(){
        return RetrofitHelper.retrofit;
    }

    public static Map<String,String> getTuijianMap(){
        Map<String,String> map = new HashMap<>();
        map.put("lon","");
        map.put("lat","");
        map.put("format","json");
        map.put("source","android");
        return map;
    }
}
