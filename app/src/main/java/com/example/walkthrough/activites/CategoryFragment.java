package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.adapter.CategoryAdapter;
import com.example.walkthrough.R;


public class CategoryFragment extends Fragment {
    String [] status_category={"Available","Available","Available","Available","Available","Available"};
    String [] name_category={"Android Khan","Android Khan","Android Khan","Android Khan","Android Khan","Android Khan"};
    String [] gender_category={"Men","Men","Men","Men","Men","Men"};
    int [] image_category={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        Context context = getContext();
        View view=inflater.inflate(R.layout.fragment_category, container, false);

        RecyclerView category_recycleView=view.findViewById(R.id.category_recycleView);
        CategoryAdapter categoryadapter=new CategoryAdapter(name_category,image_category,gender_category,status_category);
        category_recycleView.setLayoutManager(new LinearLayoutManager(context));
        category_recycleView.setAdapter(categoryadapter);
        return view;
    }
}