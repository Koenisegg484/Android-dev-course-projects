package com.example.topbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ConstraintsActivity extends AppCompatActivity {


    ListView lstv;
    String[] countries;
    ArrayAdapter<String> adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraints);

        lstv = findViewById(R.id.listview);

        countries = getResources().getStringArray(R.array.countries);

        adpter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);

        lstv.setAdapter(adpter);

        lstv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country_name = parent.getItemAtPosition(position).toString();

                String textMessage = country_name + "has been Selected as your Country.";

//                Snackbar.make(getApplicationContext(), country_name + "has been Selected as your Country.", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                }).show();
                Toast.makeText(ConstraintsActivity.this, textMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}