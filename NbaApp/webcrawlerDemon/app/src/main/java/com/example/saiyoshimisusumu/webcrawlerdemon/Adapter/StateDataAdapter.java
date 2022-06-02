package com.example.saiyoshimisusumu.webcrawlerdemon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.saiyoshimisusumu.webcrawlerdemon.CardData;
import com.example.saiyoshimisusumu.webcrawlerdemon.StateData;
import com.example.saiyoshimisusumu.webcrawlerdemon.R;
import com.example.saiyoshimisusumu.webcrawlerdemon.StateData;
import com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder.CardDataViewHolder;
import com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder.StateDataViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StateDataAdapter extends RecyclerView.Adapter<StateDataViewHolder> {
    private List<StateData> Statelists = new ArrayList<StateData>();
    Context context;
    public StateDataAdapter(List<StateData> Statelist , Context context)
    {
        this.Statelists = Statelist;
        this.context = context;
    }
    @NonNull
    @Override
    public StateDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stateview , parent ,false);
        return new StateDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StateDataViewHolder holder, int position) {
        holder.txtranking.setText(Statelists.get(position).ranking);
        holder.txtteam.setText(Statelists.get(position).team);
        holder.txtwin.setText(Statelists.get(position).win);
        holder.txtlose.setText(Statelists.get(position).lose);
        holder.txtGB.setText(Statelists.get(position).GB);
        holder.txtwin_percentage.setText(Statelists.get(position).win_percentage);
        holder.txthome.setText(Statelists.get(position).home);
        holder.txtaway.setText(Statelists.get(position).away);
        Glide.with(context).load(Statelists.get(position).ImgUrl).into(holder.getImage());
        //Picasso.get().load(Statelists.get(position).ImgUrl).into(holder.imgContext);
    }

    @Override
    public int getItemCount() {
        return Statelists.size();
    }
}
