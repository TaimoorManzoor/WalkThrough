package com.example.walkthrough.activites.Clothes.bottomNavigationFragments;

import static android.app.Activity.RESULT_OK;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.model.AddClothesDetailToRealtym;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ClothesUpdateProfileFragment extends Fragment {

    String userUid;
    ImageView imageView;
    TextView edtxtname,edtxtPrice;
    Button btnupdate;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;
    private final int PICK_IMAGE_REQUEST = 22;
    private Uri filePath;

    StorageReference storageReference;
    FirebaseStorage storage;
    String imageurlupated;

    public ClothesUpdateProfileFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_update_profile, container, false);
        dialog = new ProgressDialog(requireActivity());
        dialog.setTitle("Please wait");
        IDs(view);
        SetDetails();
        ClickListener();
        return view;
    }
    private void IDs(View view) {
        imageView=view.findViewById(R.id.pickimageid);
        edtxtname=view.findViewById(R.id.ednameid);
        edtxtPrice=view.findViewById(R.id.edtypeid);
        btnupdate=view.findViewById(R.id.UpdateProfile);

    }
    private void ClickListener(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateprofileID();
                dialog = new ProgressDialog(getContext());
                dialog.setMessage("please wait...");
                dialog.show();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               PickImage();
            }
        });
    }
    private void PickImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode,
                resultCode,
                data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null
                && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),
                        filePath);
                imageView.setImageBitmap(bitmap);
                uploadimagetoFirebaseStorage();

            }

            catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(getContext(), "Please Select IMage", Toast.LENGTH_SHORT).show();
        }
    }
    private void uploadimagetoFirebaseStorage(){
        dialog.show();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        if (filePath != null) {
            StorageReference ref = storageReference.child("imagess/"
                    + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot)
                                {
                                    ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            imageurlupated=uri.toString();
                                            Log.d(TAG, "onSuccess: "+imageurlupated);
                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            DatabaseReference usersRef = database.getReference("ClothesProfile").child(
                                                    "Clothes").child(userUid);
                                            Map<String, Object> updates = new HashMap<>();
                                            updates.put("imageurl",imageurlupated);
                                            usersRef.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
//                                                    Log.d(TAG, "onComplete: "+Updated);
                                                    if (task.isSuccessful()){
                                                        requireActivity().runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                dialog.dismiss();
                                                            }
                                                        });
                                                    }
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getContext(),e.getMessage().toString(),Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e)
                        {
                            Toast.makeText(getContext(), "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    });
        }else{
            Toast.makeText(getContext(), "please Select the Image", Toast.LENGTH_SHORT).show();
        }
    }

    private void SetDetails(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userUid= user.getUid();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(
                        "ClothesProfile").child(
                        "Clothes").
                child(userUid.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    try {
                        AddClothesDetailToRealtym obj = snapshot.getValue(AddClothesDetailToRealtym.class);
                        Glide.with(getContext()).load(obj.getImageurl()).into(imageView);
                        edtxtname.setText(obj.getUsername());
                        edtxtPrice.setText(obj.getClothestype());
                    }
                    catch (Exception e)
                    {

                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void updateprofileID(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersRef = database.getReference("ClothesProfile")
                .child("Clothes")
                .child(userUid);
        Map<String, Object> updates = new HashMap<>();
        updates.put("username",edtxtname.getText().toString().trim());
        updates.put("clothestype",edtxtPrice.getText().toString().trim());

        if (imageurlupated!=null){
            updates.put("imageurl",imageurlupated);
            Glide.with(requireContext()).clear(imageView);
            Glide.with(requireContext())
                    .load(imageurlupated)
//                    .placeholder(R.drawable.placeholder_image)  // Add a placeholder drawable
//                    .error(R.drawable.error_image)  // Add an error drawable
                    .into(imageView);

        }
        usersRef.updateChildren(updates).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    dialog.dismiss();
//                    Glide.with(getContext()).load(imageurlupated).into(imageView);
                    Toast.makeText(requireContext(), "UpDated Sucessfuly", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });



    }
}