package com.example.lianxi;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2018/5/9.
 */

public class Utils {
    private static Utils instance;
    private final Retrofit retrofit;

    OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
    public Utils(){


        okHttpClient.connectTimeout(3000, TimeUnit.SECONDS);
        okHttpClient.readTimeout(3000,TimeUnit.SECONDS);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.tianapi.com/social/")
                .client(okHttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static  Utils getInstance(){
        if(instance==null){
            synchronized (Utils.class){
                if(null==instance){
                    instance = new Utils();
                }
            }
        }
        return instance;
    }
    public api getApi(){
return  retrofit.create(api.class);
    }
}
