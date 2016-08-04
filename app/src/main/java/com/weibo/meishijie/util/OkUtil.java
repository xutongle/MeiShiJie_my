package com.weibo.meishijie.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 美貌与智慧并重的男子 on 2016/7/30.
 */

public class OkUtil {

    public static OkHttpClient getOkhttpClient(){
        return OkHttpClientHelper.ok;
    }

    private static class OkHttpClientHelper{
        private static final OkHttpClient ok = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new CacheInterceptor())
                //.cache(new okhttp3.Cache(CacheUtil.getDiskCacheDir(MainApp.getAppContext()),100 * 1024 * 1024))
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1,TimeUnit.MINUTES)
                .connectTimeout(1,TimeUnit.MINUTES)
                .build();
    }

    private static class CacheInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Response response1 = response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    //缓存七天
                    .header("Cache-Control", "max-age=" + 3600 * 24 * 7)
                    .build();
            return response1;
        }
    }

    private static class RetrofitHelper{
        private static final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(Constant.DATA_URL)
                .client(getOkhttpClient())
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
