package com.example.rikao511;

import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018/5/11.
 */

public class OkHtttpUtils {
    private static OkHttpClient okHttpClient;

    private OkHtttpUtils(){
    }

    public static OkHttpClient getInstance(){
        if(okHttpClient==null){
            synchronized (OkHtttpUtils.class){
                okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(3000, TimeUnit.SECONDS)
                        .connectTimeout(3000,TimeUnit.SECONDS)
                        .build();
            }
        }
        return okHttpClient;
    }

    public static  void doGet(String uri, Callback callback){
        OkHttpClient instance = getInstance();

        Request request = new Request.Builder()
                .url(uri)
                .build();

        instance.newCall(request).enqueue(callback);
    }
}
