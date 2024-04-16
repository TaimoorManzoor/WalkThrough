package com.example.walkthrough.activites;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.RequestedOrderAdapter;
import com.example.walkthrough.adapter.TailorFrequentAdapter;
import com.example.walkthrough.adapter.TailorPendingOrderHomeAdapter;
import com.example.walkthrough.databinding.FragmentHomeClothesBinding;
import com.example.walkthrough.databinding.FragmentHomeTailorBinding;
import com.example.walkthrough.model.FrequentCustomerModel;
import com.example.walkthrough.model.PendingOrderModel;
import com.example.walkthrough.model.RequestedOrderModel;

import java.util.ArrayList;


public class HomeClothesFragment extends Fragment {

    FragmentHomeClothesBinding binding;

    public HomeClothesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context = getContext();
        binding= FragmentHomeClothesBinding.inflate(inflater,container,false);
        // Customer Frequent code
        RecyclerView recyclerView=binding.reCycleView;
        TailorFrequentAdapter myAdapter=new TailorFrequentAdapter(customerlist());
        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(myAdapter);

        // Pending order code
        RecyclerView pendingorder=binding.pendingorderRecyclerView;
        TailorPendingOrderHomeAdapter pendingOrderAdapter=new TailorPendingOrderHomeAdapter(pendingOrderlist());
        pendingorder.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        pendingorder.setAdapter(pendingOrderAdapter);
        // Reqyested order code
        RecyclerView requestedorder=binding.requwstedorderRecyclerView;
        RequestedOrderAdapter requestedorderAdapter=new RequestedOrderAdapter(requestedOrderlist());
        requestedorder.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        requestedorder.setAdapter(requestedorderAdapter);


        return binding.getRoot();
    }
    public ArrayList<FrequentCustomerModel> customerlist()
    {
        ArrayList<FrequentCustomerModel> holder=new ArrayList<FrequentCustomerModel>();
        FrequentCustomerModel obj=new FrequentCustomerModel();
        obj.setName("Anas Ali");
        obj.setImage(R.drawable.anas);
        obj.setAddress("Karachi");
        holder.add(obj);
        return holder;
    }

    public ArrayList<PendingOrderModel> pendingOrderlist()
    {
        ArrayList<PendingOrderModel> holder=new ArrayList<PendingOrderModel>();
        PendingOrderModel obj=new PendingOrderModel();
        obj.setName("Anas Ali");
        obj.setImage(R.drawable.anas);
        obj.setDetail("Detail");
        holder.add(obj);
        return holder;
    }
    public ArrayList<RequestedOrderModel> requestedOrderlist()
    {
        ArrayList<RequestedOrderModel> holder=new ArrayList<RequestedOrderModel>();
        RequestedOrderModel obj=new RequestedOrderModel();
        obj.setImage(R.drawable.anas);
        obj.setName("Anas Ali");
        obj.setAddress("address");
        holder.add(obj);
        return holder;
    }
}