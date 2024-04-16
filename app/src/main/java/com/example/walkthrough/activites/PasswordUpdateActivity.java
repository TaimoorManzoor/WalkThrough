package com.example.walkthrough.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.walkthrough.R;

public class PasswordUpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_update);
        ImageView backArrowImage=findViewById(R.id.backArrowImage);
        Button next_btn=findViewById(R.id.signIn);

        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PasswordUpdateActivity.this,NewCredentialActivity.class);
                startActivity(intent);
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(PasswordUpdateActivity.this,SignInUserActivity.class);
                startActivity(intent);
            }
        });
    }
}