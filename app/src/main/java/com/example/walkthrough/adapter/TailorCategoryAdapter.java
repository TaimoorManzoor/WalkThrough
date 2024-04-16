package com.example.walkthrough.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.CheckoutFragment;
import com.example.walkthrough.activites.EyeFragment;
import com.example.walkthrough.activites.PortfolioFragment;
import com.example.walkthrough.activites.TailorFragment;
import com.example.walkthrough.model.TailorCategoryModel;

import java.util.ArrayList;

public class TailorCategoryAdapter extends RecyclerView.Adapter<TailorCategoryAdapter.MyViewHolder> {
   ArrayList<TailorCategoryModel> tailorlist;

    public TailorCategoryAdapter(ArrayList<TailorCategoryModel> tailorlist) {
        this.tailorlist = tailorlist;
    }


    @NonNull
    @Override
    public TailorCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.category_list_layout,parent,false);
        return new TailorCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorCategoryAdapter.MyViewHolder holder, int position) {
        final int temp = position;

        holder.status_category.setText(tailorlist.get(position).getStatus().toString().trim());
        holder.gender_category.setText(tailorlist.get(position).getGender().toString().trim());
        holder.image_category.setImageResource(tailorlist.get(position).getImage());
        holder.name_category.setText(tailorlist.get(position).getName().toString().trim());

        holder.image_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.userhome, new PortfolioFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });



    }


    @Override
    public int getItemCount() {
        return tailorlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image_category;
        private TextView name_category;
        private TextView gender_category;
        private TextView status_category;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image_category=itemView.findViewById(R.id.image_category);
            name_category=itemView.findViewById(R.id.name_category);
            gender_category=itemView.findViewById(R.id.gender_category);
            status_category=itemView.findViewById(R.id.status_category);
        }
    }

}
