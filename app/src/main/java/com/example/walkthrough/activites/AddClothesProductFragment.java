package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.AddProduuctAdapterRecycleview;
import com.example.walkthrough.adapter.PendingOrderAdapterRecycleview;
import com.example.walkthrough.databinding.FragmentAddClothesProductBinding;
import com.example.walkthrough.databinding.FragmentTailorPendingOrderBinding;
import com.example.walkthrough.model.AddProductModel;
import com.example.walkthrough.model.PendingOrderRecycleviewModel;

import java.util.ArrayList;


public class AddClothesProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentAddClothesProductBinding binding;

    public AddClothesProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddClothesProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddClothesProductFragment newInstance(String param1, String param2) {
        AddClothesProductFragment fragment = new AddClothesProductFragment();
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
        binding= FragmentAddClothesProductBinding.inflate(inflater,container,false);

        AddProduuctAdapterRecycleview categoryadapter=new AddProduuctAdapterRecycleview(pendingOrderlist());
        binding.productimagerecycleview.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.productimagerecycleview.setAdapter(categoryadapter);
        return binding.getRoot();
    }
    public ArrayList<AddProductModel> pendingOrderlist()
    {
        ArrayList<AddProductModel> holder=new ArrayList<AddProductModel>();
        AddProductModel obj=new AddProductModel();
        obj.setImage(R.drawable.anas);
        holder.add(obj);
        return holder;
    }
}