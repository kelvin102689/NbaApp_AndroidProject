

        package com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saiyoshimisusumu.webcrawlerdemon.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StateDataViewHolder extends RecyclerView.ViewHolder {

    public TextView txtranking, txtteam, txtwin, txtlose, txtGB, txtwin_percentage, txthome, txtaway;
    public ImageView imgContext;

    public StateDataViewHolder(@NonNull View itemView) {
        super(itemView);
        txtranking = (TextView)itemView.findViewById(R.id.ranking);
        txtteam = (TextView)itemView.findViewById(R.id.team);
        txtwin = (TextView)itemView.findViewById(R.id.win);
        txtlose = (TextView)itemView.findViewById(R.id.lose);
        txtGB = (TextView)itemView.findViewById(R.id.GB);
        txtwin_percentage = (TextView)itemView.findViewById(R.id.win_percentage);
        txthome = (TextView)itemView.findViewById(R.id.home);
        txtaway = (TextView)itemView.findViewById(R.id.away);
        imgContext =(ImageView)itemView.findViewById(R.id.imgContext);


    }
    public ImageView getImage(){ return imgContext;}
}
