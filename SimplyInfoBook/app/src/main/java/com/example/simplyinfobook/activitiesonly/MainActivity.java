package com.example.simplyinfobook.activitiesonly;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplyinfobook.adaptersonly.AdapterClass;
import com.example.simplyinfobook.ModelClass;
import com.example.simplyinfobook.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ModelClass> mcArray = new ArrayList<ModelClass>();
    private AdapterClass adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclercardview);

//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        ModelClass mc1 = new ModelClass("countries", "The Countries");
        ModelClass mc2 = new ModelClass("leaders", "The Leaders");
        ModelClass mc3 = new ModelClass("museums", "The Museums");
        ModelClass mc4 = new ModelClass("wonders", "The Wonders");

        mcArray.add(mc1);
        mcArray.add(mc2);
        mcArray.add(mc3);
        mcArray.add(mc4);

        adapter = new AdapterClass(mcArray, this);

        recyclerView.setAdapter(adapter);
    }
}