package com.example.sendingdataf2f;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Fragment02 extends Fragment {

    public Fragment02() {
        // Required empty public constructor
    }

    TextView uname, uemaail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_02, container, false);

        uname = view.findViewById(R.id.uname);
        uemaail = view.findViewById(R.id.uemail);

        Bundle bundle = getArguments();

        try {
            uname.setText(bundle.getString("uname"));
            uemaail.setText(bundle.getString("uemail"));
        } catch (NullPointerException e) {
            uname.setText("No Name here");
            uemaail.setText("No email here");
        }

        return  view;
    }
}