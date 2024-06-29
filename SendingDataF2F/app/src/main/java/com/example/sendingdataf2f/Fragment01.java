package com.example.sendingdataf2f;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Fragment01 extends Fragment {

    public Fragment01() {
        // Required empty public constructor
    }

    EditText takename, takeemail;
    Button submit;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_01, container, false);

        takename = view.findViewById(R.id.name);
        takeemail = view.findViewById(R.id.email);
        submit = view.findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = takename.getText().toString();
                String email = takeemail.getText().toString();

                Bundle bundle = new Bundle();
                bundle.putString("uname", name);
                bundle.putString("uemail", email);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                Fragment02 fragment02 = new Fragment02();
                fragment02.setArguments(bundle);

                fragmentTransaction.replace(R.id.fragment02, fragment02);
                fragmentTransaction.commit();

            }
        });



        return view;
    }
}