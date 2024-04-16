package com.example.walkthrough.activites;

import android.content.Context;
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
import com.example.walkthrough.adapter.checkoutAdapter;

public class CheckoutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String [] price_checkout={"245.00 Rs","245.00 Rs","245.00 Rs","245.00 Rs"};
    String [] name_checkout={"Lone clothes","Lone clothes","Lone clothes","Lone clothes"};
    int [] image_checkout={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};

    Button confirm;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        View view=inflater.inflate(R.layout.fragment_checkout, container, false);
        RecyclerView checkout_recycle_view=view.findViewById(R.id.checkout_recycle_view);
        checkoutAdapter checkoutadapter=new checkoutAdapter(image_checkout,name_checkout,price_checkout);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        checkout_recycle_view.setLayoutManager(linearLayoutManager);
        checkout_recycle_view.setAdapter(checkoutadapter);

        confirm=view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment frag = new SummaryFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.userhome, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}