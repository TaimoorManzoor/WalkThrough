package com.example.walkthrough.activites;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorRegisterBinding;


public class TailorRegisterFragment extends Fragment {

   FragmentTailorRegisterBinding binding;
   ServiceProviderActivity serviceProviderActivity;
    public TailorRegisterFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentTailorRegisterBinding.inflate(inflater,container,false);

        binding.signInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.backArrowImage.setOnClickListener(new View.OnClickListener() {
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



        return  binding.getRoot();
    }
}