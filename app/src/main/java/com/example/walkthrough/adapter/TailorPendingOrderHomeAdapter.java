package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.model.FrequentCustomerModel;
import com.example.walkthrough.model.PendingOrderModel;

import java.util.ArrayList;

public class TailorPendingOrderHomeAdapter  extends RecyclerView.Adapter<TailorPendingOrderHomeAdapter.MyViewHolder> {
    ArrayList<PendingOrderModel> pendingorder;
    public TailorPendingOrderHomeAdapter(ArrayList<PendingOrderModel> pendingorder) {
        this.pendingorder = pendingorder;
    }

    @NonNull
    @Override
    public TailorPendingOrderHomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tailorpendingorder,parent,false);
        return new TailorPendingOrderHomeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorPendingOrderHomeAdapter.MyViewHolder holder, int position) {
        final int temp = position;

        holder.detail.setText(pendingorder.get(position).getDetail().toString().trim());
        holder.name.setText(pendingorder.get(position).getName().toString().trim());
        holder.image.setImageResource(pendingorder.get(position).getImage());

    /*    holder.image_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.userhome, new PortfolioFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });*/
    }
    @Override
    public int getItemCount() {
        return pendingorder.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView name;
        private TextView detail;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            detail=itemView.findViewById(R.id.detail);
        }
    }
}
