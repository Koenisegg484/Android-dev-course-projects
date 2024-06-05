package com.example.intentsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity2 extends AppCompatActivity {

    TextView usrName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second2);

        usrName = findViewById(R.id.textView2);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        usrName.setText(name);
    }
}