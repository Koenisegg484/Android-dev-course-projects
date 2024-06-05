package com.example.gridviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gdView;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gdView = findViewById(R.id.gdview);
        fillArray();

        GridAdapter adapter = new GridAdapter(this, animalNames, images);
        gdView.setAdapter(adapter);

        gdView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You clicked " + animalNames.get(position), Toast.LENGTH_SHORT).show();
                Snackbar.make(gdView,"You clicked " + animalNames.get(position), Snackbar.LENGTH_SHORT).setAction("a", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });


    }

    private void fillArray(){
        animalNames.add("Dog");
        animalNames.add("Cat");
        animalNames.add("Bird");
        animalNames.add("Fish");
        animalNames.add("Tiger");
        animalNames.add("Lion");
        animalNames.add("Dragon");
        animalNames.add("Unicorn");
        animalNames.add("Pegasus");
        animalNames.add("Mermaid");

        images.add(R.drawable.a1);
        images.add(R.drawable.a2);
        images.add(R.drawable.a3);
        images.add(R.drawable.a4);
        images.add(R.drawable.a5);
        images.add(R.drawable.a6);
        images.add(R.drawable.a7);
        images.add(R.drawable.a8);
        images.add(R.drawable.a9);
        images.add(R.drawable.a10);
        images.add(R.drawable.a11);
    }
}