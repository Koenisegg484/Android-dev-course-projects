package com.example.sendingdataf2a;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class testFrag extends Fragment {

    EditText name, email;
    Button submit;
    public testFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_test, container, false);

        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrname = name.getText().toString();
                String usremail = email.getText().toString();
                System.out.println(usrname + usremail);

                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.takeData(usrname, usremail);
            }
        });

        return view;
    }
}