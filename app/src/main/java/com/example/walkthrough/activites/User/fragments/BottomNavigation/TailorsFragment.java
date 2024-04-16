package com.example.walkthrough.activites.User.fragments.BottomNavigation;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.walkthrough.R;
import com.example.walkthrough.activites.Clothes.ModelClass.AddCategoryProductModelClass;
import com.example.walkthrough.activites.User.fragments.adapterClasses.TailorListAdapter;
import com.example.walkthrough.activites.User.fragments.modelClasses.TailorList;
import com.example.walkthrough.databinding.FragmentTailorsBinding;
import com.example.walkthrough.model.AddTailorDetailToRealtym;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TailorsFragment extends Fragment {
    private FragmentTailorsBinding binding;
    ProgressDialog dialog;
    ArrayList<AddTailorDetailToRealtym> list;
    TailorListAdapter  adapters;
    public TailorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTailorsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        dialog = new ProgressDialog(getContext());
        dialog.setMessage("please wait...");
        dialog.show();
        GetlistofTailor(view);

        return view;
    }
    private void GetlistofTailor(View view) {
        list = new ArrayList<>();
        adapters = new  TailorListAdapter(list,getContext());
        binding.tailorsListRCV.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.tailorsListRCV.setAdapter(adapters);
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
                    Toast.makeText(getContext(), "No Tailor Yet", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Error" + error, Toast.LENGTH_SHORT).show();
            }
        });
        binding.searchView.clearFocus();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        List<AddTailorDetailToRealtym> filterlist=new ArrayList<>();
        for (AddTailorDetailToRealtym item: list){
            if(item.getClothestype().toLowerCase().contains(Text.toLowerCase(Locale.ROOT))){
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
    private OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            Bundle bundle=getArguments();
            Fragment fragment=new HomeFragment();
            fragment.setArguments(bundle);
            ((AppCompatActivity) getContext()).getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container,fragment, "findThisFragment")
                    .addToBackStack(null)
                    .commit();
        }
    };

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