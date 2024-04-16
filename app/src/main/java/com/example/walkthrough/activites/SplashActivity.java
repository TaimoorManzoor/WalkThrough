package com.example.walkthrough.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.walkthrough.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed((new Runnable(){
            @Override
            public void run() {

                TaskStackBuilder.create(SplashActivity.this)
                        .addNextIntentWithParentStack(new Intent(SplashActivity.this,MainActivity.class))
                        .addNextIntent(new Intent(SplashActivity.this,IntroActivity.class))
                        .startActivities();
                finish();
            }
        }),3000);
    }
}