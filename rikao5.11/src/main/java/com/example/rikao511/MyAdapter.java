package com.example.rikao511;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Date;
import java.util.List;




/**
 * Created by Administrator on 2018/5/11.
 */

public class MyAdapter extends RecyclerView.Adapter {

    Context context;
    List<Bean.NewslistBean> list;


    String TAG="ss";

    public MyAdapter(Context context, List<Bean.NewslistBean> list) {
        this.list = list;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==0){
            View mView = View.inflate(context,R.layout.layout_item,null);
            MyHolder holder = new MyHolder(mView);
            return holder;

        }else if(viewType==1){
            View mView = View.inflate(context,R.layout.item_layout,null);
            MyHolder1 holder = new MyHolder1(mView);
            return holder;
        }

        return null;
    }
    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return 0;
        }else{
            return 1;
        }

    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (getItemViewType(position)){
            case 0:
                MyHolder holder1 = (MyHolder) holder;
                holder1.text.setText(list.get(position).getTitle());
                String picUrl = list.get(position).getPicUrl();
                String replace = picUrl.replace("\\", "");
                Log.d("aa", "onBindViewHolder: "+picUrl);
                holder1.sim.setImageURI(replace);
                break;
            case 1:
                MyHolder1 holder2 = (MyHolder1) holder;
                holder2.text.setText(list.get(position).getTitle());

                String picUrl1 = list.get(position).getPicUrl();

                holder2.sim.setImageURI(picUrl1);
                break;

        }

    }


    @Override
    public int getItemCount() {
        return list == null ? 0: list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        public SimpleDraweeView sim;
        private final TextView text;

        public MyHolder(View view) {
            super(view);
            sim = view.findViewById(R.id.sim);
            text = view.findViewById(R.id.text);
        }
    }
    class MyHolder1 extends RecyclerView.ViewHolder {

        public SimpleDraweeView sim;
        private final TextView text;

        public MyHolder1(View view) {
            super(view);
            sim = view.findViewById(R.id.sim);
            text = view.findViewById(R.id.text);
        }
    }

}
