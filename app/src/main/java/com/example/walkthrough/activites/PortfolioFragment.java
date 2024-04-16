package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.ClothesCategoryAdapter;
import com.example.walkthrough.adapter.PortfolioAdapter;


public class PortfolioFragment extends Fragment {
    int [] image_category={R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes};
    Button complainbtn,hireme;

    public PortfolioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        View view=inflater.inflate(R.layout.fragment_portfolio, container, false);
        complainbtn=view.findViewById(R.id.complainbtn);
        hireme=view.findViewById(R.id.hirebtn);
        complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment frag = new ComplainFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.userhome, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
             /*   ft.addToBackStack(null);*/
                ft.commit();
            }
        });
        hireme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment frag = new MeasurementFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.userhome, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                /*ft.addToBackStack(null);*/
                ft.commit();
            }
        });
        RecyclerView portfolio_recyclerView=view.findViewById(R.id.portfolio_recyclerView);
        PortfolioAdapter portfolioAdapter=new PortfolioAdapter(image_category);
        portfolio_recyclerView.setLayoutManager(new LinearLayoutManager(context));
        portfolio_recyclerView.setAdapter(portfolioAdapter);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
        portfolio_recyclerView.setLayoutManager(gridLayoutManager);
        portfolio_recyclerView.setAdapter(portfolioAdapter);
        return view;
    }
}