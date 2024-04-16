package com.example.walkthrough.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentClothesAccountBinding;
import com.example.walkthrough.model.TailorProfilePorfolioModel;

import java.util.ArrayList;

public class TailorProfilePortfolioAdapter extends RecyclerView.Adapter<TailorProfilePortfolioAdapter.MyViewHolder> {
    ArrayList<TailorProfilePorfolioModel> porfoliolist;
    public TailorProfilePortfolioAdapter(ArrayList<TailorProfilePorfolioModel> porfoliolist) {
        this.porfoliolist = porfoliolist;
    }

    public TailorProfilePortfolioAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.tailor_profile_recycleview,parent,false);
        return new TailorProfilePortfolioAdapter.MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TailorProfilePortfolioAdapter.MyViewHolder holder, int position) {
        holder.image.setImageResource(porfoliolist.get(position).getImage());
    }


    @Override
    public int getItemCount() {
        return porfoliolist.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            image=itemView.findViewById(R.id.porfolioimg);

        }
    }


    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link ClothesAccountFragment#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class ClothesAccountFragment extends Fragment {

        FragmentClothesAccountBinding binding;

        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public ClothesAccountFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ClothesAccountFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static ClothesAccountFragment newInstance(String param1, String param2) {
            ClothesAccountFragment fragment = new ClothesAccountFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            binding=FragmentClothesAccountBinding.inflate(inflater,container,true);
            return binding.getRoot();


        }
    }
}
