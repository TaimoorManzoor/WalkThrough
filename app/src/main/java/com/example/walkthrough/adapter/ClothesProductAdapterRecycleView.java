package com.example.walkthrough.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.model.ClothesOrderRecivedModel;
import com.example.walkthrough.model.ClothesProductModel;

import java.util.ArrayList;

public class ClothesProductAdapterRecycleView {
//    ArrayList<ClothesProductModel> requestOrderRecyclelist;
//
//
//    public ClothesProductAdapterRecycleView(ArrayList<ClothesProductModel> requestOrderRecyclelist) {
//        this.requestOrderRecyclelist = requestOrderRecyclelist;
//    }
//
//
//
//    @NonNull
//    @Override
//    public ClothesProductAdapterRecycleView.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
//        View view=layoutInflater.inflate(R.layout.clothesproduct_recycle_view,parent,false);
//        return new ClothesProductAdapterRecycleView.MyViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ClothesProductAdapterRecycleView.MyViewHolder holder, int position) {
//        // Use toString() to convert non-string values to strings
//        holder.clothes_category.setText(requestOrderRecyclelist.get(position).getCategory().toString().trim());
//        holder.clothes_name.setText(requestOrderRecyclelist.get(position).getName().toString().trim());
//        holder.clothes_image.setImageResource(requestOrderRecyclelist.get(position).getImage());
//        holder.clothes_count.setText(requestOrderRecyclelist.get(position).getCount().toString().trim());
//        holder.clothes_detail.setText(requestOrderRecyclelist.get(position).getDetail().toString().trim());
//    }
//
//
//    @Override
//    public int getItemCount() {
//        if (requestOrderRecyclelist != null) {
//            return requestOrderRecyclelist.size();
//        } else {
//            return 0; // or handle this case appropriately
//        }
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder
//    {
//        private ImageView clothes_image;
//        private TextView clothes_name;
//        private TextView clothes_category;
//        private TextView clothes_count;
//        private TextView clothes_detail;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            clothes_image=itemView.findViewById(R.id.img);
//            clothes_name=itemView.findViewById(R.id.productname);
//            clothes_category=itemView.findViewById(R.id.productcategory);
//            clothes_count=itemView.findViewById(R.id.count);
//            clothes_detail=itemView.findViewById(R.id.detail);
//        }
//    }
}
