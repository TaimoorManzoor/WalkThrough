package com.example.walkthrough.activites.Clothes.AdapterClass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.Activities.ViewDilveryOrderAddressesActivity;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingClothesDetailsModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ConOrderClothesOrderRequestAdapter extends RecyclerView.Adapter<ConOrderClothesOrderRequestAdapter.TailorListViewHolder>{
    private ArrayList<BookingClothesDetailsModelClass> tailorList;
    Context context;

    public ConOrderClothesOrderRequestAdapter(ArrayList<BookingClothesDetailsModelClass> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.confrimed_order_requested_clothes_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final BookingClothesDetailsModelClass list = tailorList.get(position);

        holder.tvusername.setText(list.getProductname());
        holder.tvcount.setText(list.getProductcounter());
        Glide.with(context).load(list.getProductimage()).into(holder.image);
//        holder.tvprice.setText(list.);
//        holder.imagecheck.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               updateRequestStatus(list.getUserID(), list.getSellerID(), list.getProductID(),position);
//            }
//        });
//        holder.imagecross.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        holder.cardViewclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent obj = new Intent(context, ViewDilveryOrderAddressesActivity.class);
                obj.putExtra("sellerID",list.getSellerID());
                obj.putExtra("productID",list.getProductID());
                obj.putExtra("productcount",list.getProductcounter());
                obj.putExtra("productimage",list.getProductimage());
                context.startActivity(obj);

            }
        });


    }

    @Override
    public int getItemCount() {
        return tailorList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView tvusername,tvprice,tvcount;
        CardView cardViewclick;

        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.orderTailorImage);
//            imagecheck = itemView.findViewById(R.id.tickID);
//            imagecross = itemView.findViewById(R.id.falseID);
            tvusername = itemView.findViewById(R.id.ordertailorNameText);
            tvprice = itemView.findViewById(R.id.tailorGenderText);
             tvcount= itemView.findViewById(R.id.textcountid);
            cardViewclick = itemView.findViewById(R.id.cardviewID);

        }
    }
    private void updateRequestStatus(String userID, String sellerID, String requestID, int position) {
        DatabaseReference userRequestsRef = FirebaseDatabase.getInstance()
                .getReference("User Final order Details")
                .child("order")
                .child(userID);

        DatabaseReference tailorRequestsRef = FirebaseDatabase.getInstance()
                .getReference("Seller order Details")
                .child("order")
                .child(sellerID);


        // Update the requestStatus field on the user side
        tailorRequestsRef.child(requestID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    snapshot.getRef().child("orderStatus").setValue("Accepted")
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        // Remove the item at the specified position from the list
                                        if (!tailorList.isEmpty() && position < tailorList.size()) {
                                            tailorList.remove(position);
                                        }
                                        notifyDataSetChanged();

                                        // Update the requestStatus on the user side
                                        userRequestsRef.child(requestID).child("orderStatus").setValue("Accepted")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            // Check if the list is empty
                                                            if (tailorList.isEmpty()) {
                                                                // Display a message or update your UI accordingly
                                                                // For example, show a Toast message or update a TextView

                                                                notifyDataSetChanged();
                                                            }
                                                        }
                                                    }
                                                });
                                    } else {
                                        Toast.makeText(context, "Failed to update request status: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(context, "Request with ID " + requestID + " not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Error checking request status: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}
