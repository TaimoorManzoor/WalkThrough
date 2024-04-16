package com.example.walkthrough.activites.User.fragments.adapterClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;
import com.example.walkthrough.activites.User.fragments.modelClasses.ImageModelClass;

import java.util.ArrayList;
import java.util.List;

public class DesignAdapterImage extends RecyclerView.Adapter<DesignAdapterImage.TailorListViewHolder>{
    List<ImageModel> list;
    Context context;


    public DesignAdapterImage(List<ImageModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_profile_post_images,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        ImageModel imageUrl = list.get(position);

        Glide.with(context).load(imageUrl.getImageUrl()).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profilePostImage);
        }
    }
}
