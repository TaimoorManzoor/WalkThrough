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
import com.example.walkthrough.activites.Clothes.AdapterClass.ConFrimedClothesOrderAdapter;
import com.example.walkthrough.activites.Clothes.ModelClass.ClothesBookingModelClass;
import com.example.walkthrough.activites.Tailor.adapterClasses.ConFrimedTailorOrderAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.databinding.FragmentOrderTailorBinding;
import com.example.walkthrough.databinding.FragmentTailorTotalEarning2Binding;
import com.example.walkthrough.databinding.FragmentTotalEarningsBinding;
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


public class TailorTotalEarningFragment extends Fragment {

    private FragmentTailorTotalEarning2Binding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    SearchView searchBox;
    ConFrimedTailorOrderAdapter adapter;
    ArrayList<BookingDetailModelClass> list;
    double price;
    public TailorTotalEarningFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTailorTotalEarning2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        dialog = new ProgressDialog(requireContext());
        dialog.setMessage("please wait...");
        dialog.show();
        GetlistofBookedUser(view);
        return view;
    }
    private void GetlistofBookedUser(View view) {
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            ArrayList<BookingDetailModelClass> list;
            list = new ArrayList<>();
            ConFrimedTailorOrderAdapter adapters = new ConFrimedTailorOrderAdapter(list, getContext());
            binding.rvtotalmount.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.rvtotalmount.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("TailorSalaiRequests").child(
                    uid);

            ref.child("TailorRequests").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    int sum=0;
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            RequestModelCLass obj = dataSnapshot.getValue(RequestModelCLass.class);
                            String reqID = obj.getUserSenderID();
                            String status = obj.getRequestStatus();
                            if (reqID!=null && status.equals("Accepted")){
                                String price= obj.getTailorPrice();
                                 int totalprice=Integer.parseInt(price);
                                 sum=sum+totalprice;
                                DatabaseReference ref2 = firebaseDatabase.getReference("CustomerProfile");
                                ref2.child(reqID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String userName = snapshot.child("username").getValue(String.class);
                                            String userImage = snapshot.child("userImage").getValue(String.class);
                                            list.add(new BookingDetailModelClass(obj.getTailorReceiverID(), obj.getUserSenderID(), obj.getRequestID(), userName, userImage));
                                        }
                                        adapters.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        dialog.dismiss();
                                        Toast.makeText(requireContext(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                            }
                        }
                         // total price
                        String totalsum=Integer.toString(sum);
                        binding.totalmountid.setText(totalsum);

                    } else {
                        dialog.dismiss();
                        Toast.makeText(requireContext(), "No Confrimed found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(requireContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }

    }


}