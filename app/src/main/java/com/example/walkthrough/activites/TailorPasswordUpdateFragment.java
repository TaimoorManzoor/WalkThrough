package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorNewCredentialBinding;
import com.example.walkthrough.databinding.FragmentTailorPasswordUpdateBinding;


public class TailorPasswordUpdateFragment extends Fragment {
FragmentTailorPasswordUpdateBinding binding;

    public TailorPasswordUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentTailorPasswordUpdateBinding.inflate(inflater,container,false);
        binding.backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return binding.getRoot();
    }
}