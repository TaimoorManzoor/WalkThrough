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

import java.util.List;

public class ProductFetchAdapterImage extends RecyclerView.Adapter<ProductFetchAdapterImage.TailorListViewHolder>{
    private List<ImageModel> imageList;
    private Context context;

    public ProductFetchAdapterImage(Context context, List<ImageModel> imageList) {
        this.context = context;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_images_post_images,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        ImageModel imageModel = imageList.get(position);
        Glide.with(context).load(imageModel.getImageUrl()).into(holder.image);



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
