package com.example.walkthrough.activites.Clothes.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.walkthrough.databinding.FragmentRequestUserBinding;
import com.google.firebase.auth.FirebaseAuth;


public class ClothesFragmentRequests extends Fragment {

    private FragmentRequestUserBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    public ClothesFragmentRequests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("please wait...");
        dialog.show();
//        GetlistofTailor(view);
        return  view;

    }

}