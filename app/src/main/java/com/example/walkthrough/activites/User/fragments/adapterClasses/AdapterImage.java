package com.example.walkthrough.activites.User.fragments.adapterClasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.User.Activtiy.ShowTailorDetailToUserActivtiy;
import com.example.walkthrough.activites.User.fragments.modelClasses.ImageModelClass;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterImage extends RecyclerView.Adapter<AdapterImage.TailorListViewHolder>{
    ArrayList<ImageModelClass> list;
    Context context;


    public AdapterImage(ArrayList<ImageModelClass> list, Context context) {
        this.list = list;
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
        ImageModelClass imageUrl = list.get(position);

        Glide.with(context).load(imageUrl.getDrawable()).into(holder.image);



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
