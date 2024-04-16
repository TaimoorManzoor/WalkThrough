package com.example.walkthrough.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.walkthrough.R;

public class SelectMethodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_method);

        ImageView backArrowImage=findViewById(R.id.backArrowImage);
        Button next_btn=findViewById(R.id.next_btn);

        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SelectMethodActivity.this,ForgetPasswordActivity.class);
                startActivity(intent);
            }
        });
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(SelectMethodActivity.this,CodeVerificationActivity.class);
                startActivity(intent);
            }
        });
    }
}