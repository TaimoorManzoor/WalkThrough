package com.example.walkthrough.activites.Tailor.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.adapterClasses.TailorOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.TailorListAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.databinding.FragmentOrderTailorBinding;
import com.example.walkthrough.databinding.FragmentTailorsBinding;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OrderFragmentTailor extends Fragment {

    private FragmentOrderTailorBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    TailorOrderRequestAdapter adapters;
    ArrayList<BookingDetailModelClass> list;
    public OrderFragmentTailor() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderTailorBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("please wait...");
        GetlistofTailor(view);

        return view;
    }
    private void GetlistofTailor(View view) {
        dialog.show();
        mauth=FirebaseAuth.getInstance();
        FirebaseUser user =mauth.getCurrentUser();
        if (user!=null){
            String uid=user.getUid();
            list = new ArrayList<>();
            adapters = new  TailorOrderRequestAdapter(list,getActivity());
            binding.ordersTailorRCV.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.ordersTailorRCV.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("TailorSalaiRequests").child(uid).child("TailorRequests");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            RequestModelCLass obj = dataSnapshot.getValue(RequestModelCLass.class);
                            String SenderID=obj.getUserSenderID();
                            String ReqID=obj.getRequestID();
                            String status=obj.getRequestStatus();
                            if (ReqID!=null && status.equals("pending")){
                                DatabaseReference ref2 = firebaseDatabase.getReference("CustomerProfile");
                                ref2.child(SenderID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String userName = snapshot.child("username").getValue(String.class);
                                            String userImage = snapshot.child("userImage").getValue(String.class);
                                            list.add(new BookingDetailModelClass(uid,SenderID,ReqID, userName, userImage));
                                        }
                                        adapters.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        dialog.dismiss();
                                        Toast.makeText(getActivity(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }


                    }else{
                        Toast.makeText(getActivity(), "No Request Found", Toast.LENGTH_SHORT).show();
                    }
                    dialog.hide();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        }else{

        }
        binding.searchView2.clearFocus();
        binding.searchView2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Filterlist(newText);
                return true;
            }
        });

    }
    private void Filterlist(String Text) {
        List<BookingDetailModelClass> filterlist=new ArrayList<>();
        for (BookingDetailModelClass item: list){
            if(item.getUsername().toLowerCase().contains(Text.toLowerCase(Locale.ROOT))){
                filterlist.add(item);
            }
        }
        if (filterlist.isEmpty())
        {
            Toast.makeText(getContext(), "not Found", Toast.LENGTH_SHORT).show();
        }else{
            adapters.setFilteredlist(filterlist);
        }
    }
}