package com.example.walkthrough.activites;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.SharedPrefPkg.PrefManager;
import com.example.walkthrough.activites.User.UserProfileActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInUserActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    ProgressDialog dialog;
    String userID;
    PrefManager prefManager;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_user);

        TextView registerLink = findViewById(R.id.registerLink);
        ImageView backArrowImage = findViewById(R.id.backArrowImage);
        Button signup_btn = findViewById(R.id.signupButton);
        EditText email_editText = findViewById(R.id.loginEmail);
        EditText password_editText = findViewById(R.id.loginPassword);


        prefManager=new PrefManager(this);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference("SailAppForUser");
        String getcurrentstatus=getIntent().getStringExtra("currentStatus");

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInUserActivity.this, UserActivity.class);
                intent.putExtra("currentuserdata",getcurrentstatus);
                startActivity(intent);
            }
        });
        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInUserActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        TextView forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInUserActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_editText.getText().toString().trim();
                String password = password_editText.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInUserActivity.this, "Enter Detail please", Toast.LENGTH_SHORT).show();
                } else if (!email.contains("@gmail.com")) {
                    Toast.makeText(SignInUserActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(SignInUserActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    dialog = new ProgressDialog(SignInUserActivity.this);
                    dialog.setTitle("Ez Sail");
                    dialog.setTitle("PLease wait");
                    dialog.show();
                    loginWithFirebase(email_editText.getText().toString().trim(), password_editText.getText().toString().trim());
                }
            }
        });
    }

    private void loginWithFirebase(String email, String password) {

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    user = auth.getCurrentUser();
                    if (user!=null && user.isEmailVerified()){
                        userID = user.getUid();
                        ref.child("Account Details").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
//                                    String currentStatus = dataSnapshot.child("currentstatus").getValue(String.class);
//                                    if (currentStatus !=null || currentStatus.equals("User")){
                                        dialog.hide();
                                        prefManager.setCurrentstatus("User");
                                        startActivity(new Intent(SignInUserActivity.this, UserProfileActivity.class));
                                        Toast.makeText(SignInUserActivity.this, "User is exists", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        dialog.hide();
                                        // Handle the case where currentStatus is null
                                        Toast.makeText(SignInUserActivity.this, "Sorry you are not a user", Toast.LENGTH_SHORT).show();
                                    }
                                }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.e(TAG, "Error reading user role", databaseError.toException());
                                // Handle the error as needed
                            }
                        });
                    }else{
                        dialog.hide();
                        Emailverification(user);
                    }

                } else {
                    dialog.hide();
                }
                }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
                Toast.makeText(SignInUserActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void Emailverification(FirebaseUser user){
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                 if (task.isSuccessful()){
                     Toast.makeText(SignInUserActivity.this,"Please verify the Email First", Toast.LENGTH_SHORT).show();
                 }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignInUserActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}