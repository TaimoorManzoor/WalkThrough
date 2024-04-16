package com.example.walkthrough.activites.Clothes.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.User.fragments.modelClasses.OrderAddressesDetails;
import com.example.walkthrough.model.AddClothesDetailToRealtym;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDilveryOrderAddressesActivity extends AppCompatActivity {
    String sellerID,productID,image;
    TextView tvname,tvnum,tvadd;
    ImageView imag;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_dilvery_order_addresses);
        tvname=findViewById(R.id.ednameid);
        imag=findViewById(R.id.imageID);
        tvnum=findViewById(R.id.edaccountid);
        tvadd=findViewById(R.id.edaddressesid);
        sellerID=getIntent().getStringExtra("sellerID");
        productID=getIntent().getStringExtra("productID");
        image=getIntent().getStringExtra("productimage");
        Glide.with(ViewDilveryOrderAddressesActivity.this).load(image).into(imag);
        if (sellerID!=null || productID!=null){
            SetDetails();
        }else{

        }
    }
    private void SetDetails(){

        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(
                        "DilveryAddresses").child(
                        "order").
                child(sellerID);
        ref.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    OrderAddressesDetails obj=snapshot.getValue(OrderAddressesDetails.class);
                     tvname.setText(obj.getAccountname());
                     tvnum.setText(obj.getAccountnumber());
                     tvadd.setText(obj.getOrderAddresses());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ViewDilveryOrderAddressesActivity.this, "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}