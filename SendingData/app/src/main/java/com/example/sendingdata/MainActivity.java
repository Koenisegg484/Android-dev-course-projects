package com.example.sendingdata;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    EditText weight, height;
    Button startfrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        startfrag = findViewById(R.id.button2);
        FragmentManager fragmanagaer = getSupportFragmentManager();

        FragmentTransaction fragTransac = fragmanagaer.beginTransaction();

        BMIShower bmishow = new BMIShower();

        startfrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                int userWeight = Integer.parseInt(weight.getText().toString());
                int userHeight = Integer.parseInt(height.getText().toString());

                bundle.putInt("weight", userWeight);
                bundle.putInt("height", userHeight);

                bmishow.setArguments(bundle);
                fragTransac.add(R.id.frame, bmishow);
                fragTransac.commit();
            }
        });


    }
}