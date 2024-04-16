package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorAccountBinding;


public class TailorAccountFragment extends Fragment {

    FragmentTailorAccountBinding binding;


    public TailorAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Context context=getContext();
        binding=FragmentTailorAccountBinding.inflate(inflater, container,false);
        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Information is Updated",Toast.LENGTH_LONG).show();
            }
        });

        return binding.getRoot();
    }
}