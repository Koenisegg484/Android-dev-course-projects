package com.example.sendingdataf2a;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    TextView name, email;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.userEmail);
        FragmentManager fmagr = getSupportFragmentManager();
        FragmentTransaction ftrnsac = fmagr.beginTransaction();

        testFrag frag = new testFrag();
        ftrnsac.add(R.id.linear2, frag);
        ftrnsac.commit();
    }

    public void takeData(String uname, String uemail){
        System.out.println(uname + uemail);

        name.setText(uname);
        email.setText(uemail);

        System.out.println("Changed");
    }
}