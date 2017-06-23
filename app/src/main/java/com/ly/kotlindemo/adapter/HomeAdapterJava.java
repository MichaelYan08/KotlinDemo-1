package com.ly.kotlindemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ly.kotlindemo.R;

/**
 * Created by Shinelon on 2017/6/23.
 */

public class HomeAdapterJava extends RecyclerView.Adapter<HomeAdapterJava.HomeHolder> {

    private Context context;

    public HomeAdapterJava(Context context) {
        this.context = context;
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeHolder(LayoutInflater.from(context).inflate(R.layout.adapter_home, parent, false));
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HomeHolder extends RecyclerView.ViewHolder {
        ImageView ivType;
        TextView tvType;
        ImageView ivAuthor;
        TextView tvAuthor;
        TextView tvTime;
        RelativeLayout rlMessage;
        ImageView ivPart;
        ImageView ivVedio;
        TextView tvItem;

        public HomeHolder(View itemView) {
            super(itemView);
            ivType = itemView.findViewById(R.id.iv_type);
            tvType = itemView.findViewById(R.id.tv_type);
            ivAuthor = itemView.findViewById(R.id.iv_author);
            tvAuthor = itemView.findViewById(R.id.tv_author);
            tvTime = itemView.findViewById(R.id.tv_time);
            rlMessage = itemView.findViewById(R.id.rl_message);
            ivPart = itemView.findViewById(R.id.iv_part);
            ivVedio = itemView.findViewById(R.id.iv_vedio);
            tvItem = itemView.findViewById(R.id.tv_item);
        }
    }
}
