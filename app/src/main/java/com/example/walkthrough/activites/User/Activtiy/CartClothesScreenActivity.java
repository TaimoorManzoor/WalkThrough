package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.User.UserDashboardActivity;
import com.example.walkthrough.activites.User.fragments.DrawerNavigation.ListofClothesToUserFragment;
import com.example.walkthrough.activites.User.fragments.adapterClasses.CartOrderUserOrderAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.CartBookingModelClassDetails;
import com.example.walkthrough.activites.User.fragments.modelClasses.FinalOrdermodelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.OrderAddressesDetails;
import com.example.walkthrough.databinding.ActivityCartClothesScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CartClothesScreenActivity extends AppCompatActivity {
    private ActivityCartClothesScreenBinding binding;
    String productname, sellerID, productid, productimage, productprice, userID,productcount;
    FirebaseDatabase database;
    ArrayList<CartBookingModelClassDetails> detailsArrayList;
    CartOrderUserOrderAdapter orderAdapter;
    String aacountname,acountnumber,orderaddresses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartClothesScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        detailsArrayList = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        orderAdapter = new CartOrderUserOrderAdapter(detailsArrayList, CartClothesScreenActivity.this);
        binding.reyclerviewIDcart.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        binding.reyclerviewIDcart.setAdapter(orderAdapter);
        setDetails();
        click();
        showcartitems();
    }

    private void setDetails() {
        productname = getIntent().getStringExtra("productname");
        productid = getIntent().getStringExtra("ProductID");
        sellerID = getIntent().getStringExtra("SellerID");
        productimage = getIntent().getStringExtra("productimage");
        productprice = getIntent().getStringExtra("productprice");
        productcount = getIntent().getStringExtra("ProductCount");
        Toast.makeText(this, ""+productcount, Toast.LENGTH_SHORT).show();
        userID = getIntent().getStringExtra("userId");
        if (productid != null || productimage != null || productprice != null || userID != null) {
            DatabaseReference reference = database.getReference("user clothes Cart Details");
            CartBookingModelClassDetails obj = new CartBookingModelClassDetails(userID, sellerID, productid, productname, productprice, productimage, "Pending",productcount);
            reference.child("Details").child(userID).child(productid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {

                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CartClothesScreenActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }


    }

    private void showcartitems() {
        DatabaseReference ref = database.getReference(
                "user clothes Cart Details");
        ref.child("Details").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                detailsArrayList.clear();
                int totalsumprice = 0;
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        CartBookingModelClassDetails obj = dataSnapshot.getValue(CartBookingModelClassDetails.class);
                        String price = obj.getProductprice();
                        int totalprice = Integer.parseInt(price);
                        totalsumprice = totalprice + totalsumprice;
                        detailsArrayList.add(obj);
                    }
                    String totalsum = Integer.toString(totalsumprice);
                    binding.textView10.setText(totalsum);
                    binding.textView13.setText(totalsum);
                    binding.totalid.setText(totalsum);
                    orderAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CartClothesScreenActivity.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void click() {
        binding.btncheckoutid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aacountname=binding.ednameid.getText().toString();
                acountnumber=binding.edaccountid.getText().toString();
                orderaddresses=binding.edaddressesid.getText().toString();
                Sendorder(detailsArrayList);

            }
        });
    }

    private void Sendorder(ArrayList<CartBookingModelClassDetails> detailsArrayList) {
        FirebaseAuth auth;
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        if (user!=null){
            String uid=user.getUid();
            for (CartBookingModelClassDetails classDetails : detailsArrayList) {
                String orderSellerID = classDetails.getSellerID();
                String productid = classDetails.getProductId();
                String productprice=classDetails.getProductprice();
                DatabaseReference reference=database.getReference("User Final order Details").child("order").child(uid);
                String unquieID=reference.push().getKey().toString();
                FinalOrdermodelClass obj = new FinalOrdermodelClass(uid,orderSellerID,productid,"pending",productprice,productcount);
                reference.child(productid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            DatabaseReference reference1=database.getReference("Seller order Details").child("order");
                            reference1.child(orderSellerID).child(productid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                 if (task.isSuccessful()){
                                    DatabaseReference reference2=database.getReference("user clothes Cart Details").child("Details");
                                    reference2.child(userID).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                          if (task.isSuccessful()) {
                                              detailsArrayList.clear();
                                              orderAdapter.notifyDataSetChanged();
                                              sendAddressesDetails(orderSellerID);
                                              startActivity(new Intent(CartClothesScreenActivity.this, UserDashboardActivity.class));
                                          }
                                        }
                                    });
                                 }
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CartClothesScreenActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }else{

        }


    }
    private void sendAddressesDetails(String ordersellerID){
        DatabaseReference ref=database.getReference("DilveryAddresses").child("order");
        OrderAddressesDetails obj = new OrderAddressesDetails(ordersellerID,aacountname,acountnumber,orderaddresses);
        ref.child(ordersellerID).child(productid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}