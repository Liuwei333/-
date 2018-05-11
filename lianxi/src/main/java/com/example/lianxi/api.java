package com.example.lianxi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/5/9.
 */

public interface api {
    @GET("")
    Call<Bean>doGet(@Query("key")String key,@Query("num")String num,@Query("page")int pager);
}
