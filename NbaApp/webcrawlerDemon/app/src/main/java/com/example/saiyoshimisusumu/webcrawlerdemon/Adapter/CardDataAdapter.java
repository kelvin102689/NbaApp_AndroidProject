package com.example.saiyoshimisusumu.webcrawlerdemon.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.saiyoshimisusumu.webcrawlerdemon.CardData;
import com.example.saiyoshimisusumu.webcrawlerdemon.R;
import com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder.CardDataViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardDataAdapter extends RecyclerView.Adapter<CardDataViewHolder> {

    private List<CardData> CardLists = new ArrayList<CardData>();
    Context context;
    public CardDataAdapter(List<CardData> CardList , Context context)
    {
        this.CardLists = CardList;
        this.context = context;
    }
    @NonNull
    @Override
    public CardDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview , parent ,false);
        return new CardDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDataViewHolder holder, int position) {
        holder.txtFTP.setText(CardLists.get(position).FTP);
        holder.txtThreeFGP.setText(CardLists.get(position).ThreeFGP);
        holder.txtThreeFGA.setText(CardLists.get(position).ThreeFGA);
        holder.txtThreeFGM.setText(CardLists.get(position).ThreeFGM);
        holder.txtFGP.setText(CardLists.get(position).FGP);
        holder.txtPPG.setText(CardLists.get(position).PPG);
        holder.txtGP.setText(CardLists.get(position).GP);
        holder.txtTeam.setText(CardLists.get(position).Team);
        holder.txtName.setText(CardLists.get(position).Name);
        Glide.with(context).load(CardLists.get(position).ImgUrl).into(holder.getImage());
       // Picasso.get().load(CardLists.get(position).ImgUrl).into(holder.imgContext);
    }

    @Override
    public int getItemCount() {
        return CardLists.size();
    }
}
