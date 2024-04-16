package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.ClothesOrderRecivedAdapterRecycleview;
import com.example.walkthrough.adapter.OrderRecivedAdapterRecycleview;
import com.example.walkthrough.databinding.FragmentClothesOrderRecivedBinding;
import com.example.walkthrough.model.ClothesOrderRecivedModel;
import com.example.walkthrough.model.RequestOrderRecycleModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClothesOrderRecivedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClothesOrderRecivedFragment extends Fragment {

    FragmentClothesOrderRecivedBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClothesOrderRecivedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClothesOrderRecivedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClothesOrderRecivedFragment newInstance(String param1, String param2) {
        ClothesOrderRecivedFragment fragment = new ClothesOrderRecivedFragment();
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
        binding=FragmentClothesOrderRecivedBinding.inflate(inflater,container,false);


        ClothesOrderRecivedAdapterRecycleview categoryadapter=new ClothesOrderRecivedAdapterRecycleview(requestedOrderlist());
        binding.clothespendingorderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.clothespendingorderRecyclerView.setAdapter(categoryadapter);

        return binding.getRoot();
    }

    public ArrayList<ClothesOrderRecivedModel> requestedOrderlist()
    {
        ArrayList<ClothesOrderRecivedModel> holder=new ArrayList<ClothesOrderRecivedModel>();
        ClothesOrderRecivedModel obj=new ClothesOrderRecivedModel();
        obj.setClothes_name("Anas Ali KHAN");
        obj.setClothes_category("Shalwar kammeez");
        obj.setClothes_price("200 Rs");
        obj.setClothes_date("7/may/2022");
        obj.setClothes_image(R.drawable.clothes);

        holder.add(obj);
        return holder;
    }
}