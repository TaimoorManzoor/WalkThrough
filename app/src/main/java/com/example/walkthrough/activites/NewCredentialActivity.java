package com.example.walkthrough.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.walkthrough.R;

public class NewCredentialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_credential);
        ImageView backArrowImage=findViewById(R.id.backArrowImage);
        Button update_btn=findViewById(R.id.update_btn);

        backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(NewCredentialActivity.this,CodeVerificationActivity.class);
                startActivity(intent);
            }
        });
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(NewCredentialActivity.this,PasswordUpdateActivity.class);
                startActivity(intent);
            }
        });
    }
}