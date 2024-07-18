package com.example.dialogfragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MyDialogFragment extends DialogFragment {

    Button ok, cancel;
    EditText namehere;
    public MyDialogFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_dialog, container, false);

        ok = view.findViewById(R.id.okbutton);
        cancel = view.findViewById(R.id.cancelbutton);
        namehere = view.findViewById(R.id.namehere);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = namehere.getText().toString();
                ((MainActivity)getActivity()).tv.setText(name);
                getDialog().dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        return view;
    }
}