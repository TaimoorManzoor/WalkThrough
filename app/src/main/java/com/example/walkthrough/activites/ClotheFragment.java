package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.adapter.ClothesCategoryAdapter;
import com.example.walkthrough.R;
import com.example.walkthrough.model.ClotheModel;
import com.example.walkthrough.model.TailorCategoryModel;

import java.util.ArrayList;


public class ClotheFragment extends Fragment {


   /* String [] status_category={"Available","Available","Available","Available","Available","Available"};
    String [] name_category={"Android Khan","Android Khan","Android Khan","Android Khan","Android Khan","Android Khan"};
    String [] gender_category={"Men","Men","Men","Men","Men","Men"};
    int [] image_category={R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes};*/
    public ClotheFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Context context = getContext();
        View view=inflater.inflate(R.layout.fragment_clothe, container, false);

        RecyclerView clothes_category_recycleView=view.findViewById(R.id.cloth_category_recycleView);
        ClothesCategoryAdapter categoryadapter=new ClothesCategoryAdapter(clotheslist());
        clothes_category_recycleView.setLayoutManager(new LinearLayoutManager(context));
        clothes_category_recycleView.setAdapter(categoryadapter);
        return view;
    }

    public ArrayList<ClotheModel> clotheslist()
    {
        ArrayList<ClotheModel> holder=new ArrayList<ClotheModel>();
        ClotheModel obj=new ClotheModel();
        obj.setName("Clothes Name");
        obj.setImage(R.drawable.clothes);
        obj.setStatus("Available");
        obj.setGender("Male");

        holder.add(obj);
        return holder;
    }
}