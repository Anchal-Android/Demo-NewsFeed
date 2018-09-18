package com.example.anchalsinghal.newsfeed.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.anchalsinghal.newsfeed.R;
import com.example.anchalsinghal.newsfeed.Room.entity.UserEntity;
import com.example.anchalsinghal.newsfeed.Utilities.LoadImage;
import com.example.anchalsinghal.newsfeed.listener.NewsFeedListener;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<UserEntity> values;
    private Context context;

    private NewsFeedListener listener;


    public MyAdapter(List<UserEntity> values, Context context,NewsFeedListener listener) {
        this.values = values;
        this.context = context;
        this.listener =listener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_details,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.ViewHolder holder, int position) {


            holder.tv_title.setText(values.get(holder.getAdapterPosition()).getTitle());
            holder.tv_feed_description.setText(values.get(holder.getAdapterPosition()).getDescription());
        LoadImage.loadImageWithPicasso(context,values.get(holder.getAdapterPosition()).getImageUrl(),null,holder.iv_feed_image);

        holder.layout.setTag(position);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

listener.onNewsFeedClick(values.get(holder.getAdapterPosition()).getTitle(),values.get(holder.getAdapterPosition()).getDescription(),values.get(holder.getAdapterPosition()).getImageUrl());
            }
        });

    }

    @Override
    public int getItemCount() {
        return values.size();
    }

    public class ViewHolder  extends  RecyclerView.ViewHolder{

        public ImageView iv_feed_image;
        public TextView tv_title;
        public TextView tv_feed_description;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            iv_feed_image = v.findViewById(R.id.iv_feed_image);
            tv_title = v.findViewById(R.id.tv_title);

            tv_feed_description = v.findViewById(R.id.tv_feed_description);


        }
    }
}
