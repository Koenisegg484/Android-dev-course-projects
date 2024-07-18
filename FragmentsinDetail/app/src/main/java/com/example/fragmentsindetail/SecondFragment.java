package com.example.fragmentsindetail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class SecondFragment extends Fragment {

    ImageView imageView;


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second, container, false);
        imageView = view.findViewById(R.id.image);

        Bundle bundle = getArguments();

        int pos = bundle.getInt("pos");

        if (pos == 0){
            imageView.setImageResource(R.drawable.images);
        } else if (pos == 1) {
            imageView.setImageResource(R.drawable.images1);
        } else if (pos == 2) {
            imageView.setImageResource(R.drawable.images2);
        } else if (pos == 3) {
            imageView.setImageResource(R.drawable.images3);
        } else if (pos == 4) {
            imageView.setImageResource(R.drawable.images4);
        } else if (pos == 5) {
            imageView.setImageResource(R.drawable.images5);
        } else if (pos == 6) {
            imageView.setImageResource(R.drawable.images6);
        }


        return view;
    }
}