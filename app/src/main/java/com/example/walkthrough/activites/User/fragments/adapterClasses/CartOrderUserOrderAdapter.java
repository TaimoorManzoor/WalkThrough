package com.example.walkthrough.activites.User.fragments.adapterClasses;

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
import com.example.walkthrough.activites.User.Activtiy.ViewUserMeasurementDetailsActivity;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.CartBookingModelClassDetails;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CartOrderUserOrderAdapter extends RecyclerView.Adapter<CartOrderUserOrderAdapter.TailorListViewHolder>{
    private ArrayList<CartBookingModelClassDetails> tailorList;
    Context context;

    public CartOrderUserOrderAdapter(ArrayList<CartBookingModelClassDetails> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_deailuser,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        final CartBookingModelClassDetails list = tailorList.get(position);
        holder.tvusername.setText(list.getProductname());
        holder.tvprice.setText(list.getProductprice());
        Glide.with(context).load(list.getProductimage()).into(holder.image);

        holder.imagedel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delcarddata(list.getUserID(),list.getProductId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return tailorList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image,imagedel;
        TextView tvusername,tvprice;
        CardView cardview;


        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tailorImage);
            imagedel = itemView.findViewById(R.id.deleteIDimage);
            tvusername = itemView.findViewById(R.id.tailorNameText);
            tvprice = itemView.findViewById(R.id.priceText);
            cardview = itemView.findViewById(R.id.recyclerView);
//            tvtype = itemView.findViewById(R.id.tailorGenderText);
        }
    }
    private void delcarddata(String userID,String productID){
        FirebaseDatabase database;
        DatabaseReference ref;
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("user clothes Cart Details").child("Details");
        ref.child(userID).child(productID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    notifyDataSetChanged();
                    Toast.makeText(context, "Remove", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
