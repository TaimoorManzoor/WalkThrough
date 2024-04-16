package com.example.walkthrough.activites.User.fragments.BottomNavigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;

import com.example.walkthrough.activites.User.fragments.adapterClasses.BookingClothesUserOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.CartOrderUserOrderAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.ClothesUserOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.ShowClothesProductAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.SliderAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.TailorListAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingClothesDetailsModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.CartBookingModelClassDetails;
import com.example.walkthrough.activites.User.fragments.modelClasses.FinalOrdermodelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.SliderData;
import com.example.walkthrough.databinding.FragmentHome2Binding;
import com.example.walkthrough.databinding.FragmentHomeBinding;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private FragmentHome2Binding binding;
    ProgressDialog dialog;
    FirebaseDatabase database;
    ArrayList<CartBookingModelClassDetails> detailsArrayList;
    FirebaseAuth auth;
    CartOrderUserOrderAdapter orderAdapter;
    FirebaseAuth mauth;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHome2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();

          if (isAdded()){
              ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

              sliderDataArrayList.add(new SliderData(R.drawable.sliderimg1));
              sliderDataArrayList.add(new SliderData(R.drawable.sliderimg2));
              sliderDataArrayList.add(new SliderData(R.drawable.sliderimg3));
              sliderDataArrayList.add(new SliderData(R.drawable.fabric));

              SliderAdapter adapter = new SliderAdapter(getContext(), sliderDataArrayList);
              binding.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
              binding.slider.setSliderAdapter(adapter);
              binding.slider.setScrollTimeInSec(3);
              binding.slider.setAutoCycle(true);
              binding.slider.startAutoCycle();
              dialog = new ProgressDialog(requireActivity());
              dialog.setMessage("please wait...");
              dialog.show();
              GetlistofTailor(view);
              GetlistofClothes();
          }


//        Getlistofclothes(view);
        return view;
    }




    private void GetlistofTailor(View view) {
        ArrayList<AddTailorDetailToRealtym> list;
        list = new ArrayList<>();
        TailorListAdapter adapters = new  TailorListAdapter(list,requireActivity());
        binding.popularTailorRCV.setLayoutManager(new LinearLayoutManager(requireActivity(),LinearLayoutManager.HORIZONTAL,false));
        binding.popularTailorRCV.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("TailorProfile").child(
                "Tailor");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        AddTailorDetailToRealtym obj = dataSnapshot.getValue(AddTailorDetailToRealtym.class);
                        list.add(obj);
                    }
                    dialog.dismiss();
                    adapters.notifyDataSetChanged();
                }else{
                    dialog.dismiss();
                    Toast.makeText(requireActivity(), "No Tailor Yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
                Toast.makeText(requireActivity(), "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void GetlistofClothes() {
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            ArrayList<BookingClothesDetailsModelClass> list;
            list = new ArrayList<>();
            BookingClothesUserOrderRequestAdapter adapters = new BookingClothesUserOrderRequestAdapter(list, requireActivity());
            binding.mostSearchedClothesRCV.setLayoutManager(new LinearLayoutManager(requireActivity()));
            binding.mostSearchedClothesRCV.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("User Final order Details").child(
                    "order");

            ref.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FinalOrdermodelClass obj = dataSnapshot.getValue(FinalOrdermodelClass.class);
                            String producID = obj.getProductID();
                            String status = obj.getOrderStatus();
                            if (producID!=null && status.equals("Accepted")){
                                DatabaseReference ref2 = firebaseDatabase.getReference("Clothes Product Details To User");
                                ref2.child(producID).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            String userName = snapshot.child("sellerproductusername").getValue(String.class);
                                            String userImage = snapshot.child("productimage").getValue(String.class);
                                            list.add(new BookingClothesDetailsModelClass(uid, obj.getSellerID(), obj.getProductID(),userImage,userName,obj.getProductcount()));

                                        }
                                        adapters.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        dialog.dismiss();
                                        Toast.makeText(requireActivity(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                adapters.notifyDataSetChanged();
                            }

                        }
                        adapters.notifyDataSetChanged();
                    } else {
                    }
                    dialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(requireActivity(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }
    }

    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Exit();
        }
    };

    private void Exit() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Ez Sail");
        builder.setMessage("Are you sure you want to exit the EzSail?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getActivity().finishAffinity();
            }
        });
        builder.setNegativeButton("No", null);
        builder.show();
    }




    // Register the callback in the onCreate() method

}
