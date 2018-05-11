package com.example.retrofitx.utils;

import android.os.Environment;

import com.example.retrofitx.api.Api;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/11.
 */

public class Utils {

    private static Utils instance;
    private final Retrofit retrofit;

    public Utils(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();

        File file = new File(Environment.getExternalStorageDirectory(),"cache11111");

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.readTimeout(3000, TimeUnit.SECONDS);
        okHttpClient.connectTimeout(3000,TimeUnit.SECONDS);
        okHttpClient.cache(new Cache(file,10*1024));
        okHttpClient.addInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY));
        okHttpClient.addNetworkInterceptor(httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY));

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.zhaoapi.cn/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public static Utils getInstance(){
        if(instance==null){
            synchronized (Utils.class){
                if(null==instance){
                    instance = new Utils();
                }
            }
        }
        return instance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }
}
