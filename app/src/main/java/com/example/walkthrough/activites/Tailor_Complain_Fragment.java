package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorComplainBinding;


public class Tailor_Complain_Fragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentTailorComplainBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Tailor_Complain_Fragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentTailorComplainBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}