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
import com.example.walkthrough.model.RequestOrderRecycleModel;

import java.util.ArrayList;

public class ClothesOrderRecivedAdapterRecycleview   extends RecyclerView.Adapter<ClothesOrderRecivedAdapterRecycleview.MyViewHolder>
{
    ArrayList<ClothesOrderRecivedModel> requestOrderRecyclelist;


    public ClothesOrderRecivedAdapterRecycleview(ArrayList<ClothesOrderRecivedModel> requestOrderRecyclelist) {
        this.requestOrderRecyclelist = requestOrderRecyclelist;
    }



    @NonNull
    @Override
    public ClothesOrderRecivedAdapterRecycleview.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.clothes_order_recived_recycle_view,parent,false);
        return new ClothesOrderRecivedAdapterRecycleview.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClothesOrderRecivedAdapterRecycleview.MyViewHolder holder, int position) {
        holder.clothes_category.setText(requestOrderRecyclelist.get(position).getClothes_category().toString().trim());
        holder.clothes_name.setText(requestOrderRecyclelist.get(position).getClothes_name().toString().trim());
        holder.clothes_image.setImageResource(requestOrderRecyclelist.get(position).getClothes_image());
        holder.clothes_price.setText(requestOrderRecyclelist.get(position).getClothes_price());
        holder.clothes_date.setText(requestOrderRecyclelist.get(position).getClothes_date());

      /*  holder.name_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.userhome, new productclothesFragment()).commit();
            }
        });*/
    }


    @Override
    public int getItemCount() {
        if (requestOrderRecyclelist != null) {
            return requestOrderRecyclelist.size();
        } else {
            return 0; // or handle this case appropriately
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView clothes_image;
        private TextView clothes_name;
        private TextView clothes_category;
        private TextView clothes_price;
        private TextView clothes_date;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            clothes_image=itemView.findViewById(R.id.img);
            clothes_name=itemView.findViewById(R.id.name);
            clothes_category=itemView.findViewById(R.id.suite_category);
            clothes_price=itemView.findViewById(R.id.price);
            clothes_date=itemView.findViewById(R.id.date);


        }
    }
}
