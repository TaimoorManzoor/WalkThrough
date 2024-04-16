package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.model.PendingOrderRecycleviewModel;
import com.example.walkthrough.model.RequestOrderRecycleModel;

import java.util.ArrayList;

public class PendingOrderAdapterRecycleview  extends RecyclerView.Adapter<PendingOrderAdapterRecycleview.MyViewHolder>
{
    ArrayList<PendingOrderRecycleviewModel> pendingOrderRecyclelist;

    public PendingOrderAdapterRecycleview(ArrayList<PendingOrderRecycleviewModel> pendingOrderRecyclelist) {
        this.pendingOrderRecyclelist = pendingOrderRecyclelist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tailor_pendingorder_received_recycleview,parent,false);
        return new PendingOrderAdapterRecycleview.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.suite_category.setText(pendingOrderRecyclelist.get(position).getSuitecategory().toString().trim());
        holder.name.setText(pendingOrderRecyclelist.get(position).getName().toString().trim());
        holder.image.setImageResource(pendingOrderRecyclelist.get(position).getImage());
        holder.date.setText(pendingOrderRecyclelist.get(position).getDate());
        holder.price.setText(pendingOrderRecyclelist.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        if (pendingOrderRecyclelist != null) {
            return pendingOrderRecyclelist.size();
        } else {
            return 0; // or handle this case appropriately
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView name;
        private TextView suite_category;
        private TextView price;
        private TextView date;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            suite_category=itemView.findViewById(R.id.suite_category);
            price=itemView.findViewById(R.id.price);
            date=itemView.findViewById(R.id.date);


        }
    }
}
