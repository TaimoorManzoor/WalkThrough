package com.example.walkthrough.activites.User.Activtiy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.IntroActivity;
import com.example.walkthrough.activites.SplashActivity;
import com.example.walkthrough.activites.User.UserDashboardActivity;
import com.example.walkthrough.databinding.ActivityAnimationPaymentBinding;
import com.example.walkthrough.databinding.ActivityBookClothesUserBinding;

public class AnimationPaymentActivity extends AppCompatActivity {
    private ActivityAnimationPaymentBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAnimationPaymentBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.animationid.playAnimation();
        new Handler().postDelayed((new Runnable(){
            @Override
            public void run() {

                Intent intent=new Intent(AnimationPaymentActivity.this, UserDashboardActivity.class);
                startActivity(intent);
                finish();

            }
        }),5000);
        Toast.makeText(this, "transfer Payment", Toast.LENGTH_SHORT).show();
    }
}