package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;
import com.example.walkthrough.activites.User.fragments.adapterClasses.ProductFetchAdapterImage;
import com.example.walkthrough.databinding.ActivityBookClothesUserBinding;
import com.example.walkthrough.model.AddUserDetailToRealtym;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BookClothesUserActivity extends AppCompatActivity {
    private ActivityBookClothesUserBinding binding;
    String productname,sellerid,productimage,productprice,productdes,ProductID;
    FirebaseAuth mAuth;
    String username,userImage;
    FirebaseUser currentUser;
    String uid;

    int counter=1;
    private List<ImageModel> imageList = new ArrayList<>();
    RecyclerView recyclerView;
    private ProductFetchAdapterImage imageAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBookClothesUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
        imageAdapter = new ProductFetchAdapterImage(getApplicationContext(),imageList);
        recyclerView.setAdapter(imageAdapter);
        mAuth= FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        setDetails();
        getDetails();
        fetchImageUrls();
        setContentView(view);

    }
    private void getDetails(){
        if (currentUser!=null){
            uid = currentUser.getUid();
            FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
            DatabaseReference ref= firebaseDatabase.getReference(
                            "CustomerProfile").
                    child(uid.toString());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        AddUserDetailToRealtym obj=snapshot.getValue(AddUserDetailToRealtym.class);
                        username=obj.getUsername();
                        userImage=obj.getUserImage();
//                    Glide.with(getContext()).load(obj.getUserImage()).into(imageView);
//                    edtxtname.setText(obj.getUsername());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(BookClothesUserActivity.this, "Error"+error, Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }

    }
    private void setDetails(){
        productname=getIntent().getStringExtra("name");
        sellerid=getIntent().getStringExtra("sellerid");
        ProductID=getIntent().getStringExtra("productID");
        productimage=getIntent().getStringExtra("image");
        productprice=getIntent().getStringExtra("price");
        productdes=getIntent().getStringExtra("des");
        binding.titleid.setText(productname);
        binding.priceid.setText(productprice);
        binding.description.setText(productdes);
//        Glide.with(BookClothesUserActivity.this).load(productimage).into(binding.imageView9);
        binding.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter>1){
                    counter--;
                    binding.textView40.setText(String.valueOf(counter));
                }
            }
        });
        binding.imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                binding.textView40.setText(String.valueOf(counter));
            }
        });
        binding.clothescartid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cprice=Integer.parseInt(productprice);
                int totalprice=cprice*counter;
                String stringtotailprice=String.valueOf(totalprice);
                Intent obj = new Intent(BookClothesUserActivity.this, CartClothesScreenActivity.class);
                obj.putExtra("productimage", productimage);
                obj.putExtra("productname", productname);
                obj.putExtra("userId", uid);
                obj.putExtra("productprice", stringtotailprice);
                obj.putExtra("SellerID", sellerid);
                obj.putExtra("ProductID", ProductID);
                obj.putExtra("ProductCount", String.valueOf(counter));
                startActivity(obj);
            }
        });
    }
    private void fetchImageUrls() {

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("Clothes Product Images").child(sellerid);
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