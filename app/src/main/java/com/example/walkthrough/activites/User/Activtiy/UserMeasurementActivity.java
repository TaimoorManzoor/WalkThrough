package com.example.walkthrough.activites.User.Activtiy;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;
import com.example.walkthrough.activites.User.UserDashboardActivity;
import com.example.walkthrough.activites.User.UserProfileActivity;
import com.example.walkthrough.activites.User.fragments.adapterClasses.DesignAdapterImage;
import com.example.walkthrough.activites.User.fragments.adapterClasses.FetchAdapterImage;
import com.example.walkthrough.activites.User.fragments.adapterClasses.MeasurementAdapterImage;
import com.example.walkthrough.activites.User.fragments.modelClasses.UsermeasurementModelClass;
import com.example.walkthrough.databinding.ActivityShowTailorDetailToUserActivtiyBinding;
import com.example.walkthrough.databinding.ActivityUserMeasurementBinding;
import com.example.walkthrough.model.AddUserDetailToRealtym;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserMeasurementActivity extends AppCompatActivity {
    private ActivityUserMeasurementBinding binding;
    String Shoulder,Stomach,FrontNeck,SleeveNeck,Waist,BodyWidth,FullLength,tailoruserID,currentuserID,tailoraddress;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference ref1;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    ProgressDialog dialog;
    private List<Uri> selectedImages = new ArrayList<>();
    private List<ImageModel> imageList = new ArrayList<>();
    RecyclerView recyclerView;
    MeasurementAdapterImage imageAdapter;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserMeasurementBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        tailoruserID=getIntent().getStringExtra("tailorID");
        currentuserID=getIntent().getStringExtra("currentUserID");
        tailoraddress=getIntent().getStringExtra("tailoradd");
        //
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        ///
        dialog=new ProgressDialog(UserMeasurementActivity.this);
        dialog.setTitle("please wait");
        recyclerView = view.findViewById(R.id.designID);




        Clicklistener();

    }
    private void Clicklistener(){
        binding.appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Shoulder= binding.editText.getText().toString().trim();
                Stomach=binding.editText2.getText().toString().trim();
                FrontNeck=binding.editText3.getText().toString().trim();
                SleeveNeck=binding.editText4.getText().toString().trim();
                Waist=binding.editText5.getText().toString().trim();
                BodyWidth=binding.editText6.getText().toString().trim();
                FullLength=binding.editText7.getText().toString().trim();

                UploadUserMeasurementActivity();

            }
        });
        binding.appCompatButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent obj =new Intent(UserMeasurementActivity.this,UserCheckoutActivity.class);
                obj.putExtra("tailorid",tailoruserID);
                obj.putExtra("userid",currentuserID);
                obj.putExtra("addresses",tailoraddress);
                startActivity(obj);
            }
        });
        binding.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
    }
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");

        // Start the image picker activity
        pickImages.launch(intent);
    }
    private final ActivityResultLauncher<Intent> pickImages = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    handleImageSelection(result.getData());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false));
                    imageAdapter = new MeasurementAdapterImage(selectedImages,this);
                    recyclerView.setAdapter(imageAdapter);
                    imageAdapter.notifyDataSetChanged();
                }
            }
    );
    private void handleImageSelection(Intent data) {
        ClipData clipData = data.getClipData();

        if (clipData != null) {
            // Multiple images selected
            for (int i = 0; i < clipData.getItemCount(); i++) {
                Uri imageUri = clipData.getItemAt(i).getUri();
                selectedImages.add(imageUri);
            }
        } else {
            // Single image selected
            Uri imageUri = data.getData();
            selectedImages.add(imageUri);
        }
//        if (imageAdapter == null) {
//
//
//        } else {
//
//        }

        // Now, you have the list of selected images in the "selectedImages" list.
        // You can proceed to upload them to Firebase Storage and save the URLs in the Realtime Database.

    }
    private void uploadImages() {
        dialog.show();


        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("TailorDesignImageUrls").child(tailoruserID);

        for (Uri imageUri : selectedImages) {
            String imageName = UUID.randomUUID().toString();
            StorageReference imageRef = storageReference.child("images/" + imageName);

            imageRef.putFile(imageUri)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            imageRef.getDownloadUrl().addOnSuccessListener(downloadUrl -> {
                                String imageUrl = downloadUrl.toString();
                                saveImageUrlToDatabase(imageUrl, databaseRef);
                            });
                        } else {
                            dialog.hide();

                            Toast.makeText(getApplicationContext(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void saveImageUrlToDatabase(String imageUrl, DatabaseReference databaseRef) {
        String key = databaseRef.push().getKey();
        if (key != null) {
            databaseRef.child(key).setValue(imageUrl)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            dialog.hide();
                            DatabaseReference Ref = FirebaseDatabase.getInstance().getReference("UserDesignImageUrls").child(currentuserID);
                            Ref.child(key).setValue(imageUrl);
//                            Toast.makeText(getApplicationContext(), "Image URL saved successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.hide();
                            Toast.makeText(getApplicationContext(), "Failed to save image URL", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }



    private void UploadUserMeasurementActivity(){
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        ref1=database.getReference("TailorMeasurement");
        UsermeasurementModelClass obj =new UsermeasurementModelClass(Shoulder,Stomach,FrontNeck,SleeveNeck,Waist,BodyWidth,FullLength,tailoruserID,currentuserID);
        ref1.child(tailoruserID).child(currentuserID).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    uploadImages();
                    DatabaseReference refuser=database.getReference("UserMeasurement");
                    refuser.child(currentuserID).child(tailoruserID).setValue(obj);
                    Toast.makeText(UserMeasurementActivity.this, "Go to Cart Now", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });


    }
//    private void fetchImageUrls() {
//
//        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("UserDesignImageUrls").child(currentuserID);
//        databaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                imageList.clear();
//                if (snapshot.exists()){
//                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                        String imageUrl = dataSnapshot.getValue(String.class);
//                        ImageModel imageModel = new ImageModel(imageUrl);
//                        imageList.add(imageModel);
//                    }
//
//                    imageAdapter.notifyDataSetChanged();
//                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                Toast.makeText(getApplicationContext(), "Failed to fetch image URLs", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}