package com.example.lianxi;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private XRecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private int refreshTime = 1;
    int operType=1;
 String uri="http://api.tianapi.com/social/?key=48a7d7193e11bd2dd4a683b6e2f90a4f&num=30&page="+refreshTime;

   private Handler handler = new Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);

           String string = (String) msg.obj;
           Gson gson = new Gson();
           Bean bean = gson.fromJson(string, Bean.class);


           if(operType==1){
               newslist.clear();
           }
           newslist.addAll(bean.getNewslist());

           if(mAdapter==null){
               mAdapter = new MyAdapter(MainActivity.this, newslist);

               mRecyclerView.setAdapter(mAdapter);
           }else{
               mAdapter.notifyDataSetChanged();
           }



           mRecyclerView.refreshComplete();

       }
   };
    private List<Bean.NewslistBean> newslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recyclerview);
        ok();


        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime=1;
                operType=1;
               uri="http://api.tianapi.com/social/?key=48a7d7193e11bd2dd4a683b6e2f90a4f&num=30&page="+refreshTime;
                ok();

            }

            @Override
            public void onLoadMore() {
                refreshTime++;
                operType=2;
                uri="http://api.tianapi.com/social/?key=48a7d7193e11bd2dd4a683b6e2f90a4f&num=30&page="+refreshTime;
                ok();
            }

        });



        }

    private void ok() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .build();
        Request request = new Request.Builder()
                .url(uri)
                .build();
        okHttpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {


                String string = response.body().string();

                Message msg = Message.obtain();
                msg.obj=string;

                handler.sendMessage(msg);


            }
        });
    }
}
