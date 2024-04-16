package com.example.walkthrough.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.ActivityServiceProviderBinding;

public class ServiceProviderActivity extends AppCompatActivity {

    ActivityServiceProviderBinding providerBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        providerBinding=ActivityServiceProviderBinding.inflate(getLayoutInflater());
        setContentView(providerBinding.getRoot());



       Clicklistener();


    }
    private void Clicklistener(){
        providerBinding.tailor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status=providerBinding.tailor.getText().toString();
                Intent intent =new Intent(ServiceProviderActivity.this,TailorSignInActivtiy.class);
                intent.putExtra("tailorcurrentStatus",status);
                startActivity(intent);
            }
        });
        providerBinding.clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status=providerBinding.clothes.getText().toString();
                Intent intent =new Intent(ServiceProviderActivity.this,ClothesSignInActivity.class);
                intent.putExtra("clothescurrentStatus",status);
                startActivity(intent);
            }
        });
    }
}