package com.example.simplynotetaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateNoteActivity extends AppCompatActivity {

    EditText titlebar;
    EditText contentbox;
    Button save;
    Button cancel;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Edit Note");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setContentView(R.layout.activity_update_note);

        titlebar = findViewById(R.id.updateTitle);
        contentbox = findViewById(R.id.updateContent);
        save = findViewById(R.id.buttonSave);
        cancel = findViewById(R.id.cancel);

        getData();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateNote();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Nothing to Update", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    public void updateNote() {
        String titleLast = titlebar.getText().toString();
        String contentLast = contentbox.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("titleLast", titleLast);
        intent.putExtra("contentLast", contentLast);
        intent.putExtra("id", id); // Make sure to pass the ID back as well
        setResult(RESULT_OK, intent);
        finish();
    }


    // Method to retrieve data from Intent extras
    public void getData() {
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getIntExtra("id", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            // Set retrieved data to EditText fields
            titlebar.setText(title);
            contentbox.setText(content);
        }
    }
}