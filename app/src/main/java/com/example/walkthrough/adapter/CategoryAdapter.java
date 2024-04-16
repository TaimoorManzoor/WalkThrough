package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder>
{
    String [] name_category;
    String [] gender_category;
    String [] status_category;
    int [] image_category;
    public CategoryAdapter(String [] name_category,int [] image_category,String [] gender_category,String [] status_category)
    {
        this.name_category=name_category;
        this.image_category=image_category;
        this.gender_category=gender_category;
        this.status_category=status_category;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.category_list_layout,parent,false);
        return new CategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        holder.status_category.setText(status_category[position]);
        holder.gender_category.setText(gender_category[position]);
        holder.image_category.setImageResource(image_category[position]);
        holder.name_category.setText(name_category[position]);
    }


    @Override
    public int getItemCount() {
        return image_category.length;
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
