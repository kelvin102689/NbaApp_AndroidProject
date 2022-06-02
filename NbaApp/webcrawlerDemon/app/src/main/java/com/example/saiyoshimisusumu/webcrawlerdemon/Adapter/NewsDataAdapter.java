package com.example.saiyoshimisusumu.webcrawlerdemon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saiyoshimisusumu.webcrawlerdemon.NewsData;
import com.example.saiyoshimisusumu.webcrawlerdemon.R;
import com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder.NewsDataViewHolder;

import java.util.List;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataViewHolder> {

    private List<NewsData> newsDataList;
    Context context;

    public NewsDataAdapter(List<NewsData> newsDataList, Context context) {
        this.newsDataList = newsDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsview, parent, false);
        return new NewsDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDataViewHolder holder, int position) {
        final NewsData newsData = newsDataList.get(position);
        holder.txtNewsTitle.setText(newsData.NewsTitle);
        holder.txtcontent.setText(newsData.content);
        Glide.with(context).load(newsData.ImgUrl).into(holder.getImage());

        holder.setItemViewClickEvent(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                System.out.println("url = " + newsData.linkUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsData.linkUrl));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }
}
