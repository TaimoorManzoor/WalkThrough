package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.User.UserDashboardActivity;

import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.databinding.ActivityUserCheckoutBinding;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.example.walkthrough.model.RequestedOrderModel;
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

import java.util.Set;

public class UserCheckoutActivity extends AppCompatActivity {
    private ActivityUserCheckoutBinding binding;

    private EditText editTextCardNumber, editTextCVV, editTextExpirationDate;
    private EditText editTextAmount, editTextDescription,editTextotp;
    Button buttonVerifyOTP ;

    final String url1="https://payway-backend.vercel.app/api/card/initiate-eazy-silai-card-payment";

    String tailorid,UserID,tailorAdd,TailorPrice;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserCheckoutBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        tailorid=getIntent().getStringExtra("tailorid");
        UserID=getIntent().getStringExtra("userid");
        tailorAdd=getIntent().getStringExtra("addresses");

        editTextCardNumber = findViewById(R.id.editTextCardNumber);
        editTextCVV = findViewById(R.id.editTextCVV);
        editTextExpirationDate = findViewById(R.id.editTextExpirationDate);
        editTextAmount = findViewById(R.id.editTextAmount);
        editTextDescription = findViewById(R.id.editTextDescription);


        tailorid=getIntent().getStringExtra("tailorid");
        UserID=getIntent().getStringExtra("userid");
        tailorAdd=getIntent().getStringExtra("addresses");
        binding.locationText.setText(tailorAdd);
        SetDetails();
        Click();

    }
    private void SetDetails(){
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(
                        "TailorProfile").child(
                        "Tailor").
                child(tailorid.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    AddTailorDetailToRealtym obj=snapshot.getValue(AddTailorDetailToRealtym.class);
                    Glide.with(UserCheckoutActivity.this).load(obj.getImageurl()).into(binding.imageviewid);
                    binding.tailornameid.setText(obj.getUsername());
                    binding.statusid.setText(obj.getClothestype());
                    binding.price.setText(obj.getTailorprice());
                    TailorPrice=obj.getTailorprice();
                    binding.textView29.setText(TailorPrice);
                    binding.textView32.setText(obj.getTailorprice());
                    binding.totalid.setText(obj.getTailorprice());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserCheckoutActivity.this, "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Click(){

        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserCheckoutActivity.this, UserHiringActivity.class));

            }
        });
        binding.cardviewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextCVV.getText().toString().trim().isEmpty()  && !editTextCardNumber.getText().toString().trim().isEmpty() && !editTextExpirationDate.getText().toString().trim().isEmpty())
                {
                    SendRequest();
                    startActivity(new Intent(UserCheckoutActivity.this, AnimationPaymentActivity.class));
                    Toast.makeText(UserCheckoutActivity.this, "Payment Process and Approved By SuperVisior", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(UserCheckoutActivity.this, "Plase Fill Out the Card Info", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void SendRequest(){
        FirebaseDatabase database;
        database=FirebaseDatabase.getInstance();
        DatabaseReference ref;
        ref=database.getReference("UserSalaiRequests").child(UserID).child("UserRequests");
        DatabaseReference ref1=database.getReference("TailorSalaiRequests").child(tailorid).child("TailorRequests");
        String unquieID=ref.push().getKey().toString();
        if (unquieID!=null){
            RequestModelCLass obj =new RequestModelCLass(UserID,tailorid,unquieID,"pending",TailorPrice);
            ref.child(unquieID).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        ref1.child(unquieID).setValue(obj);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UserCheckoutActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }}