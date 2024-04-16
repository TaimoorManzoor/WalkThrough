package com.example.walkthrough.activites;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.walkthrough.activites.Tailor.TailorProfileActivity;
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

public class TailorSignInActivtiy extends AppCompatActivity {
    FirebaseAuth auth;
    DatabaseReference ref;
    ProgressDialog dialog;
    FirebaseDatabase firebaseDatabase;
    String userID;

    PrefManager prefManager;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_sign_in_activtiy);
        TextView registerLink = findViewById(R.id.registerLink);
        ImageView backArrowImage = findViewById(R.id.backArrowImage);
        Button signup_btn = findViewById(R.id.signupButton);
        EditText email_editText = findViewById(R.id.loginEmail);
        EditText password_editText = findViewById(R.id.loginPassword);
        String gettailorcurrentstatus = getIntent().getStringExtra("tailorcurrentStatus");
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("SailAppForTailor");
        auth = FirebaseAuth.getInstance();
        prefManager=new PrefManager(this);
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TailorSignInActivtiy.this, TailorSignupActivty.class);
                intent.putExtra("currentuserdata", gettailorcurrentstatus);
                startActivity(intent);
            }
        });
        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TailorSignInActivtiy.this, MainActivity.class);
                startActivity(intent);
            }
        });
        TextView forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TailorSignInActivtiy.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_editText.getText().toString().trim();
                String password = password_editText.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(TailorSignInActivtiy.this, "Enter Detail please", Toast.LENGTH_SHORT).show();
                } else if (!email.contains("@gmail.com")) {
                    Toast.makeText(TailorSignInActivtiy.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(TailorSignInActivtiy.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    dialog = new ProgressDialog(TailorSignInActivtiy.this);
                    dialog.setTitle("Ez Sail");
                    dialog.setTitle("PLease wait");
                    dialog.show();
                    loginWithFirebase(email_editText.getText().toString().trim(), password_editText.getText().toString().trim());
                }
            }
        });

    }

    private void loginWithFirebase(String email, String password) {

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        dialog.dismiss(); // Dismiss the dialog here, as it should be hidden in both success and failure cases

                        if (task.isSuccessful()) {
                            FirebaseUser user = auth.getCurrentUser();
                            if (user != null && user.isEmailVerified()) {
                                userID = user.getUid();
                                ref.child("Account Details").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            prefManager.setCurrentstatus("Tailor");
                                            startActivity(new Intent(TailorSignInActivtiy.this, TailorProfileActivity.class));
                                            Toast.makeText(TailorSignInActivtiy.this, "Welcome", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Handle the case where currentStatus is null
                                            Toast.makeText(TailorSignInActivtiy.this, "Sorry, You are not a Tailor", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Log.e(TAG, "Error reading user role", databaseError.toException());
                                        // Handle the error as needed
                                    }
                                });
                            } else {
                                dialog.hide();
                                Emailverification(user);
                            }
                        } else {
                            Log.e(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(TailorSignInActivtiy.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Sign-in process failed", e);
                        Toast.makeText(TailorSignInActivtiy.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
    private void Emailverification(FirebaseUser user){
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(TailorSignInActivtiy.this,"Please verify the Email First", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(TailorSignInActivtiy.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
    }



