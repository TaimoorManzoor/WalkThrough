package com.example.walkthrough.activites;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.SharedPrefPkg.PrefManager;
import com.example.walkthrough.activites.Clothes.ClothesProfileActivity;
import com.example.walkthrough.activites.Tailor.TailorProfileActivity;
import com.example.walkthrough.activites.User.UserProfileActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.github.dreierf.materialintroscreen.MaterialIntroActivity;
import io.github.dreierf.materialintroscreen.MessageButtonBehaviour;
import io.github.dreierf.materialintroscreen.SlideFragmentBuilder;

public class IntroActivity extends MaterialIntroActivity {




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addSlide(new SlideFragmentBuilder()
                .title("Welcome To EZ Silai")
                .description("Step into convenience with EZ Silai, your go-to solution for hassle-free and easy sewing experiences.")
                .image(R.drawable.splash_screen1)
                .buttonsColor(R.color.lightblue)
                .backgroundColor(R.color.darkblue)
                .build()
        );
        addSlide(new SlideFragmentBuilder()
                .title("Stitching Redefined")
                .description("Say Farewell to Traditional Processes and Embrace Effortless Sewing Innovation with EZ Silai")
                .image(R.drawable.splash_screen2)
                .buttonsColor(R.color.lightblue)
                .backgroundColor(R.color.darkblue)
                .build()
        );
        addSlide(new SlideFragmentBuilder()
                .title("Keep Tracks Of Your Orders")
                .description("Effortlessly manage your purchases with our intuitive platform, ensuring seamless tracking and timely updates on orders.")
                .image(R.drawable.splash_screen3)
                .buttonsColor(R.color.lightblue)
                .backgroundColor(R.color.darkblue)
                .build());

    }

    public void role() {
        Intent intent = new Intent(IntroActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //
    @Override
    protected void onStart() {
        super.onStart();
        PrefManager prefManager1 = new PrefManager(getApplicationContext());
        if (prefManager1.getCurrentstatus().equals("Tailor")) {
            //step 1.
            Intent intent = new Intent(getApplicationContext(), TailorProfileActivity.class);
            startActivity(intent);
        } else if (prefManager1.getCurrentstatus().equals("User")) {
            Intent intent = new Intent(getApplicationContext(), UserProfileActivity.class);
            startActivity(intent);
        } else if (prefManager1.getCurrentstatus().equals("Clothes")) {
            Intent intent = new Intent(getApplicationContext(), ClothesProfileActivity.class);
            startActivity(intent);
        }
//         step 2
//        }else if (prefManager1.getCurrentstatus().equals("Admin")){
//            //step 2
//            Intent intent=new Intent(getApplicationContext(), AdminDashboard.class);
//            startActivity(intent);
//        }
    }
}


