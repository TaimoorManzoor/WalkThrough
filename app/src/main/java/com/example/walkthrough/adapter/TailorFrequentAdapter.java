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

import java.util.ArrayList;

public class TailorFrequentAdapter extends RecyclerView.Adapter<TailorFrequentAdapter.MyViewHolder>{
    ArrayList<FrequentCustomerModel> custmerlist;
    public TailorFrequentAdapter(ArrayList<FrequentCustomerModel> custmerlist) {
        this.custmerlist = custmerlist;
    }

    @NonNull
    @Override
    public TailorFrequentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tailor_home_frequent_order,parent,false);
        return new TailorFrequentAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorFrequentAdapter.MyViewHolder holder, int position) {
        final int temp = position;

        holder.address.setText(custmerlist.get(position).getAddress().toString().trim());
        holder.name.setText(custmerlist.get(position).getName().toString().trim());
        holder.image.setImageResource(custmerlist.get(position).getImage());

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
        return custmerlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView name;
        private TextView address;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);
        }
    }
}
