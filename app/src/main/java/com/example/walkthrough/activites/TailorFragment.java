package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.TailorCategoryAdapter;
import com.example.walkthrough.model.TailorCategoryModel;

import java.util.ArrayList;


public class TailorFragment extends Fragment  {
    String [] status_category={"Available","Available","Available","Available","Available","Available"};
    String [] name_category={"Android Khan","Android Khan","Android Khan","Android Khan","Android Khan","Android Khan"};
    String [] gender_category={"Men","Men","Men","Men","Men","Men"};
    int [] image_category={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};



    public TailorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Context context = getContext();

        View view=inflater.inflate(R.layout.fragment_tailor, container, false);

        RecyclerView tailor_category_recycleView=view.findViewById(R.id.tailor_category_recycleView);
        TailorCategoryAdapter categoryadapter=new TailorCategoryAdapter(tailorlist());
        tailor_category_recycleView.setLayoutManager(new LinearLayoutManager(context));
        tailor_category_recycleView.setAdapter(categoryadapter);
        return view;
    }

    public ArrayList<TailorCategoryModel> tailorlist()
    {
        ArrayList<TailorCategoryModel> holder=new ArrayList<TailorCategoryModel>();
        TailorCategoryModel obj=new TailorCategoryModel();
        obj.setName("Anas Ali");
        obj.setImage(R.drawable.anas);
        obj.setStatus("Available");
        obj.setGender("Male");

        holder.add(obj);
        return holder;
    }
}