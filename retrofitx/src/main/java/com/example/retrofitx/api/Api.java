package com.example.retrofitx.api;

import com.example.retrofitx.bean.Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface Api {
    @GET("ad/getAd")
    Call<Bean>doGet(@Query("num")int num);
}
