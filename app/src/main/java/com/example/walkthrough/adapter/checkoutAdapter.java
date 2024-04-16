package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;

public class checkoutAdapter extends RecyclerView.Adapter<checkoutAdapter.MyViewHolder> {
    String [] price_checkout;
    String [] name_checkout;
    int [] image_checkout;
    public checkoutAdapter(int [] image_checkout,String [] name_checkout,String [] price_checkout)
    {
        this.image_checkout=image_checkout;
        this.name_checkout=name_checkout;
        this.price_checkout=price_checkout;
    }

    @NonNull
    @Override
    public checkoutAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.product_checkout_recycleview,parent,false);
        return new checkoutAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull checkoutAdapter.MyViewHolder holder, int position) {
        holder.name_checkout.setText(name_checkout[position]);
        holder.image_checkout.setImageResource(image_checkout[position]);
        holder.price_checkout.setText(price_checkout[position]);
    }


    @Override
    public int getItemCount() {
        return price_checkout.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_checkout;
        private TextView price_checkout;
        private TextView name_checkout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image_checkout=itemView.findViewById(R.id.imageView6);
            price_checkout=itemView.findViewById(R.id.price);
            name_checkout=itemView.findViewById(R.id.clothes_name);
        }
}
}
