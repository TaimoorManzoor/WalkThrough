package com.example.walkthrough.activites.User.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.adapterClasses.TailorOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.UserOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.databinding.FragmentHome2Binding;
import com.example.walkthrough.databinding.FragmentRequestUserBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class RequestUserFragment extends Fragment {

    private FragmentRequestUserBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;

    public RequestUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRequestUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("please wait...");
        dialog.show();
        GetlistofTailor(view);
        return view;

    }

    private void GetlistofTailor(View view) {
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            ArrayList<BookingDetailModelClass> list;
            list = new ArrayList<>();
            UserOrderRequestAdapter adapters = new UserOrderRequestAdapter(list, getContext());
            binding.ordersTailorRCV.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.ordersTailorRCV.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("UserSalaiRequests").child(
                    uid);

            ref.child("UserRequests").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            RequestModelCLass obj = dataSnapshot.getValue(RequestModelCLass.class);
                            String reqID = obj.getTailorReceiverID();
                            String status = obj.getRequestStatus();
                            if (reqID!=null && status.equals("pending")){
                                DatabaseReference ref2 = firebaseDatabase.getReference("TailorProfile").child(
                                        "Tailor");
                                ref2.child(reqID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String userName = snapshot.child("username").getValue(String.class);
                                            String userImage = snapshot.child("imageurl").getValue(String.class);
                                            list.add(new BookingDetailModelClass(obj.getTailorReceiverID(), obj.getUserSenderID(), obj.getRequestID(), userName, userImage));

                                        }
                                        adapters.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        dialog.dismiss();
                                        Toast.makeText(getContext(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        adapters.notifyDataSetChanged();
                    } else {

                    }
                    dialog.hide();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }

    }
}