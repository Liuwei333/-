package com.example.rikao511;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.refresh_status_textview)
    XRecyclerView rcy;
    int indexType=1;
    int onType=1;
    String uri="http://api.tianapi.com/guonei/?key=a91da7f58d9f0bd1e9d9f0c5778b9a5a&num=30&page="+indexType;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String json = (String) msg.obj;

            Gson gson = new Gson();
            Bean bean = gson.fromJson(json, Bean.class);

            if(onType==1){
                newslist.clear();
            }

            newslist.addAll(bean.getNewslist());

            if(myAdapter==null){

                Log.d("aa", "handleMessage: "+newslist.get(1).getPicUrl());
                myAdapter = new MyAdapter(MainActivity.this,newslist);
                rcy.setAdapter(myAdapter);
            }else{
                myAdapter.notifyDataSetChanged();
            }

            rcy.refreshComplete();
        }
    };

    private List<Bean.NewslistBean> newslist  = new ArrayList<>();
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ok();

        rcy.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        rcy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                onType=1;
                indexType=1;
                uri="http://api.tianapi.com/guonei/?key=a91da7f58d9f0bd1e9d9f0c5778b9a5a&num=30&page="+indexType;
                ok();

            }

            @Override
            public void onLoadMore() {
                onType=2;
                indexType++;
                uri="http://api.tianapi.com/guonei/?key=a91da7f58d9f0bd1e9d9f0c5778b9a5a&num=30&page="+indexType;
                ok();
            }
        });
    }

    private void ok() {
        OkHtttpUtils.doGet(uri, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String string = response.body().string();

                Message msg = Message.obtain();
                msg.obj=string;

                handler.sendMessage(msg);
            }
        });

    }
}
