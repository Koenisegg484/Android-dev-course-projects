package com.example.applicationlifecycleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        goBack = findViewById(R.id.button4);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        Log.d("message", "Second Activity onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("message", "Second Activity onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("message", "Second Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("message", "Second Activity onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("message", "Second Activity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("message", "Second Activity onResume");
    }
}