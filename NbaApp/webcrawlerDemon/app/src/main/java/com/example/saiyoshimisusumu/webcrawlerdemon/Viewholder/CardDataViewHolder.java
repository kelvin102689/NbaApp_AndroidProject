package com.example.saiyoshimisusumu.webcrawlerdemon.Viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saiyoshimisusumu.webcrawlerdemon.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardDataViewHolder extends RecyclerView.ViewHolder {

    public TextView txtName, txtTeam, txtGP, txtPPG, txtFGP, txtThreeFGM, txtThreeFGA, txtThreeFGP, txtFTP;
    public ImageView imgContext;

    public CardDataViewHolder(@NonNull View itemView) {
        super(itemView);
        txtFTP = (TextView)itemView.findViewById(R.id.FTP);
        txtThreeFGP = (TextView)itemView.findViewById(R.id.ThreeFGP);
        txtThreeFGA = (TextView)itemView.findViewById(R.id.ThreeFGA);
        txtThreeFGM = (TextView)itemView.findViewById(R.id.ThreeFGM);
        txtFGP = (TextView)itemView.findViewById(R.id.FGP);
        txtPPG = (TextView)itemView.findViewById(R.id.PPG);
        txtGP = (TextView)itemView.findViewById(R.id.GP);
        txtTeam = (TextView)itemView.findViewById(R.id.Team);
        txtName = (TextView)itemView.findViewById(R.id.Name);
        imgContext =(ImageView)itemView.findViewById(R.id.imgContext);


    }
    public ImageView getImage(){ return imgContext;}
}
