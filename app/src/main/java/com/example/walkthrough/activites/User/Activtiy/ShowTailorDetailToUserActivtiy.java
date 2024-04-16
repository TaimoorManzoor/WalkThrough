package com.example.walkthrough.activites.User.Activtiy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;
import com.example.walkthrough.activites.User.UserDashboardActivity;
import com.example.walkthrough.activites.User.fragments.adapterClasses.AdapterImage;
import com.example.walkthrough.activites.User.fragments.adapterClasses.FetchAdapterImage;
import com.example.walkthrough.activites.User.fragments.adapterClasses.TailorListAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.ImageModelClass;
import com.example.walkthrough.databinding.ActivityShowTailorDetailToUserActivtiyBinding;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.example.walkthrough.model.AddUserDetailToRealtym;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowTailorDetailToUserActivtiy extends AppCompatActivity {
    private ActivityShowTailorDetailToUserActivtiyBinding binding;
    String tailorname, tailorprice, tailorimage, tailoruserID,phonenumber,tailoradd;
    private List<ImageModel> imageList = new ArrayList<>();
    String complainhere="923432171778";

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    String username, userImage;
    FirebaseUser currentUser;
    String uid;
    FetchAdapterImage adapterImage;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowTailorDetailToUserActivtiyBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        binding.BackimageviewID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserDashboardActivity.class));
            }
        });
        tailoruserID = getIntent().getStringExtra("userID");
        phonenumber = getIntent().getStringExtra("phone");
        tailoradd = getIntent().getStringExtra("add");
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference("RequestForBookTailor");



        getDetails();
        setDetail();
        TailorBook();
        getrating();

//        fetchimages();
        // recycler view
        fetchImageUrls();
    }

    private void getDetails() {
        if (currentUser != null) {
            uid = currentUser.getUid();
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference(
                            "CustomerProfile").
                    child(uid.toString());
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        AddUserDetailToRealtym obj = snapshot.getValue(AddUserDetailToRealtym.class);
                        username = obj.getUsername();
                        userImage = obj.getUserImage();
//                    Glide.with(getContext()).load(obj.getUserImage()).into(imageView);
//                    edtxtname.setText(obj.getUsername());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ShowTailorDetailToUserActivtiy.this, "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }

    }

    private void TailorBook() {
        binding.whatsppnumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("smsto:" +phonenumber);
                Intent whatsappIntent = new Intent(Intent.ACTION_SENDTO, uri);
                whatsappIntent.setPackage("com.whatsapp"); // Specify the package name

                // You can add extra text if needed
                // whatsappIntent.putExtra("sms_body", "Hello, this is a message from my app!");

                // Start the WhatsApp intent
                startActivity(whatsappIntent);
            }
        });

        binding.complainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("smsto:" +complainhere);
                Intent whatsappIntent = new Intent(Intent.ACTION_SENDTO, uri);
                whatsappIntent.setPackage("com.whatsapp"); // Specify the package name

                // You can add extra text if needed
                // whatsappIntent.putExtra("sms_body", "Hello, this is a message from my app!");

                // Start the WhatsApp intent
                startActivity(whatsappIntent);
            }
        });

        binding.hireID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent obj =new Intent(ShowTailorDetailToUserActivtiy.this, UserMeasurementActivity.class);
               obj.putExtra("currentUserID",uid);
               obj.putExtra("tailorID",tailoruserID);
               obj.putExtra("tailoradd",tailoradd);
               startActivity(obj);

            }

        });
    }

    private void setDetail() {
        tailorname = getIntent().getStringExtra("name");
        tailorprice = getIntent().getStringExtra("price");
        tailorimage = getIntent().getStringExtra("image");
        Glide.with(ShowTailorDetailToUserActivtiy.this).load(tailorimage).into(binding.tailorprofileID);
        binding.textView22.setText(tailorname);
        binding.textView24.setText(tailorprice);
    }
    private void fetchImageUrls() {
        if (tailoruserID != null) {
            DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("ImageUrls").child(tailoruserID);
            databaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    imageList.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            String imageUrl = dataSnapshot.getValue(String.class);
                            ImageModel imageModel = new ImageModel(imageUrl);
                            imageList.add(imageModel);
                        }
                        recyclerView = findViewById(R.id.recyclerviewID);
                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                        adapterImage = new FetchAdapterImage(getApplicationContext(), imageList);
                        recyclerView.setAdapter(adapterImage);

                        adapterImage.notifyDataSetChanged();
                    } else {

                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplicationContext(), "Failed to fetch image URLs", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(this, "Tailor Id is null", Toast.LENGTH_SHORT).show();
        }

    }
    private void getrating(){
        if (tailoruserID!=null){
            ref=firebaseDatabase.getReference(
                    "Rating").child(tailoruserID);
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String rating=snapshot.child("ratingnumber").getValue(String.class);
                        Toast.makeText(ShowTailorDetailToUserActivtiy.this, ""+rating, Toast.LENGTH_SHORT).show();
                        binding.tvratingiD.setText(rating);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(ShowTailorDetailToUserActivtiy.this, ""+ error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(),UserDashboardActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference userRequestsRef = FirebaseDatabase.getInstance()
                .getReference("UserSalaiRequests")
                .child(uid)
                .child("UserRequests")
                ;

        // Check if there is an existing request for the given tailor
        userRequestsRef.orderByChild("tailorReceiverID").equalTo(tailoruserID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // User has already sent a request to this tailor
                    binding.hireID.setVisibility(View.GONE);
                    Toast.makeText(ShowTailorDetailToUserActivtiy.this, "You have already sent a request to this tailor.", Toast.LENGTH_SHORT).show();
                } else {
                    // No existing request, proceed to send a new request

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
                Toast.makeText(ShowTailorDetailToUserActivtiy.this, "Error checking existing requests: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}




