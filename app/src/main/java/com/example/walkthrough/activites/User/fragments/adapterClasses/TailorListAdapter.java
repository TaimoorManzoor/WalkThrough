package com.example.walkthrough.activites.User.fragments.adapterClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.User.Activtiy.ShowTailorDetailToUserActivtiy;
import com.example.walkthrough.activites.User.fragments.modelClasses.SliderData;
import com.example.walkthrough.activites.User.fragments.modelClasses.TailorList;
import com.example.walkthrough.model.AddTailorDetailToRealtym;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TailorListAdapter extends RecyclerView.Adapter<TailorListAdapter.TailorListViewHolder>{
    private ArrayList<AddTailorDetailToRealtym> tailorList;
    Context context;

    public TailorListAdapter(ArrayList<AddTailorDetailToRealtym> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
    }
    public void setFilteredlist(List<AddTailorDetailToRealtym> filteredlist){
        this.tailorList= (ArrayList<AddTailorDetailToRealtym>) filteredlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tailors_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        final AddTailorDetailToRealtym list = tailorList.get(position);

        holder.name.setText(list.getUsername());
        holder.gender.setText(list.getClothestype());
        holder.price.setText(list.getTailorprice());
        Glide.with(context).load(list.getImageurl()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ShowTailorDetailToUserActivtiy.class);
                i.putExtra("userID",list.getUserid());
                i.putExtra("name",list.getUsername());
                i.putExtra("image",list.getImageurl());
                i.putExtra("price",list.getTailorprice());
                i.putExtra("phone",list.getPhone());
                i.putExtra("add",list.getTailoraddresses());
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tailorList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        TextView name, gender, price;
        CircleImageView image;
        CardView cardView;
        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tailorNameText);
            gender = itemView.findViewById(R.id.tailorGenderText);
            price = itemView.findViewById(R.id.priceText);
            image = itemView.findViewById(R.id.tailorImage);
            cardView = itemView.findViewById(R.id.cardviewID);
        }
    }
}
