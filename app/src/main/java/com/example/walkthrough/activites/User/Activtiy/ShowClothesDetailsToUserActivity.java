package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.AdapterClass.ClothesProductAdapter;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.User.fragments.adapterClasses.ShowClothesProductAdapter;
import com.example.walkthrough.databinding.ActivityShowClothesDetailsToUserBinding;
import com.example.walkthrough.databinding.ActivityUserCheckoutBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowClothesDetailsToUserActivity extends AppCompatActivity {
    private ActivityShowClothesDetailsToUserBinding binding;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowClothesDetailsToUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        dialog = new ProgressDialog(ShowClothesDetailsToUserActivity.this);
        dialog.setMessage("please wait...");
        dialog.show();
        Getlistofclothes();
    }
    private void Getlistofclothes() {
        ArrayList<AddCategoryProductModelClass> list;
        list = new ArrayList<>();
        ShowClothesProductAdapter adapters = new  ShowClothesProductAdapter(list,ShowClothesDetailsToUserActivity.this);
        binding.mostSearchedClothesRCV.setLayoutManager(new LinearLayoutManager(ShowClothesDetailsToUserActivity.this,LinearLayoutManager.VERTICAL,false));
        binding.mostSearchedClothesRCV.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("ViewProductClothes");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        AddCategoryProductModelClass obj = dataSnapshot.getValue(AddCategoryProductModelClass.class);
                        list.add(obj);
                    }
                    dialog.dismiss();
                    adapters.notifyDataSetChanged();
                }else{
                    dialog.dismiss();
                    Toast.makeText(ShowClothesDetailsToUserActivity.this, "No Product Yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(ShowClothesDetailsToUserActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}