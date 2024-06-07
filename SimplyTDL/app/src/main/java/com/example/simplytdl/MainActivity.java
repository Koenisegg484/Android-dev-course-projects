package com.example.simplytdl;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText inputTasks;
    Button addTasks;
    ListView tasksListview;
    ArrayList<String> tasklist;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        getSupportActionBar().setLogo(R.mipmap.logo);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        inputTasks = findViewById(R.id.addtask);
        addTasks = findViewById(R.id.addbutton);
        tasksListview = findViewById(R.id.taskslist);

        tasklist = fileHelper.readData(this);

        CustomListItem custom = new CustomListItem(this, tasklist);

        tasksListview.setAdapter(custom);

        addTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = inputTasks.getText().toString();
                if (task.isEmpty()){
                    Toast.makeText(MainActivity.this, R.string.tstmsg1, Toast.LENGTH_SHORT).show();
                }else {
                    tasklist.add(task);
                    inputTasks.setText("");
                    fileHelper.writeData(tasklist, getApplicationContext());
                    custom.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, R.string.tstmsg2, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}