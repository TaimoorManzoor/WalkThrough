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
import com.example.walkthrough.activites.Tailor.adapterClasses.ConFrimedTailorOrderAdapter;
import com.example.walkthrough.activites.Tailor.adapterClasses.TailorOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.adapterClasses.ConfrimedOrderUserOrderAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.databinding.FragmentNewOrderTBinding;
import com.example.walkthrough.databinding.FragmentNewTTHomeBinding;
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


public class NewOrderTFragment extends Fragment {


    private FragmentNewOrderTBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    SearchView searchBox;
    ConFrimedTailorOrderAdapter adapter;
    ArrayList<BookingDetailModelClass> list;
    public NewOrderTFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewOrderTBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

       dialog =new ProgressDialog(getContext());
       dialog.setTitle("please wait");
       dialog.show();
       GetlistofTailor(view);


        return  view;
    }
    private void GetlistofTailor(View view) {
        mauth = FirebaseAuth.getInstance();
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null) {
            String uid = user.getUid();
            ArrayList<BookingDetailModelClass> list;
            list = new ArrayList<>();
            ConFrimedTailorOrderAdapter adapters = new ConFrimedTailorOrderAdapter(list, getContext());
            binding.ordersTailorRCV.setLayoutManager(new LinearLayoutManager(getContext()));
            binding.ordersTailorRCV.setAdapter(adapters);
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
                                        }else{
                                            dialog.hide();
                                        }
                                        adapters.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        dialog.dismiss();
                                        Toast.makeText(getContext(), "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                dialog.hide();
                            }
                        }
                        dialog.dismiss();

                    } else {
                        dialog.dismiss();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
                }
            });
        } else {

        }
//        searchBox = view.findViewById(R.id.searchView2);
//        searchBox.clearFocus();
//        searchBox.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                Filterlist(newText);
//                return true;
//            }
//        });

    }
//    private void Filterlist(String Text) {
//        List<BookingDetailModelClass> filterlist=new ArrayList<>();
//        for (BookingDetailModelClass item: list){
//            if(item.getUsername().toLowerCase().contains(Text.toLowerCase(Locale.ROOT))){
//                filterlist.add(item);
//            }
//        }
//        if (filterlist==null&&filterlist.isEmpty())
//        {
//            Toast.makeText(getContext(), "not Found", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(getContext(), "Data Exists ", Toast.LENGTH_SHORT).show();
//            adapter.setFilteredlist(filterlist);
//        }
    }
