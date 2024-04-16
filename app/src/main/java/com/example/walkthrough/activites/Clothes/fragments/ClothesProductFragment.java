package com.example.walkthrough.activites.Clothes.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.Activities.ClothesAddCategoryActivtiy;
import com.example.walkthrough.activites.Clothes.AdapterClass.ClothesProductAdapter;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.Clothes.ModelClass.ClothesProductModel;
import com.example.walkthrough.activites.Clothes.bottomNavigationFragments.NewClothesHomeFragment;
import com.example.walkthrough.activites.Tailor.adapterClasses.TailorOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.BottomNavigation.HomeFragment;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.databinding.FragmentClothesProduct2Binding;
import com.example.walkthrough.databinding.FragmentClothesProductBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClothesProductFragment extends Fragment {
    private FragmentClothesProduct2Binding binding;
    private ClothesProductAdapter adapter;
    private ArrayList<AddCategoryProductModelClass> data;
    ProgressDialog dialog;
    FirebaseAuth mauth;

    public ClothesProductFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentClothesProduct2Binding.inflate(inflater,container,false);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("please wait...");
        dialog.show();
        binding.allProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ClothesAddCategoryActivtiy.class));
            }
        });
        VeiwProduct();
        return binding.getRoot();
    }
    private void VeiwProduct(){
        mauth=FirebaseAuth.getInstance();
        FirebaseUser user =mauth.getCurrentUser();
        if (user!=null){
            String uid=user.getUid();
            data = new ArrayList<>();
            adapter = new ClothesProductAdapter(data,getContext());
            binding.clothesProductRCV.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.clothesProductRCV.setAdapter(adapter);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference(
                            "Clothes Product Details")
                    .child(
                    uid);

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    data.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            AddCategoryProductModelClass obj = dataSnapshot.getValue(AddCategoryProductModelClass.class);
                            data.add(obj);
                        }
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }else{
                        dialog.dismiss();
                        Toast.makeText(getContext(), "No Product Yet", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }
        binding.searchViewClothesProduct.clearFocus();
        binding.searchViewClothesProduct.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Filterlist(newText);
                return true;
            }
        });
    }
    private void Filterlist(String Text) {
        List<AddCategoryProductModelClass> filterlist=new ArrayList<>();
        for (AddCategoryProductModelClass item: data){
            if(item.getSellerproductusername().toLowerCase().contains(Text.toLowerCase(Locale.ROOT))){
                filterlist.add(item);
            }
        }
        if (filterlist.isEmpty())
        {
            Toast.makeText(getContext(), "not Found", Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilteredlist(filterlist);
        }
    }
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Bundle bundle=getArguments();
            Fragment fragment=new NewClothesHomeFragment();
            fragment.setArguments(bundle);
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container,fragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    };

    // Register the callback in the onCreate() method
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }

    // Unregister the callback in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        onBackPressedCallback.remove();
    }

}