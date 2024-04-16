package com.example.walkthrough.activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.CartAdapter;


public class CartFragment extends Fragment {
    String [] cart_size={"7 meter size","7 meter size","7 meter size","7 meter size"};
    String [] name_cloth={"Lone clothes","Lone clothes","Lone clothes","Lone clothes"};
    int [] image_cloth={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};

Button findtailor;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        View view=inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView cart_recycleView=view.findViewById(R.id.cart_recycleView);
        CartAdapter cartadapter=new CartAdapter(cart_size,image_cloth,name_cloth);
        cart_recycleView.setLayoutManager(new LinearLayoutManager(context));
        cart_recycleView.setAdapter(cartadapter);

        Button findtailor=view.findViewById(R.id.findtailor);
        Button proceedtocheckout=view.findViewById(R.id.proceedtocheckout);
        findtailor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the FragmentManager instance.
                FragmentManager fragmentManager = getFragmentManager();

                // Begin a fragment transaction.
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the new fragment.
                transaction.add(R.id.userhome, new TailorFragment());

                // Commit the fragment transaction.
                transaction.commit();
            }
        });
        proceedtocheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the FragmentManager instance.
                FragmentManager fragmentManager = getFragmentManager();

                // Begin a fragment transaction.
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Replace the current fragment with the new fragment.
                transaction.add(R.id.userhome, new CheckoutFragment());

                // Commit the fragment transaction.
                transaction.commit();
            }

        });

        return view;



    }
}