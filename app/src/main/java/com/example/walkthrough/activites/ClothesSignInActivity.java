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
import com.example.walkthrough.activites.Clothes.Activities.ClothesDashboardTwoActivity;
import com.example.walkthrough.activites.Clothes.ClothesProfileActivity;
import com.example.walkthrough.activites.Tailor.TailorProfileActivity;
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

public class ClothesSignInActivity extends AppCompatActivity {
    FirebaseAuth auth;
    TextView registerLink;
    ProgressDialog dialog;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    PrefManager prefManager;
    String userID;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_sign_in);

        registerLink = findViewById(R.id.TregisterLink);
        ImageView backArrowImage = findViewById(R.id.backArrowImage);
        Button signup_btn = findViewById(R.id.signupButton);
        EditText email_editText = findViewById(R.id.loginEmail);
        EditText password_editText = findViewById(R.id.loginPassword);

        prefManager=new PrefManager(this);

        auth = FirebaseAuth.getInstance();
        String getclothescurrentstatus=getIntent().getStringExtra("clothescurrentStatus");
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesSignInActivity.this, ClothesSignUpActivity.class);
                intent.putExtra("currentuserdata",getclothescurrentstatus);
                startActivity(intent);
            }
        });
        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesSignInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        TextView forgetPassword = findViewById(R.id.forgetPassword);
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ClothesSignInActivity.this, ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_editText.getText().toString().trim();
                String password = password_editText.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(ClothesSignInActivity.this, "Enter Detail please", Toast.LENGTH_SHORT).show();
                } else if (!email.contains("@gmail.com")) {
                    Toast.makeText(ClothesSignInActivity.this, "Enter valid email", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 6) {
                    Toast.makeText(ClothesSignInActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                } else {
                    dialog =new ProgressDialog(ClothesSignInActivity.this);
                    dialog.setTitle("Ez Sail");
                    dialog.show();
                    loginWithFirebase(email_editText.getText().toString().trim(), password_editText.getText().toString().trim());
                }
            }
        });
    }
    private void loginWithFirebase(String email, String password) {
        firebaseDatabase=FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference(
                "SailAppForClothes");
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
                                            prefManager.setCurrentstatus("Clothes");
                                            startActivity(new Intent(ClothesSignInActivity.this, ClothesProfileActivity.class));
                                            Toast.makeText(ClothesSignInActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                        } else {
                                            // Handle the case where currentStatus is null
                                            Toast.makeText(ClothesSignInActivity.this, "Sorry, You are not a Seller", Toast.LENGTH_SHORT).show();
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
                                Emailverification(user);                       }
                        } else {
                            Log.e(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ClothesSignInActivity.this, "Authentication failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "Sign-in process failed", e);
                        Toast.makeText(ClothesSignInActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void Emailverification(FirebaseUser user){
        if (user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(ClothesSignInActivity.this,"Please verify the Email First", Toast.LENGTH_SHORT).show();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(ClothesSignInActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }

    }
}