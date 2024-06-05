package com.example.recyclerviewexxample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private ArrayList<String> countryNameList = new ArrayList<>();
    private ArrayList<String> detailsList = new ArrayList<>();
    private ArrayList<Integer> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recView = findViewById(R.id.recView);
        recView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        countryNameList.add("India");
        countryNameList.add("Isle Of Man");
        countryNameList.add("Japan");
        countryNameList.add("Nepal");
        countryNameList.add("Viettnam");
//        System.out.println(countryNameList);

        detailsList.add("India is situated in Asia.");
        detailsList.add("Isle of Man is situated in Europe.");
        detailsList.add("Japan is situated in Asia");
        detailsList.add("Nepal is situated in Asia.");
        detailsList.add("Vietnam is situated in Asia.");
//        System.out.println(detailsList);

        imageList.add(R.drawable.indiaflag);
        imageList.add(R.drawable.isleifofman);
        imageList.add(R.drawable.japanflag);
        imageList.add(R.drawable.nepalflag);
        imageList.add(R.drawable.vietnamflag);
//        System.out.println(imageList);

        recyclerAdapterView adapter = new recyclerAdapterView(countryNameList, detailsList, imageList, MainActivity.this);
        recView.setAdapter(adapter);

        adapter.setOnItemClickListener(new recyclerAdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(String itemText) {
                Snackbar.make(findViewById(R.id.jjk),"You clicked on "+itemText, Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

            }
        });




    }
}