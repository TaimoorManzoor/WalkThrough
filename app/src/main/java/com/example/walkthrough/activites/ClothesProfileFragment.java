package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.ClothesProfilePortfolioAdapter;
import com.example.walkthrough.adapter.TailorProfilePortfolioAdapter;
import com.example.walkthrough.databinding.FragmentClothesProfileBinding;
import com.example.walkthrough.model.ClothesProfilePorfolioModel;
import com.example.walkthrough.model.TailorProfilePorfolioModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClothesProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClothesProfileFragment extends Fragment {

    FragmentClothesProfileBinding binding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClothesProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClothesProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClothesProfileFragment newInstance(String param1, String param2) {
        ClothesProfileFragment fragment = new ClothesProfileFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentClothesProfileBinding.inflate(inflater, container, false);

        binding.hirebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new Clothes_AccountFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        });

        binding.complainbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new Clothes_Complain_Fragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.container, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });


        RecyclerView portfolio_recyclerView=binding.portfolioRecyclerView;
        ClothesProfilePortfolioAdapter portfolioAdapter=new ClothesProfilePortfolioAdapter(portfoliolist());
       /* portfolio_recyclerView.setLayoutManager(new LinearLayoutManager(context));
        portfolio_recyclerView.setAdapter(portfolioAdapter);*/

        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),3);
        portfolio_recyclerView.setLayoutManager(gridLayoutManager);
        portfolio_recyclerView.setAdapter(portfolioAdapter);
        return  binding.getRoot();
    }
    public ArrayList<ClothesProfilePorfolioModel> portfoliolist()
    {
        ArrayList<ClothesProfilePorfolioModel> holder=new ArrayList<ClothesProfilePorfolioModel>();
        ClothesProfilePorfolioModel obj=new ClothesProfilePorfolioModel();
        obj.setImage(R.drawable.clothes);
        holder.add(obj);
        return holder;
    }
}