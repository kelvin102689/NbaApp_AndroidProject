package com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder;

 import android.view.View;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.TextView;
 import android.widget.Toast;

 import com.example.saiyoshimisusumu.webcrawlerdemon.R;

 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.recyclerview.widget.RecyclerView;

 public class NewsDataViewHolder extends RecyclerView.ViewHolder {
     private View itemView;
     public TextView txtNewsTitle, txtcontent;
     public ImageView imgContext;

     public NewsDataViewHolder(@NonNull View itemView) {
         super(itemView);
         this.itemView = itemView;
         txtNewsTitle = (TextView)itemView.findViewById(R.id.NewsTitle);
         txtcontent = (TextView)itemView.findViewById(R.id.content);
         imgContext =(ImageView)itemView.findViewById(R.id.ImgUrl);
     }

     public ImageView getImage(){ return imgContext;}

     public void setItemViewClickEvent(@Nullable View.OnClickListener event) {
        this.itemView.setOnClickListener(event);
     }
 }