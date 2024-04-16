package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.PortfolioFragment;
import com.example.walkthrough.model.AddProductModel;
import com.example.walkthrough.model.TailorCategoryModel;

import java.util.ArrayList;

public class AddProduuctAdapterRecycleview extends RecyclerView.Adapter<AddProduuctAdapterRecycleview.MyViewHolder>
{
    public ArrayList<AddProductModel> addProductModels;
    public  AddProduuctAdapterRecycleview(ArrayList<AddProductModel> addProductModels)
    {
        this.addProductModels=addProductModels;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.productimage_recycleview,parent,false);
        return new AddProduuctAdapterRecycleview.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.image.setImageResource(addProductModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return addProductModels.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.image);
        }
    }

}
