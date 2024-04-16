package com.example.walkthrough.activites.Tailor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Tailor.adapterClasses.TailorOrderRequestAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.BookingDetailModelClass;
import com.example.walkthrough.activites.User.fragments.modelClasses.RequestModelCLass;
import com.example.walkthrough.databinding.ActivityPendingreqOrderCheckerBinding;
import com.example.walkthrough.databinding.ActivityTailerDashboardBinding;
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

public class PendingreqOrderCheckerActivity extends AppCompatActivity {
    private ActivityPendingreqOrderCheckerBinding binding;
    ProgressDialog dialog;
    FirebaseAuth mauth;
    TailorOrderRequestAdapter adapters;
    ArrayList<BookingDetailModelClass> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPendingreqOrderCheckerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(PendingreqOrderCheckerActivity.this);
        dialog.setMessage("please wait...");
        GetlistofTailor();
    }
    private void GetlistofTailor() {
        dialog.show();
        mauth= FirebaseAuth.getInstance();
        FirebaseUser user =mauth.getCurrentUser();
        if (user!=null){
            String uid=user.getUid();
            list = new ArrayList<>();
            adapters = new TailorOrderRequestAdapter(list,PendingreqOrderCheckerActivity.this);
            binding.ordersTailorRCV.setLayoutManager(new LinearLayoutManager(PendingreqOrderCheckerActivity.this));
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
                                        Toast.makeText(PendingreqOrderCheckerActivity.this, "Error fetching user details: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        }


                    }else{
                        Toast.makeText(PendingreqOrderCheckerActivity.this, "No Request Found", Toast.LENGTH_SHORT).show();
                    }
                    dialog.hide();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(PendingreqOrderCheckerActivity.this, "Error" + error, Toast.LENGTH_SHORT).show();
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
            Toast.makeText(PendingreqOrderCheckerActivity.this, "not Found", Toast.LENGTH_SHORT).show();
        }else{
            adapters.setFilteredlist(filterlist);
        }
    }
}