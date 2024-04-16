package com.example.walkthrough.activites.Clothes.bottomNavigationFragments;

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
import com.example.walkthrough.activites.Clothes.AdapterClass.ConOrderClothesOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.SliderAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingClothesDetailsModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.FinalOrdermodelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.SliderData;
import com.example.walkthrough.databinding.FragmentNewClothesHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class NewClothesHomeFragment extends Fragment {

    private FragmentNewClothesHomeBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;

    public NewClothesHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewClothesHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
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

        //
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("please wait...");
        dialog.show();
        GetlistofClothes();
        return view;
    }

    private void GetlistofClothes() {
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            ArrayList<BookingClothesDetailsModelClass> list;
            list = new ArrayList<>();
            ConOrderClothesOrderRequestAdapter adapters = new ConOrderClothesOrderRequestAdapter(list, getContext());
            binding.bookeduserID.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.bookeduserID.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("Seller order Details").child(
                    "order");

            ref.child(uid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    dialog.hide();
                    list.clear();
                    int sum=0;
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FinalOrdermodelClass obj = dataSnapshot.getValue(FinalOrdermodelClass.class);
                            String producID = obj.getProductID();
                            String status = obj.getOrderStatus();
                            if (producID!=null && status.equals("Accepted")){
                                String price= obj.getProductprice();
                                int totalprice=Integer.parseInt(price);
                                sum=sum+totalprice;
                                DatabaseReference ref2 = firebaseDatabase.getReference("Clothes Product Details").child(uid);
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
                                        Toast.makeText(getContext(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                        adapters.notifyDataSetChanged();
                    } else {
                        adapters.notifyDataSetChanged();
                        dialog.hide();
                        Toast.makeText(getContext(), "No Request found", Toast.LENGTH_SHORT).show();
                    }
//                    String totalsum=Integer.toString(sum);
//                    binding.totalmountid.setText(totalsum);
//                    dialog.hide();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    dialog.hide();
                    Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
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
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requireActivity().getOnBackPressedDispatcher().addCallback(this, onBackPressedCallback);
    }
}