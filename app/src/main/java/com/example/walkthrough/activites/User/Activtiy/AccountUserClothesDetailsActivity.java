package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.User.fragments.modelClasses.ClothesbankAccountDetails;
import com.example.walkthrough.databinding.ActivityAnimationPaymentBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountUserClothesDetailsActivity extends AppCompatActivity {
    EditText edusername, edaccount, edaddresses;
    Button btn;
    String username, useraccountnumber, useraddresses, currentuid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_clothes_details);
//        auth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        reference = firebaseDatabase.getReference("User Clothes Payment Details");
//        Ids();
//        Clicklistener();
    }
}
//    private void Ids(){
//        edusername=findViewById(R.id.ednameid);
//        edaccount=findViewById(R.id.edaccountid);
//        edaddresses=findViewById(R.id.edaddressesid);
//        btn=findViewById(R.id.btnsndorderid);
//    }
//    private void Clicklistener(){
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                username=edusername.getText().toString().trim();
//                useraccountnumber=edaccount.getText().toString().trim();
//                useraddresses=edaddresses.getText().toString().trim();
//                if (username.isEmpty()){
//                    Toast.makeText(AccountUserClothesDetailsActivity.this, "Enter details please", Toast.LENGTH_SHORT).show();
//                }else{
//                    AddDetails();
//                }
//            }
//        });
//    }
//    private void AddDetails(){
//        FirebaseUser user= auth.getCurrentUser();
//        if (user!=null){
//            currentuid=user.getUid();
//            ClothesbankAccountDetails obj = new ClothesbankAccountDetails(currentuid,username,useraddresses,useraccountnumber);
//            reference.child(currentuid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                 if (task.isSuccessful()){
//
//                 }
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(AccountUserClothesDetailsActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
