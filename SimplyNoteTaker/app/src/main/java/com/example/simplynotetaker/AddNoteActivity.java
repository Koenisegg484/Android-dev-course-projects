package com.example.simplynotetaker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    EditText titlebar;
    EditText contentbox;
    Button save;
    Button cancel;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Note");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_note);

        titlebar = findViewById(R.id.editTitle);
        contentbox = findViewById(R.id.editContent);
        save = findViewById(R.id.buttonOK);
        cancel = findViewById(R.id.buttonCancel);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Nothing to Save", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    public void saveNote(){
        String title = titlebar.getText().toString();
        String content = contentbox.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("noteTitle", title);
        intent.putExtra("noteContent", content);
        setResult(RESULT_OK, intent);
//        System.out.println(title + "  " + content);
//        Log.d("mess", title);
        finish();
    }
}

