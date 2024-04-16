package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.CategoryAdapter;
import com.example.walkthrough.adapter.OrderRecivedAdapterRecycleview;
import com.example.walkthrough.adapter.RequestedOrderAdapter;
import com.example.walkthrough.databinding.FragmentTailorOrderRecivedBinding;
import com.example.walkthrough.model.RequestOrderRecycleModel;
import com.example.walkthrough.model.RequestedOrderModel;

import java.util.ArrayList;

public class tailorOrderRecivedFragment extends Fragment {

    FragmentTailorOrderRecivedBinding binding;
    public tailorOrderRecivedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        binding=FragmentTailorOrderRecivedBinding.inflate(inflater,container,false);


        OrderRecivedAdapterRecycleview categoryadapter=new OrderRecivedAdapterRecycleview(requestedOrderlist());
        binding.orderRecived.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.orderRecived.setAdapter(categoryadapter);
        return binding.getRoot();
    }
    public ArrayList<RequestOrderRecycleModel> requestedOrderlist()
    {
        ArrayList<RequestOrderRecycleModel> holder=new ArrayList<RequestOrderRecycleModel>();
        RequestOrderRecycleModel obj=new RequestOrderRecycleModel();
        obj.setName("Anas Ali KHAN");
        obj.setGender("Male");
        obj.setImages(R.drawable.clothes);
        obj.setImagecancel(R.drawable.baseline_cancel_24);
        obj.setImagecheck(R.drawable.check_circle);

        holder.add(obj);
        return holder;
    }
}