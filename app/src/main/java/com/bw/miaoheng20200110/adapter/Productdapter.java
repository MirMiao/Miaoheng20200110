package com.bw.miaoheng20200110.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.miaoheng20200110.R;
import com.bw.miaoheng20200110.entity.CartEntity;

import java.util.List;

/**
 * 时间 :2020/1/10  9:14
 * 作者 :苗恒
 * 功能 :
 */
public class Productdapter extends RecyclerView.Adapter<Productdapter.MyViewHolder> {
    public Context context;
    public List<CartEntity.ResultBean.ShoppingCartListBean> list;

    public Productdapter(Context context, List<CartEntity.ResultBean.ShoppingCartListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(View.inflate(context, R.layout.item_2, null));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getCommodityName());
        Glide.with(context).load(list.get(position).getPic()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;

        private final ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv);
            imageView = itemView.findViewById(R.id.iv);
        }
    }
}
