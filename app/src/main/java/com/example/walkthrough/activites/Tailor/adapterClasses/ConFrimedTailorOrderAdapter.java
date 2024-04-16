package com.example.walkthrough.activites.Tailor.adapterClasses;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.example.walkthrough.activites.Tailor.ViewTailorMeasurementDetailsActivity;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ConFrimedTailorOrderAdapter extends RecyclerView.Adapter<ConFrimedTailorOrderAdapter.TailorListViewHolder>{
    private ArrayList<BookingDetailModelClass> tailorList;
    Context context;

    public ConFrimedTailorOrderAdapter(ArrayList<BookingDetailModelClass> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
    }
    public void setFilteredlist(List<BookingDetailModelClass> filteredlist){
        this.tailorList= (ArrayList<BookingDetailModelClass>) filteredlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tailor_user_customer_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, int position) {
        final BookingDetailModelClass list = tailorList.get(position);

        holder.tvusername.setText(list.getUsername());
        Glide.with(context).load(list.getUserimage()).into(holder.image);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent obj =new Intent(context, ViewTailorMeasurementDetailsActivity.class);
               obj.putExtra("id",list.getUserID());
               obj.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        CardView cardView;

        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.tailorImage);
            cardView = itemView.findViewById(R.id.cardviewID);
            tvusername = itemView.findViewById(R.id.tailorNameText);

        }
    }


    }

