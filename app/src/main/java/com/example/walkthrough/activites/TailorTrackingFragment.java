package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorTrackingBinding;


public class TailorTrackingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    FragmentTailorTrackingBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TailorTrackingFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TailorTrackingFragment newInstance(String param1, String param2) {
        TailorTrackingFragment fragment = new TailorTrackingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        binding=FragmentTailorTrackingBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }
}