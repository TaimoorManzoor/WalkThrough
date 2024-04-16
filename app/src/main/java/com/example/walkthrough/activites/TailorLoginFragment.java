package com.example.walkthrough.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorLoginBinding;


public class TailorLoginFragment extends Fragment {
    ServiceProviderActivity serviceProviderActivity;
    FragmentTailorLoginBinding binding;
    public TailorLoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentTailorLoginBinding.inflate(inflater,container,false);
        binding.registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
        serviceProviderActivity=(ServiceProviderActivity)getActivity();
        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(getActivity(),TailorHomeActivity.class);
//                startActivity(intent);

            }
        });
        return binding.getRoot();
    }
}