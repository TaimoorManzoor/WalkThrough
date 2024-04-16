package com.example.walkthrough.activites.User.fragments.adapterClasses;

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
import com.example.walkthrough.activites.User.Activtiy.ShowTailorDetailToUserActivtiy;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingClothesDetailsModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.FinalOrdermodelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ClothesUserOrderRequestAdapter extends RecyclerView.Adapter<ClothesUserOrderRequestAdapter.TailorListViewHolder>{
    private ArrayList<BookingClothesDetailsModelClass> tailorList;
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    public ClothesUserOrderRequestAdapter(ArrayList<BookingClothesDetailsModelClass> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_order_requested_clothes_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        final BookingClothesDetailsModelClass list = tailorList.get(position);
        holder.tvusername.setText(list.getProductname());
        Glide.with(context).load(list.getProductimage()).into(holder.image);
        holder.imagecross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DeleteRequested(list.getUserID(),list.getSellerID(), list.getProductID());
            }
        });

    }

    @Override
    public int getItemCount() {
        return tailorList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image,imagecross;
        TextView tvusername;
        CardView cardview;


        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.orderTailorImage);
            imagecross = itemView.findViewById(R.id.falseID);
            tvusername = itemView.findViewById(R.id.ordertailorNameText);
            cardview = itemView.findViewById(R.id.cardviewID);
//            tvtype = itemView.findViewById(R.id.tailorGenderText);
        }
    }
    private void DeleteRequested(String userID, String sellerID, String requestID){
        DatabaseReference userRequestsRef = FirebaseDatabase.getInstance()
                .getReference("User Final order Details")
                .child("order")
                .child(userID);
        DatabaseReference clothesRequestsRef = FirebaseDatabase.getInstance()
                .getReference("Seller order Details")
                .child("order")
                .child(sellerID);
        clothesRequestsRef.child(requestID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
//                    tailorList.remove(position);
                    notifyDataSetChanged();
                    userRequestsRef.child(requestID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            notifyDataSetChanged();

//                            Toast.makeText(context, "Request Delete", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });

    }


}
