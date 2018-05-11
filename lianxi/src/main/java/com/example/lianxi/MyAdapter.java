package com.example.lianxi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adminjs on 2017/12/21.
 */

public class MyAdapter extends RecyclerView.Adapter{
    Context context;
    List<Bean.NewslistBean>list;


    public MyAdapter(Context context,List<Bean.NewslistBean> list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case 0:
                view = View.inflate(context,R.layout.item_layout,null);
                holder = new MyHolder(view);
                break;
            case 1:
                view = View.inflate(context,R.layout.layout__item,null);
                holder = new MyHolder1(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)){
            case 0:
                final MyHolder holder1 = (MyHolder) holder;
                holder1.si.setImageURI(list.get(position).getPicUrl());
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(5f);
                roundingParams.setRoundAsCircle(true);
                holder1.si.getHierarchy().setRoundingParams(roundingParams);
                holder1.title.setText(list.get(position).getTitle());
                break;
            case 1:
                final MyHolder1 myHolder1 = (MyHolder1) holder;
                myHolder1.name.setText(list.get(position).getTitle());
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        if(position%2 == 0){
            return 0;
        }else {
            return 1;
        }
    }
    @Override
    public int getItemCount() {
        return list== null ? 0 : list.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView si;
        private final TextView title;

        public MyHolder(View itemView) {
            super(itemView);
            si = itemView.findViewById(R.id.simpledra);
            title = itemView.findViewById(R.id.text);
        }
    }
    class MyHolder1 extends RecyclerView.ViewHolder {
        TextView name;

        public MyHolder1(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
        }
    }
}
