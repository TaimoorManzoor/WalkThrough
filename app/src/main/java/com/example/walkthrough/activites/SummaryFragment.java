package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.checkoutAdapter;

public class SummaryFragment extends Fragment {
    String [] price_checkout={"245.00 Rs","245.00 Rs","245.00 Rs","245.00 Rs"};
    String [] name_checkout={"Lone clothes","Lone clothes","Lone clothes","Lone clothes"};
    int [] image_checkout={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};

    Button continueshopping;

    // TODO: Rename and change types of parameters


    public SummaryFragment() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        View view= inflater.inflate(R.layout.fragment_summary, container, false);
        RecyclerView checkout_recycle_view=view.findViewById(R.id.checkout_recycle_view);
        checkoutAdapter checkoutadapter=new checkoutAdapter(image_checkout,name_checkout,price_checkout);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);

        checkout_recycle_view.setLayoutManager(linearLayoutManager);
        checkout_recycle_view.setAdapter(checkoutadapter);

        continueshopping=view.findViewById(R.id.continueshopping);
        continueshopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AppCompatActivity appCompatActivity=(AppCompatActivity)view.getContext();
                appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.userhome, new HomeFragment()).addToBackStack(null).commit();
            }
        });

        return view;
    }
}