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

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.PortfolioAdapter;
import com.example.walkthrough.adapter.TailorProfilePortfolioAdapter;
import com.example.walkthrough.databinding.FragmentTailorProfileBinding;
import com.example.walkthrough.model.RequestedOrderModel;
import com.example.walkthrough.model.TailorProfilePorfolioModel;

import java.util.ArrayList;


public class TailorProfileFragment extends Fragment {

    FragmentTailorProfileBinding binding;
    public TailorProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
/*
        View view=inflater.inflate(R.layout.fragment_portfolio, container, false);
*/
        binding=FragmentTailorProfileBinding.inflate(inflater,container,false);

        binding.hirebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new TailorAccountFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                 ft.commit();
            }
        });

        binding.complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new Tailor_Complain_Fragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        RecyclerView portfolio_recyclerView=binding.portfolioRecyclerView;
        TailorProfilePortfolioAdapter portfolioAdapter=new TailorProfilePortfolioAdapter(portfoliolist());
       /* portfolio_recyclerView.setLayoutManager(new LinearLayoutManager(context));
        portfolio_recyclerView.setAdapter(portfolioAdapter);*/

        GridLayoutManager gridLayoutManager=new GridLayoutManager(context,3);
        portfolio_recyclerView.setLayoutManager(gridLayoutManager);
        portfolio_recyclerView.setAdapter(portfolioAdapter);

        return binding.getRoot();
    }
    public ArrayList<TailorProfilePorfolioModel> portfoliolist()
    {
        ArrayList<TailorProfilePorfolioModel> holder=new ArrayList<TailorProfilePorfolioModel>();
        TailorProfilePorfolioModel obj=new TailorProfilePorfolioModel();
        obj.setImage(R.drawable.anas);
        holder.add(obj);
        return holder;
    }
}