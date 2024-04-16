package com.example.walkthrough.activites.Clothes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.activites.Clothes.AdapterClass.OrderClothesOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingClothesDetailsModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.FinalOrdermodelClass;
import com.example.walkthrough.databinding.ActivityRequestedClothesOrderBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RequestedClothesOrderActivity extends AppCompatActivity {
    private ActivityRequestedClothesOrderBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRequestedClothesOrderBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dialog = new ProgressDialog(RequestedClothesOrderActivity.this);
        dialog.setMessage("please wait...");
        dialog.show();
        GetlistofClothes();
    }
    private void GetlistofClothes() {
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            ArrayList<BookingClothesDetailsModelClass> list;
            list = new ArrayList<>();
            OrderClothesOrderRequestAdapter adapters = new OrderClothesOrderRequestAdapter(list, RequestedClothesOrderActivity.this);
            binding.ordersTailorRCV.setLayoutManager(new LinearLayoutManager(RequestedClothesOrderActivity.this));
            binding.ordersTailorRCV.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("Seller order Details").child(
                    "order");

            ref.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FinalOrdermodelClass obj = dataSnapshot.getValue(FinalOrdermodelClass.class);
                            String producID = obj.getProductID();
                            String status = obj.getOrderStatus();
                            if (producID!=null && status.equals("pending")){
                                DatabaseReference ref2 = firebaseDatabase.getReference("Clothes Product Details").child(uid);
                                ref2.child(producID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String userName = snapshot.child("sellerproductusername").getValue(String.class);
                                            String userImage = snapshot.child("productimage").getValue(String.class);
                                            list.add(new BookingClothesDetailsModelClass(obj.getUserID(), obj.getSellerID(), obj.getProductID(),userImage,userName,obj.getProductcount()));

                                        }
                                        adapters.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        dialog.dismiss();
                                        Toast.makeText(RequestedClothesOrderActivity.this, "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        adapters.notifyDataSetChanged();
                    } else {
                        adapters.notifyDataSetChanged();
                        Toast.makeText(RequestedClothesOrderActivity.this, "No Request found", Toast.LENGTH_SHORT).show();
                    }
                    dialog.hide();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(RequestedClothesOrderActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }

    }

}