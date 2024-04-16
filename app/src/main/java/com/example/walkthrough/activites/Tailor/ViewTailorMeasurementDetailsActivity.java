package com.example.walkthrough.activites.Tailor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;
import com.example.walkthrough.activites.User.fragments.adapterClasses.DesignAdapterImage;
import com.example.walkthrough.databinding.ActivityViewTailorMeasurementDetailsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewTailorMeasurementDetailsActivity extends AppCompatActivity {
    private ActivityViewTailorMeasurementDetailsBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference ref;
    String userID,Stomach,FrontNeck,SleeveNeck,Waist,BodyWidth,FullLength;
    private List<Uri> selectedImages = new ArrayList<>();
    private List<ImageModel> imageList = new ArrayList<>();
    RecyclerView recyclerView;
    DesignAdapterImage imageAdapter;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate  (savedInstanceState);
        binding = ActivityViewTailorMeasurementDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        userID=getIntent().getStringExtra("id");
        //
        recyclerView = view.findViewById(R.id.designID2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        imageAdapter =new DesignAdapterImage(imageList,getApplicationContext());
        recyclerView.setAdapter(imageAdapter);
        getMeasurment();
        click();
        fetchImageUrls();
    }

    private void click(){
        binding.appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewTailorMeasurementDetailsActivity.this,TailerDashboardActivity.class));
            }
        });
    }
    private void getMeasurment(){
        auth=FirebaseAuth.getInstance();
        FirebaseUser user= auth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        if (user!=null){
            uid=user.getUid();
            ref=database.getReference(
                    "TailorMeasurement").child(uid);
            ref.child(userID).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                       String Shoulder=snapshot.child("shoulder").getValue(String.class);
                        BodyWidth=snapshot.child("bodyWidth").getValue().toString();
                        FrontNeck=snapshot.child("frontNeck").getValue().toString();
                        FullLength=snapshot.child("fullLength").getValue().toString();
                        SleeveNeck=snapshot.child("sleeveNeck").getValue().toString();
                        Stomach=snapshot.child("stomach").getValue().toString();
                        Waist=snapshot.child("waist").getValue().toString();
                        binding.editText.setText(Shoulder);
                        binding.editText2.setText(Stomach);
                        binding.editText3.setText(FrontNeck);
                        binding.editText4.setText(SleeveNeck);
                        binding.editText5.setText(Waist);
                        binding.editText6.setText(BodyWidth);
                        binding.editText7.setText(FullLength);
                        Toast.makeText(ViewTailorMeasurementDetailsActivity.this, "Data is exists "+Shoulder, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ViewTailorMeasurementDetailsActivity.this, "Data not found"+ error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }

    }
    private void fetchImageUrls() {

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("TailorDesignImageUrls").child(uid);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                imageList.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        String imageUrl = dataSnapshot.getValue(String.class);
                        ImageModel imageModel = new ImageModel(imageUrl);
                        imageList.add(imageModel);
                    }

                    imageAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Failed to fetch image URLs", Toast.LENGTH_SHORT).show();
            }
        });
    }
}