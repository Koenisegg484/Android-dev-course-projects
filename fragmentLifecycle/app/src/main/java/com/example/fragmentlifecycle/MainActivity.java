package com.example.fragmentlifecycle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Message", "First activity created");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        Log.d("Message mainActivity", "This is the onCreateView");
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onStart() {
        Log.d("Message mainActivity", "This is the onStart method");
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Message mainActivity", "This is the onResume method");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Message mainActivity", "This is the onRestart method");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Message mainActivity", "This is the onStop method");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Message mainActivity", "This is the onPause method");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Message mainActivity", "This is the onDestroy method");
    }
}