package com.example.testfirst;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ConstraintsLayoutPractice extends AppCompatActivity {

    ListView list;
    String[] countries;
    ArrayAdapter <String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constraints_layout_practice);

        list = findViewById(R.id.list);
        countries = getResources().getStringArray(R.array.countries);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                String name = parent.getItemAtPosition(position).toString();
                name += " was clicked";
//                Toast.makeText(ConstraintsLayoutPractice.this, name, Toast.LENGTH_SHORT).show();
                Snackbar.make(list, name, Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });

        }
}