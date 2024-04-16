package com.example.walkthrough.activites.Tailor.adapterClasses;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.TailerDashboardActivity;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.ImageModelClass;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TailorOrderRequestAdapter extends RecyclerView.Adapter<TailorOrderRequestAdapter.TailorListViewHolder>{
    private ArrayList<BookingDetailModelClass> tailorList;
    Context context;


    public TailorOrderRequestAdapter(ArrayList<BookingDetailModelClass> tailorList, Context context) {
        this.tailorList = tailorList;
        this.context = context;
        if (context == null) {
            throw new IllegalArgumentException("Context cannot be null in TailorOrderRequestAdapter");
        }
    }
    public void setFilteredlist(List<BookingDetailModelClass> filteredlist){
        this.tailorList= (ArrayList<BookingDetailModelClass>) filteredlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TailorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_tailor_list,parent,false);
        return new TailorListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TailorListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!tailorList.isEmpty() && position < tailorList.size()) {
            final BookingDetailModelClass list = tailorList.get(position);
            holder.tvusername.setText(list.getUsername());
            if (context != null) {
                Glide.with(context)
                        .load(list.getUserimage())
                        .into(holder.image);
            }
            holder.imagecheck.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateRequestStatus(list.getUserID(),list.getTailorID(), list.getReqID(),position);
                }
            });
            holder.imagefalse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteRequested(list.getUserID(),list.getTailorID(), list.getReqID());
                }
            });

        }
        }


    @Override
    public int getItemCount() {
        return tailorList.size();
    }

    static class TailorListViewHolder extends RecyclerView.ViewHolder{
        ImageView image,imagecheck,imagefalse;
        TextView tvusername;

        public TailorListViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.orderTailorImage);
            imagecheck = itemView.findViewById(R.id.tickID);
            imagefalse = itemView.findViewById(R.id.falseID);
            tvusername = itemView.findViewById(R.id.ordertailorNameText);
        }
    }
    private void updateRequestStatus(String userID, String tailorID, String requestID, int position) {
        DatabaseReference userRequestsRef = FirebaseDatabase.getInstance()
                .getReference("UserSalaiRequests")
                .child(userID)
                .child("UserRequests");

        DatabaseReference tailorRequestsRef = FirebaseDatabase.getInstance()
                .getReference("TailorSalaiRequests")
                .child(tailorID)
                .child("TailorRequests");

        // Update the requestStatus field on the user side
        tailorRequestsRef.child(requestID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    snapshot.getRef().child("requestStatus").setValue("Accepted")
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
                                        userRequestsRef.child(requestID).child("requestStatus").setValue("Accepted")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            // Check if the list is empty
                                                            if (tailorList.isEmpty()) {
                                                                // Display a message or update your UI accordingly
                                                                // For example, show a Toast message or update a TextView
                                                                updateEmptyListUI();
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

    private void updateEmptyListUI() {
    }

    private void DeleteRequested(String userID, String tailorID, String requestID){
        DatabaseReference tailorRequestsRef = FirebaseDatabase.getInstance()
                .getReference("TailorSalaiRequests")
                .child(tailorID)
                .child("TailorRequests");
        DatabaseReference userRequestsRef = FirebaseDatabase.getInstance()
                .getReference("UserSalaiRequests")
                .child(userID)
                .child("UserRequests");
        tailorRequestsRef.child(requestID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
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
