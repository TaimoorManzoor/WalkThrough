package com.example.walkthrough.activites;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.walkthrough.R;
import com.example.walkthrough.databinding.FragmentTailorTotalEarningBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class TailorTotalEarningFragment extends Fragment {

    FragmentTailorTotalEarningBinding binding;

    public TailorTotalEarningFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        binding=FragmentTailorTotalEarningBinding.inflate(inflater,container,false);



        ArrayList<BarEntry> information=new ArrayList<>();
        information.add(new BarEntry(2010,1000));
        information.add(new BarEntry(2011,1200));
        information.add(new BarEntry(2012,1400));
        information.add(new BarEntry(2013,1700));
        information.add(new BarEntry(2014,1300));
        information.add(new BarEntry(2015,1100));
        information.add(new BarEntry(2016,1000));
        information.add(new BarEntry(2017,1700));
        information.add(new BarEntry(2018,1900));
        information.add(new BarEntry(2019,2200));
        BarDataSet dataset=new BarDataSet(information,"Report");
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(20f);
        BarData barData=new BarData(dataset);
        binding.monthlychart.setFitBars(true);
        binding.monthlychart.setData(barData);
        binding.monthlychart.getDescription().setText("Bar Report Demo");
        binding.monthlychart.animateY(2000);



        ArrayList<BarEntry> informations=new ArrayList<>();
        information.add(new BarEntry(2010,1000));
        information.add(new BarEntry(2011,1200));
        information.add(new BarEntry(2012,1400));
        information.add(new BarEntry(2013,1700));
        information.add(new BarEntry(2014,1300));
        information.add(new BarEntry(2015,1100));
        information.add(new BarEntry(2016,1000));
        information.add(new BarEntry(2017,1700));
        information.add(new BarEntry(2018,1900));
        information.add(new BarEntry(2019,2200));
        BarDataSet datasets=new BarDataSet(information,"Report");
        dataset.setColors(ColorTemplate.MATERIAL_COLORS);
        dataset.setValueTextColor(Color.BLACK);
        dataset.setValueTextSize(20f);
        BarData barDatas=new BarData(dataset);
        binding.yearlylychart.setFitBars(true);
        binding.yearlylychart.setData(barData);
        binding.yearlylychart.getDescription().setText("Bar Report Demo");
        binding.yearlylychart.animateY(2000);
        return binding.getRoot();
    }
}