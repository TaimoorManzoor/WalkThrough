package com.example.walkthrough.activites.Clothes.AdapterClass;

import android.content.Context;
import android.content.Intent;
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
import com.example.walkthrough.activites.Clothes.ModelClass.ClothesBookingModelClass;
import com.example.walkthrough.activites.Tailor.ViewTailorMeasurementDetailsActivity;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;

import java.util.ArrayList;
import java.util.List;

public class ConFrimedClothesOrderAdapter extends RecyclerView.Adapter<ConFrimedClothesOrderAdapter.TailorListViewHolder>{
    private ArrayList<ClothesBookingModelClass> tailorList;
    Context context;

    public ConFrimedClothesOrderAdapter(ArrayList<ClothesBookingModelClass> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
    }
    public void setFilteredlist(List<ClothesBookingModelClass> filteredlist){
        this.tailorList= (ArrayList<ClothesBookingModelClass>) filteredlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_customer_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        final ClothesBookingModelClass list = tailorList.get(position);

        holder.tvusername.setText(list.getUsername());
        holder.tvproductname.setText(list.getProductname());
        holder.tvproductprice.setText(list.getProductprice());
        Glide.with(context).load(list.getProductimage()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//               Intent obj =new Intent(context, ViewTailorMeasurementDetailsActivity.class);
//               obj.putExtra("id",list.getUserID());
//               obj.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//               context.startActivity(obj);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tailorList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvusername,tvproductname,tvproductprice;
        CardView cardView;

        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tailorImage);
            cardView = itemView.findViewById(R.id.cardviewID);
            tvusername = itemView.findViewById(R.id.tailorNameText);
            tvproductname = itemView.findViewById(R.id.productname);
            tvproductprice = itemView.findViewById(R.id.productprice);

        }
    }


    }

