package com.example.walkthrough.activites.Clothes.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.ClothesProfileActivity;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.databinding.ActivityClothesAddCategoryActivtiyBinding;
import com.example.walkthrough.model.AddClothesDetailToRealtym;
import com.example.walkthrough.model.ClothesProductModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClothesAddCategoryActivtiy extends AppCompatActivity {

    FirebaseStorage storage;
    Button btnupload, btnsaveid;
    String currentUser;
    EditText edsellerproductusername, eddes, edcategory, edcolor, edCount;
    private FirebaseAuth mAuth;
    ImageView imagepick, imagetwp, imagethree, imagefour,imagefive;
    Uri fileuri;
    FirebaseDatabase firebaseDatabase;

    private final int PICK_IMAGE_REQUEST = 22;

    String selleruserID, sellerproductusername, productimage, des, category, color, Count;
    ProgressDialog dialog;
    FirebaseAuth firebaseAuth;
    String Productid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_add_category_activtiy);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        dialog = new ProgressDialog(this);
        dialog.setTitle("Please wait");
        Ids();
        Clicklistener();
    }

    private void Ids() {
        imagepick = findViewById(R.id.imageviewoneID);
        imagetwp = findViewById(R.id.imagetwo);
        imagethree = findViewById(R.id.imagethree);
        imagefour = findViewById(R.id.imagefour);
        imagefive = findViewById(R.id.fiveimageID);
        edsellerproductusername = findViewById(R.id.ednameid);
        eddes = findViewById(R.id.eddesid);
        edcategory = findViewById(R.id.edcateid);
        edcolor = findViewById(R.id.edcolorid);
        edCount = findViewById(R.id.edcountid);
        btnupload = findViewById(R.id.saveid);
        btnsaveid = findViewById(R.id.pickIDbtn);

    }

    private void Clicklistener() {
        btnsaveid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickImages();
            }
        });
        btnupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sellerproductusername = edsellerproductusername.getText().toString().trim();
                category = edcategory.getText().toString().trim();
                color = edcolor.getText().toString().trim();
                Count = edCount.getText().toString().trim();
                des = eddes.getText().toString().trim();
                if (sellerproductusername.isEmpty()
                ) {
                    Toast.makeText(getApplicationContext(), "Please Fill Details", Toast.LENGTH_SHORT).show();
                } else {
                    AddProductDetails();
                    dialog = new ProgressDialog(ClothesAddCategoryActivtiy.this);
                    dialog.setMessage("please wait...");


                }
            }
        });
    }

    private void pickImages() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data != null) {
                handleSelectedImages(data);
            }
        }
    }

    private void handleSelectedImages(Intent data) {
        // List to store image URIs
        List<Uri> imageUris = new ArrayList<>();

        if (data.getClipData() != null) {
            int itemCount = data.getClipData().getItemCount();

            for (int i = 0; i < itemCount && i < 5; i++) {
                Uri imageUri = data.getClipData().getItemAt(i).getUri();
                imageUris.add(imageUri);
                // Load the image into the corresponding ImageView
                switch (i) {
                    case 0:
                        imagepick.setImageURI(imageUri);
                        break;
                    case 1:
                        imagetwp.setImageURI(imageUri);
                        break;
                    case 2:
                        imagethree.setImageURI(imageUri);
                        break;
                    case 3:
                        imagefour.setImageURI(imageUri);
                        break;
                    case 4:
                        imagefive.setImageURI(imageUri);
                        break;
                }
            }

            // Upload images to Firebase Storage and save URLs to Realtime Database
            uploadImages(imageUris);
        }

    }

    private void uploadImages(List<Uri> imageUris) {
        String uid = firebaseAuth.getCurrentUser().getUid();

        for (int i = 0; i < imageUris.size(); i++) {
            Uri imageUri = imageUris.get(i);
            String imageName = "image" + i + ".jpg";

            StorageReference storageReference = storage.getReference("Clothes Product Images").child(uid).child(imageName);

            storageReference.putFile(imageUri)
                    .addOnSuccessListener(taskSnapshot -> {
                        // Image uploaded successfully, get the download URL
                        storageReference.getDownloadUrl().addOnSuccessListener(uri -> {
                            String imageUrl = uri.toString();
                            productimage=uri.toString();
                            // Save the image URL to the Realtime Database
                            saveImageUrlToDatabase(imageUrl);
                        });
                    })
                    .addOnFailureListener(e -> {
                        // Handle unsuccessful uploads
                        Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show();
                    });
        }
    }

    private void saveImageUrlToDatabase(String imageUrl) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            selleruserID = user.getUid();

            // Create the database reference for the seller's images
            DatabaseReference sellerReference = firebaseDatabase.getReference("Clothes Product Images").child(selleruserID);

            // Generate a unique product ID
          String  Productid = sellerReference.push().getKey();

            // Store image URL directly under product ID with the unique key
//            DatabaseReference imageReference = sellerReference.child(Productid).child(imageKey);

            // Store the image URL under the generated key
            sellerReference.child(Productid).setValue(imageUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        // Image URL saved successfully
                        // Handle success here, e.g., proceed to next activity
                    } else {
                        Toast.makeText(ClothesAddCategoryActivtiy.this, "Failed to save image URL", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void AddProductDetails() {
        if (selleruserID!=null){
            dialog.show();
            DatabaseReference databaseReference = firebaseDatabase.getReference("Clothes Product Details").child(selleruserID);
            String  Productid = databaseReference.push().getKey().toString();
            AddCategoryProductModelClass obj =new AddCategoryProductModelClass(selleruserID,Productid,sellerproductusername,productimage,des,category,color,Count);
            databaseReference.child(Productid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){
                        dialog.hide();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("Clothes Product Details To User");
                        databaseReference.child(Productid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Intent obj = new Intent(ClothesAddCategoryActivtiy.this, ClothesDashboardTwoActivity.class);
                                    startActivity(obj);
                                    Toast.makeText(ClothesAddCategoryActivtiy.this, "Data is uploaded", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });

        }else{
            Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
        }
        }

}