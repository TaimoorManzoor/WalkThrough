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
import com.example.walkthrough.activites.User.Activtiy.ShowTailorDetailToUserActivtiy;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserOrderRequestAdapter extends RecyclerView.Adapter<UserOrderRequestAdapter.TailorListViewHolder>{
    private ArrayList<BookingDetailModelClass> tailorList;
    Context context;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    public UserOrderRequestAdapter(ArrayList<BookingDetailModelClass> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
        // Ensure context is not null before using it
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null in UserOrderRequestAdapter");
        }

    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_order_requested_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        final BookingDetailModelClass list = tailorList.get(position);
        holder.tvusername.setText(list.getUsername());
        if (context != null) {
            Glide.with(context)
                    .load(list.getUserimage())
                    .into(holder.image);
        }
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj = new Intent(context, ShowTailorDetailToUserActivtiy.class);
                obj.putExtra("userID",list.getTailorID());
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
        TextView tvusername;
        CardView cardview;


        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tailorImageid);
            tvusername = itemView.findViewById(R.id.tailorNameTextid);
            cardview = itemView.findViewById(R.id.cardviewID);
//            tvtype = itemView.findViewById(R.id.tailorGenderText);
        }
    }

}
