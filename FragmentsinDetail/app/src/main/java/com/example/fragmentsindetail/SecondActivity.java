package com.example.fragmentsindetail;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        SecondFragment secondFragment = new SecondFragment();

        Intent intent = getIntent();
        int pos = intent.getIntExtra("pos", 0);

        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);
        secondFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.Fragment002, secondFragment);
        fragmentTransaction.commit();

    }
}