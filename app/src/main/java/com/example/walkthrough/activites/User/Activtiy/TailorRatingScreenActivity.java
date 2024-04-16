package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.User.UserDashboardActivity;
import com.example.walkthrough.activites.User.fragments.modelClasses.Ratingmodelclas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TailorRatingScreenActivity extends AppCompatActivity {

    RatingBar ratingBar;
    AppCompatButton sendReviewButton;
    String userID;
    String ratingnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_rating_screen);
        userID=getIntent().getStringExtra("tailorID");
        ratingBar = findViewById(R.id.ratingBar);
        sendReviewButton = findViewById(R.id.sendReviewBtn);

        sendReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userID!=null){
                    ratingnum= String.valueOf(ratingBar.getRating());
                    RatingTOdatabase();
                }else{
                    Toast.makeText(TailorRatingScreenActivity.this, "ID is null", Toast.LENGTH_SHORT).show();
                }

                }

        });
    }
    private void RatingTOdatabase(){
        Ratingmodelclas obj = new Ratingmodelclas(userID,ratingnum);
        FirebaseDatabase database;
        DatabaseReference reference;
        database=FirebaseDatabase.getInstance();
        reference=database.getReference("Rating");
        reference.child(userID).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                startActivity(new Intent(getApplicationContext(), UserDashboardActivity.class));
                Toast.makeText(TailorRatingScreenActivity.this, "thanku for the Rating", Toast.LENGTH_SHORT).show();
            }
        });
    }

}