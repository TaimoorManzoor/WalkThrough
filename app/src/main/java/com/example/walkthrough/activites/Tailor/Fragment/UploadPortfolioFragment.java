package com.example.walkthrough.activites.Tailor.Fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.ModelClass.ImageModel;
import com.example.walkthrough.activites.Tailor.TailerDashboardActivity;
import com.example.walkthrough.activites.User.fragments.adapterClasses.AdapterImage;
import com.example.walkthrough.activites.User.fragments.adapterClasses.FetchAdapterImage;
import com.example.walkthrough.activites.User.fragments.modelClasses.ImageModelClass;
import com.example.walkthrough.databinding.FragmentNewOrderTBinding;
import com.example.walkthrough.databinding.FragmentUploadPortfolioBinding;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UploadPortfolioFragment extends Fragment {
    private FragmentUploadPortfolioBinding binding;
    private FirebaseAuth mAuth;
    String userUid;
    int count =0;

    private List<Uri> selectedImages = new ArrayList<>();

    private FirebaseStorage storage;
    private StorageReference storageReference;
    ProgressDialog dialog;
    private RecyclerView recyclerView;
    private FetchAdapterImage imageAdapter;
    private List<ImageModel> imageList = new ArrayList<>();

    public UploadPortfolioFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUploadPortfolioBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        // recycler view
        recyclerView = view.findViewById(R.id.recyclerviewID);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        imageAdapter = new FetchAdapterImage(getContext(), imageList);
        recyclerView.setAdapter(imageAdapter);


        // dialog
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Uploading Images...");
        SetDetails();
        fetchImageUrls();

//        Show();

        binding.pickID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });


        return view;
    }
    private void SetDetails(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userUid= user.getUid();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(
                        "TailorProfile").child(
                        "Tailor").
                child(userUid.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    AddTailorDetailToRealtym obj=snapshot.getValue(AddTailorDetailToRealtym.class);
                    Glide.with(requireContext()).load(obj.getImageurl()).into(binding.tailorprofileID);
                    binding.textView22.setText(obj.getUsername());
                    binding.textView24.setText(obj.getTailorprice());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
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

        // Now, you have the list of selected images in the "selectedImages" list.
        // You can proceed to upload them to Firebase Storage and save the URLs in the Realtime Database.
        uploadImages(selectedImages, count);
    }
    private void uploadImages(List<Uri> selectedImages, int index) {

        dialog.show();
//      FirebaseUser user = mAuth.getCurrentUser();
//      String userUid = user != null ? user.getUid() : "";
        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("ImageUrls").child(userUid);

        // for (Uri imageUri : selectedImages) {
        Uri imageUri = selectedImages.get(index);
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
                        Toast.makeText(requireContext(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                });



        count++;
    }

    private void saveImageUrlToDatabase(String imageUrl, DatabaseReference databaseRef) {
        String key = databaseRef.push().getKey();
        if (key != null) {
            databaseRef.child(key).setValue(imageUrl)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            dialog.hide();
                            Toast.makeText(requireContext(), "Image URL saved successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            dialog.hide();
                            Toast.makeText(requireContext(), "Failed to save image URL", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }
    private void fetchImageUrls() {

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference("ImageUrls").child(userUid);
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
                Toast.makeText(requireContext(), "Failed to fetch image URLs", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




















