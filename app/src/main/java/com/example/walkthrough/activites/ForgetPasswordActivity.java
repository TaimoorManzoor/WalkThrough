package com.example.walkthrough.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {
    Button btnsnnemail;
    ImageView tvlogin;
    EditText edemail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
       IDs();
       Clicklistener();


    }
    @SuppressLint("WrongViewCast")
    private void IDs(){
        btnsnnemail=findViewById(R.id.next_btn);
        edemail=findViewById(R.id.emailLogin);
        tvlogin=findViewById(R.id.backArrowImage);
    }
    private void Clicklistener(){
        btnsnnemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edemail.getText().toString().isEmpty()
                ) {
                    Toast.makeText(ForgetPasswordActivity.this, "Enter valid Email", Toast.LENGTH_SHORT).show();
                } else if (!edemail.getText().toString().contains("@gmail.com")) {
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                } else {
                    ValidData(edemail.getText().toString());
                }
            }
        });
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SignInUserActivity.class));
            }
        });
    }
    private void ValidData(String email){
        mAuth = FirebaseAuth.getInstance();
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this, "PLz Check your email", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ForgetPasswordActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}