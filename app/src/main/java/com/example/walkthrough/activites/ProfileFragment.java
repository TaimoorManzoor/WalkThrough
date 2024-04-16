package com.example.walkthrough.activites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.adapter.ViewPageTailorAdaptor;
import com.google.android.material.tabs.TabLayout;


public class ProfileFragment extends Fragment {


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      /*  TabLayout tabLayout;
        ViewPager viewPager;
        View view =

        tabLayout = view.findViewById(R.id.tablayout);
        viewPager = view.findViewById(R.id.viewpager);

        // Create and set up the adapter for ViewPager
        ViewPageTailorAdaptor viewPageTailorAdapter = new ViewPageTailorAdaptor(getChildFragmentManager());
        viewPager.setAdapter(viewPageTailorAdapter);

        // Connect TabLayout with ViewPager
        tabLayout.setupWithViewPager(viewPager);
*/
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}
