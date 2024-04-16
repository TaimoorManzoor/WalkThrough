package com.example.walkthrough.activites;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.walkthrough.adapter.ClothesAdapter;
import com.example.walkthrough.adapter.MyAdapter;
import com.example.walkthrough.R;
import com.example.walkthrough.adapter.RecommendAdapter;
import com.example.walkthrough.adapter.TailorAdapter;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.example.walkthrough.model.TailorProfileDetailModelClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    ImageView imageView;
    int [] image={R.drawable.women,R.drawable.women,R.drawable.women,R.drawable.women,R.drawable.women,R.drawable.women,R.drawable.women,R.drawable.women};
    String [] name={"Alter","Alter","Alter","Alter","Alter","Alter","Alter","Alter"};
    int [] image_tailor={R.drawable.profile,R.drawable.profile,R.drawable.profile,R.drawable.profile};
    String [] name_tailor={"Adrian Fernandez","Adrian Fernandez","Adrian Fernandez","Adrian Fernandez"};
    String [] address_tailor={"Korangi 3","Korangi 3","Korangi 3","Korangi 3"};
    String [] price_tailor={"Rs 1,500","Rs 1,500","Rs 1,500","Rs 1,500"};

    int [] image_clothes={R.drawable.clothes,R.drawable.clothes,R.drawable.clothes,R.drawable.clothes};
    String [] name_clothes={"Clothes Name","Clothes Name","Clothes Name","Clothes Name"};
    String [] price_clothes={"Rs 1,500","Rs 1,500","Rs 1,500","Rs 1,500"};
    ProgressDialog dialog;
    public HomeFragment() {
        // Required empty public constructor
    }

       @SuppressLint("MissingInflatedId")
       @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            //General Code
           Context context = getContext();
           View view=inflater.inflate(R.layout.fragment_home, container, false);
           dialog = new ProgressDialog(getContext());
           dialog.setMessage("please wait...");
           dialog.show();
           imageView=view.findViewById(R.id.imageID);
           imageView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   FirebaseAuth auth = FirebaseAuth.getInstance();

                   auth.signOut();
                   startActivity(new Intent(getContext(),MainActivity.class));
               }
           });
           // Category code
//           RecyclerView recyclerView=view.findViewById(R.id.reCycleView);
//           MyAdapter myAdapter=new MyAdapter(name,image);
//           recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
//           recyclerView.setAdapter(myAdapter);

            // Tailor code


           // Clothes
           RecyclerView clothes_recyclerView=view.findViewById(R.id.clothes_recyclerView);
           ClothesAdapter clothesAdapter=new ClothesAdapter(name_clothes,image_clothes,price_clothes);
           clothes_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
           clothes_recyclerView.setAdapter(clothesAdapter);

           //Recommanded for you
           RecyclerView recommend_recyclerView=view.findViewById(R.id.recommended_recyclerView);
           RecommendAdapter recommendAdapter=new RecommendAdapter(name_tailor,image_tailor,price_tailor);
           recommend_recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
           recommend_recyclerView.setAdapter(recommendAdapter);
           GetlistofTailor(view);
        return view;
    }
//    private void TailorProfile(View view){
//
//
//        RecyclerView tailor_recyclerView=view.findViewById(R.id.tailor_recyclerView);
//        TailorAdapter tailorAdapter=new TailorAdapter(name_tailor,image_tailor,address_tailor,price_tailor);
//        tailor_recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//        tailor_recyclerView.setAdapter(tailorAdapter);
//    }
    private void GetlistofTailor(View view){
        ArrayList<AddTailorDetailToRealtym> list;
        RecyclerView recyclerView;
        TailorAdapter adapters;
        recyclerView=view.findViewById(R.id.tailor_recyclerView);
        list=new ArrayList<>();
        adapters=new TailorAdapter(list,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapters);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference ref= firebaseDatabase.getReference("TailorProfile").child(
                "Tailor");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                        AddTailorDetailToRealtym obj=dataSnapshot.getValue(AddTailorDetailToRealtym.class);
                        list.add(obj);
                    }
                    dialog.dismiss();
                    adapters.notifyDataSetChanged();
                }else {
                    dialog.dismiss();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error"+error, Toast.LENGTH_SHORT).show();
            }
        });
    }

}