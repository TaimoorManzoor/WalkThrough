package com.example.walkthrough.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.CheckoutFragment;

public class PortfolioAdapter  extends RecyclerView.Adapter<PortfolioAdapter.MyViewHolder> {
    int [] image;

    public PortfolioAdapter(int [] image)
    {
        this.image=image;
    }
    @NonNull
    @Override
    public PortfolioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.portfolio_recycleview,parent,false);
        return new PortfolioAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PortfolioAdapter.MyViewHolder holder, int position) {
        holder.imageView.setImageResource(image[position]);
    }


    @Override
    public int getItemCount() {
        return image.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView=itemView.findViewById(R.id.imageView4);
        }
    }
}
