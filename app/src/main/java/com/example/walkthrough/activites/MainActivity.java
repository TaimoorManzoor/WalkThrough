package com.example.walkthrough.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.SharedPrefPkg.PrefManager;
import com.example.walkthrough.activites.Clothes.ClothesProfileActivity;
import com.example.walkthrough.activites.Tailor.TailorProfileActivity;
import com.example.walkthrough.activites.User.UserProfileActivity;

import com.example.walkthrough.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    FirebaseAuth mAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Button user = findViewById(R.id.user);
        Button service = findViewById(R.id.service);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = binding.user.getText().toString();
                Intent intent = new Intent(MainActivity.this, SignInUserActivity.class);
                intent.putExtra("currentStatus", status);
                startActivity(intent);

            }
        });
        service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ServiceProviderActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        PrefManager prefManager1= new PrefManager(getApplicationContext());
        if (prefManager1.getCurrentstatus().equals("Tailor")){
            //step 1.
            Intent intent=new Intent(getApplicationContext(), TailorProfileActivity.class);
            startActivity(intent);
        }else if(prefManager1.getCurrentstatus().equals("User")) {
            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            startActivity(intent);
        }else if(prefManager1.getCurrentstatus().equals("Clothes")){
            Intent intent = new Intent(getApplicationContext(), ClothesProfileActivity.class);
            startActivity(intent);
        }
        // step 2
//        }else if (prefManager1.getCurrentstatus().equals("Admin")){
//            //step 2
//            Intent intent=new Intent(getApplicationContext(), AdminDashboard.class);
//            startActivity(intent);
//        }
    }
}