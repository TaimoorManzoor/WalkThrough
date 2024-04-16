package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorAccountPaymentDetailBinding;


public class TailorAccountPaymentDetailFragment extends Fragment {

    FragmentTailorAccountPaymentDetailBinding binding;


    public TailorAccountPaymentDetailFragment() {
        // Required empty public constructor
    }



    public static TailorAccountPaymentDetailFragment newInstance(String param1, String param2) {
        TailorAccountPaymentDetailFragment fragment = new TailorAccountPaymentDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context=getContext();
        binding=FragmentTailorAccountPaymentDetailBinding.inflate(inflater,container,false);

        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Information is Updated",Toast.LENGTH_LONG).show();
            }
        });
        return binding.getRoot();

    }
}