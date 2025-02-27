package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorSelectMethodBinding;


public class TailorSelectMethodFragment extends Fragment {


    FragmentTailorSelectMethodBinding binding;

    public TailorSelectMethodFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentTailorSelectMethodBinding.inflate(inflater,container,false);

        binding.nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.backArrowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return binding.getRoot();


    }
}