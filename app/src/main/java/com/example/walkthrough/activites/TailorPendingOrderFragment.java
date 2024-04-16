package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.OrderRecivedAdapterRecycleview;
import com.example.walkthrough.adapter.PendingOrderAdapterRecycleview;
import com.example.walkthrough.databinding.FragmentTailorPendingOrderBinding;
import com.example.walkthrough.model.PendingOrderRecycleviewModel;
import com.example.walkthrough.model.RequestOrderRecycleModel;

import java.util.ArrayList;


public class TailorPendingOrderFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FragmentTailorPendingOrderBinding binding;

    public TailorPendingOrderFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TailorPendingOrderFragment newInstance(String param1, String param2) {
        TailorPendingOrderFragment fragment = new TailorPendingOrderFragment();
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
        binding=FragmentTailorPendingOrderBinding.inflate(inflater,container,false);

        PendingOrderAdapterRecycleview categoryadapter=new PendingOrderAdapterRecycleview(pendingOrderlist());
        binding.pendingorderRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.pendingorderRecyclerView.setAdapter(categoryadapter);
        return binding.getRoot();

    }
    public ArrayList<PendingOrderRecycleviewModel> pendingOrderlist()
    {
        ArrayList<PendingOrderRecycleviewModel> holder=new ArrayList<PendingOrderRecycleviewModel>();
        PendingOrderRecycleviewModel obj=new PendingOrderRecycleviewModel();
        obj.setName("Anas Ali KHAN");
        obj.setDate("2018-11-15");
        obj.setImage(R.drawable.anas);
        obj.setPrice("1500 Rs");
        obj.setSuitecategory("Shalwar Kameez");
        holder.add(obj);
        return holder;
    }
}