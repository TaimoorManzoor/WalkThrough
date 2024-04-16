package com.example.walkthrough.activites.User.fragments.adapterClasses;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;

import java.util.List;

public class MeasurementAdapterImage extends RecyclerView.Adapter<MeasurementAdapterImage.TailorListViewHolder>{
    private List<Uri> imageList;
    Context context;


    public MeasurementAdapterImage(List<Uri> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_post_images,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        Uri imageUrl = imageList.get(position);

        Glide.with(context).load(imageUrl).into(holder.image);



    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.profilePostImage);
        }
    }
}
