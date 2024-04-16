package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.EyeFragment;
import com.example.walkthrough.activites.productclothesFragment;
import com.example.walkthrough.model.ClotheModel;

import java.util.ArrayList;

public class ClothesCategoryAdapter extends RecyclerView.Adapter<ClothesCategoryAdapter.MyViewHolder>  {

    ArrayList<ClotheModel> clothesList;

    public ClothesCategoryAdapter(ArrayList<ClotheModel> clothesList) {
        this.clothesList = clothesList;
    }

    @NonNull
    @Override
    public ClothesCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.category_list_layout,parent,false);
        return new ClothesCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesCategoryAdapter.MyViewHolder holder, int position) {
        holder.status_category.setText(clothesList.get(position).getStatus().toString().trim());
        holder.gender_category.setText(clothesList.get(position).getGender().toString().trim());
        holder.image_category.setImageResource(clothesList.get(position).getImage());
        holder.name_category.setText(clothesList.get(position).getName().toString().trim());

        holder.name_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.userhome, new productclothesFragment()).commit();
            }
        });
    }


    @Override
    public int getItemCount() {
        return clothesList.size();
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
