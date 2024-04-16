package com.example.walkthrough.activites;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.walkthrough.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class AccountFragment extends Fragment {
    Button confirm;
    String userUid;
    ImageView imageView;
    TextView txtname,txtphonenumber,txtemail,txtpassword;
    Button btnupdate;
    private FirebaseAuth mAuth;
    ProgressDialog dialog;

    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context=getContext();
         View view=inflater.inflate(R.layout.fragment_account, container, false);
        confirm=view.findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Information is Updated",Toast.LENGTH_LONG).show();
            }
        });
        SetProfile();
        Ids(view);
        SetCreate();
        return view;

    }
    private void Ids(View view){
     txtname=view.findViewById(R.id.firstname);
     txtphonenumber=view.findViewById(R.id.phone);
     txtpassword=view.findViewById(R.id.password);
     txtemail=view.findViewById(R.id.emails);
     imageView=view.findViewById(R.id.profile);


    }
    private void SetProfile(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userUid= user.getUid();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(
                "CustomerProfile").
               child(userUid.toString());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String userimage = snapshot.child("userImage").getValue(String.class);
                    Glide.with(getContext()).load(userimage).into(imageView);
                    String username = snapshot.child(
                            "username").getValue(String.class);
                    txtname.setText(username);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void SetCreate(){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        userUid= user.getUid();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference(
                                "SailAppForUser").
                child("Account Details");
        ref.child(userUid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String userphonenumber = snapshot.child(
                            "phonumber").getValue(String.class);
                    String useremail = snapshot.child(
                                    "useremail").getValue(String.class);
                    txtphonenumber.setText(userphonenumber);
                    txtemail.setText(useremail);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}