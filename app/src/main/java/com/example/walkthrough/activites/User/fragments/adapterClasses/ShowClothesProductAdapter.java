package com.example.walkthrough.activites.User.fragments.adapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.User.Activtiy.BookClothesUserActivity;

import java.util.ArrayList;

public class ShowClothesProductAdapter extends RecyclerView.Adapter<ShowClothesProductAdapter.ClothesProductViewHolder> {
    private ArrayList<AddCategoryProductModelClass> data;
    private Context context;

    public ShowClothesProductAdapter(ArrayList<AddCategoryProductModelClass> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ClothesProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clothes_products_detail, parent, false);
        return new ClothesProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesProductViewHolder holder, int position) {
        final AddCategoryProductModelClass productsData = data.get(position);
        Glide.with(context).load(productsData.getProductimage()).into(holder.productImg);

        holder.productName.setText(productsData.getSellerproductusername());
        holder.productCategory.setText(productsData.getCategory());
        holder.productPrice.setText(productsData.getColor());
        holder.tvproductdes.setText(productsData.getDes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj =new Intent(context, BookClothesUserActivity.class);
                obj.putExtra("sellerid",productsData.getSelleruserID());
                obj.putExtra("image",productsData.getProductimage());
                obj.putExtra("name",productsData.getSellerproductusername());
                obj.putExtra("price",productsData.getColor());
                obj.putExtra("des",productsData.getDes());
                obj.putExtra("productID",productsData.getProductID());
                context.startActivity(obj);
//
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ClothesProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImg;
        TextView productName, productCategory, productPrice, productCount,tvproductdes;

        public ClothesProductViewHolder(@NonNull View itemView) {
            super(itemView);

            productImg = itemView.findViewById(R.id.productClothImage);
            productName = itemView.findViewById(R.id.productClothName);
            productCategory = itemView.findViewById(R.id.productClothCategory);
            productPrice = itemView.findViewById(R.id.productClothPrice);
            tvproductdes = itemView.findViewById(R.id.clothesdes);

        }
    }
}
