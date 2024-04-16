package com.example.walkthrough.activites.User.fragments.DrawerNavigation;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.User.fragments.adapterClasses.ShowClothesProductAdapter;
import com.example.walkthrough.databinding.FragmentListofClothesToUserBinding;
import com.example.walkthrough.databinding.FragmentTailorsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ListofClothesToUserFragment extends Fragment {


    private FragmentListofClothesToUserBinding binding;
    ProgressDialog dialog;
    public ListofClothesToUserFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListofClothesToUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dialog = new ProgressDialog(requireActivity());
        dialog.setMessage("please wait...");
        dialog.show();
        Getlistofclothes(view);
        return view;
    }
    private void Getlistofclothes(View view) {
        ArrayList<AddCategoryProductModelClass> list;
        list = new ArrayList<>();
        ShowClothesProductAdapter adapters = new  ShowClothesProductAdapter(list,getContext());
        binding.mostSearchedClothesRCV.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        binding.mostSearchedClothesRCV.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("Clothes Product Details To User");

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
                    try{
                        dialog.dismiss();
                        Toast.makeText(getContext(), "No Product Yet", Toast.LENGTH_SHORT).show();
                }
                    catch (Exception e)
                    {
                        dialog.dismiss();

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
}