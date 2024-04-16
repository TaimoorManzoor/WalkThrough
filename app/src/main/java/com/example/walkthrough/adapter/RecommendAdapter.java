package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;

public class RecommendAdapter  extends RecyclerView.Adapter<RecommendAdapter.MyViewHolder>  {
    String [] name_recommend;
    String [] price_recommend;
    int [] image_recommend;
    public RecommendAdapter(String [] name_recommend,int [] image_recommend,String [] price_recommend)
    {
        this.image_recommend=image_recommend;
        this.name_recommend=name_recommend;
        this.price_recommend=price_recommend;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tailor_list_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.image_recommend.setImageResource(image_recommend[position]);
        holder.name_recommend.setText(name_recommend[position]);
        holder.price_recommend.setText(price_recommend[position]);
    }


    @Override
    public int getItemCount() {
        return image_recommend.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_recommend;
        private TextView name_recommend;
        private TextView price_recommend;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image_recommend=itemView.findViewById(R.id.img);
            name_recommend=itemView.findViewById(R.id.name);
            price_recommend=itemView.findViewById(R.id.price);
        }
    }
}


