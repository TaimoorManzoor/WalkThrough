package com.example.walkthrough.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.walkthrough.R;

public class CodeVerificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_verification);
        ImageView backArrowImage=findViewById(R.id.backArrowImage);
        Button verify_btn=findViewById(R.id.verify_btn);

        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CodeVerificationActivity.this,SelectMethodActivity.class);
                startActivity(intent);
            }
        });
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(CodeVerificationActivity.this,NewCredentialActivity.class);
                startActivity(intent);
            }
        });
    }
}