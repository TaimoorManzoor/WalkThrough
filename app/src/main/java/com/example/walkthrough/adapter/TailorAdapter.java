package com.example.walkthrough.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.example.walkthrough.model.TailorProfileDetailModelClass;

import java.util.ArrayList;

public class TailorAdapter  extends RecyclerView.Adapter<TailorAdapter.MyViewHolder>  {

    ArrayList<AddTailorDetailToRealtym> datalist;
    Context context;

    public TailorAdapter(ArrayList<AddTailorDetailToRealtym> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
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
        final AddTailorDetailToRealtym temp=datalist.get(position);
        Glide.with(context.getApplicationContext()).load(temp.getImageurl()).into(holder.image_tailor);
        holder.name_tailor.setText(temp.getUsername());
        holder.address_tailor.setText(temp.getTailoraddresses());
        holder.price_tailor.setText(temp.getTailorprice());
    }


    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_tailor;
        private TextView name_tailor;
        private TextView address_tailor;
        private TextView price_tailor;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image_tailor=itemView.findViewById(R.id.img);
            name_tailor=itemView.findViewById(R.id.name);
            address_tailor=itemView.findViewById(R.id.address);
            price_tailor=itemView.findViewById(R.id.price);
        }
    }
}


