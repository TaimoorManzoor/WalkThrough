package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.model.ClothesProfilePorfolioModel;
import com.example.walkthrough.model.TailorProfilePorfolioModel;

import java.util.ArrayList;

public class ClothesProfilePortfolioAdapter extends RecyclerView.Adapter<ClothesProfilePortfolioAdapter.MyViewHolder> {

    ArrayList<ClothesProfilePorfolioModel> porfoliolist;
    public ClothesProfilePortfolioAdapter(ArrayList<ClothesProfilePorfolioModel> porfoliolist) {
        this.porfoliolist = porfoliolist;
    }

    public ClothesProfilePortfolioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.clothes_profile_recycleview,parent,false);
        return new ClothesProfilePortfolioAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ClothesProfilePortfolioAdapter.MyViewHolder holder, int position) {
        holder.image.setImageResource(porfoliolist.get(position).getImage());
    }


    @Override
    public int getItemCount() {
        return porfoliolist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.porfolioimg);

        }
    }
}
