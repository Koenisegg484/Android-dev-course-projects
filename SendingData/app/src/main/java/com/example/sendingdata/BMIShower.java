package com.example.sendingdata;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BMIShower extends Fragment {

    TextView bmi;

    public BMIShower() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b_m_i_shower, container, false);
        bmi = view.findViewById(R.id.bmi);

        Bundle bundle = getArguments();
        int wight = bundle.getInt("weight");
        int height = bundle.getInt("height");

        double result = (double) (wight * 1000) / (height * height);
        bmi.setText("Your bmi is : " + result);

        return view;
    }
}