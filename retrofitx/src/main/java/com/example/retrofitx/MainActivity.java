package com.example.retrofitx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.example.retrofitx.adapter.MyAdapter;
import com.example.retrofitx.bean.Bean;
import com.example.retrofitx.utils.Utils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rcy)
    XRecyclerView rcy;

    int retion=1;
    int onType=1;
    String uri="https://www.zhaoapi.cn/ad/getAd?num="+retion;
    List<Bean.MiaoshaBean.ListBeanX> list=new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ok();

         rcy.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));

         rcy.setLoadingListener(new XRecyclerView.LoadingListener() {
             @Override
             public void onRefresh() {
                 retion=1;
                 onType=1;
                 uri="https://www.zhaoapi.cn/ad/getAd?num="+retion;
                 ok();
             }

             @Override
             public void onLoadMore() {
                 retion++;
                 onType=2;
                 uri="https://www.zhaoapi.cn/ad/getAd?num="+retion;
                 ok();
             }
         });

    }

    private void ok() {

        Call<Bean> dataBeanCall = Utils.getInstance().getApi().doGet(retion);
        dataBeanCall.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {

                if(onType==1){
                    list.clear();
                }

                list.addAll(response.body().getMiaosha().getList());

                        if(myAdapter==null){
                            myAdapter = new MyAdapter(MainActivity.this,list);
                            rcy.setAdapter(myAdapter);
                        }

                        rcy.refreshComplete();

            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

            }
        });

    }


}
