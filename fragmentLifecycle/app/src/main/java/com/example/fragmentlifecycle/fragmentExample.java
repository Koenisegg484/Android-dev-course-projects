package com.example.fragmentlifecycle;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentExample extends Fragment {
    @Nullable

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("Fragment", "This is the onAttach method");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("Fragment", "This is the onCreateView method");
        return inflater.inflate(R.layout.fragment_new, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Fragment", "This is the onCreate method");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("Fragment", "This is the onViewCreated method");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i("Fragment", "This is the onStart method");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("Fragment", "This is the onResume method");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("Fragment", "This is the onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("Fragment", "This is the onStop method");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i("Fragment", "This is the onDestroyView method");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Fragment", "This is the onDestroy method");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("Fragment", "This is the onDetach method");
    }
}
