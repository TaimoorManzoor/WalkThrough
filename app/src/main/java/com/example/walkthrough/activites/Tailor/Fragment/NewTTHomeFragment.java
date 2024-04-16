package com.example.walkthrough.activites.Tailor.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.adapterClasses.ConFrimedTailorOrderAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.SliderAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.activites.User.fragments.modelClasses.SliderData;
import com.example.walkthrough.databinding.FragmentNewTTHomeBinding;
import com.example.walkthrough.databinding.FragmentTailorsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class NewTTHomeFragment extends Fragment {

   private FragmentNewTTHomeBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    public NewTTHomeFragment() {
        // Required empty public constructor
    }





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewTTHomeBinding.inflate(getLayoutInflater());
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
        dialog =new ProgressDialog(getContext());
        try{
        dialog.setTitle("Please Wait");
        dialog.show();
        }
        catch (Exception  e)
        {
            dialog.setTitle("Please Wait");
        }
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
            ConFrimedTailorOrderAdapter adapters = new ConFrimedTailorOrderAdapter(list, requireActivity());
            binding.bookeduserID.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.bookeduserID.setAdapter(adapters);
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference ref = firebaseDatabase.getReference("TailorSalaiRequests").child(
                    uid);

            ref.child("TailorRequests").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    list.clear();
                    if (snapshot.exists()) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            RequestModelCLass obj = dataSnapshot.getValue(RequestModelCLass.class);
                            String reqID = obj.getUserSenderID();
                            String status = obj.getRequestStatus();
                            if (reqID!=null && status.equals("Accepted")){
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
                                        Toast.makeText(getActivity(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }


                    }
                    dialog.hide();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Error" + error, Toast.LENGTH_SHORT).show();
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

    // Unregister the callback in the onDestroy() method
    @Override
    public void onDestroy() {
        super.onDestroy();
        onBackPressedCallback.remove();
    }


}