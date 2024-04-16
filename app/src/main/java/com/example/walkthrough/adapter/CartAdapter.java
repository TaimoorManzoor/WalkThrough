package com.example.walkthrough.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder>{
    String [] cart_size;
    String [] name_cloth;
    int [] image_cloth;
    public CartAdapter(String [] cart_size,int [] image_cloth,String [] name_cloth)
    {
        this.cart_size=cart_size;
        this.image_cloth=image_cloth;
        this.name_cloth=name_cloth;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.cart_recycle_layout,parent,false);
        return new CartAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        holder.cart_size.setText(cart_size[position]);
        holder.image_cloth.setImageResource(image_cloth[position]);
        holder.name_cloth.setText(name_cloth[position]);
    }


    @Override
    public int getItemCount() {
        return name_cloth.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_cloth;
        private TextView cart_size;
        private TextView name_cloth;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image_cloth=itemView.findViewById(R.id.image_cloth);
            cart_size=itemView.findViewById(R.id.cart_size);
            name_cloth=itemView.findViewById(R.id.name_cloth);
        }
    }
}
