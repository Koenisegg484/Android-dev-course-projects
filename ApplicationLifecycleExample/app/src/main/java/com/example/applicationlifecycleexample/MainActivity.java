package com.example.applicationlifecycleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button bt1;
    Button bt2;
    Button activityChanger;
    TextView tv01;
    TextView tv02;

    int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt1 = findViewById(R.id.button);
        bt2 = findViewById(R.id.button2);
        activityChanger = findViewById(R.id.button3);
        tv01 = findViewById(R.id.textView);
        tv02 = findViewById(R.id.toCount);



        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv01.setText("Button 1 was clicked.");
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                tv01.setText("Button 2 was clicked.");
            }
        });

        activityChanger.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
//                if(counter >= 1){
//                    tv02.setVisibility(View.VISIBLE);
//                }
//                counter++;
//                tv02.setText(counter);
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(i);
            }
        });
        Log.d("message", "First Activity onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("message", "First Activity onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("message", "First Activity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("message", "First Activity onStop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("message", "First Activity onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("message", "First Activity onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("message", "First Activity onResume");
    }
}