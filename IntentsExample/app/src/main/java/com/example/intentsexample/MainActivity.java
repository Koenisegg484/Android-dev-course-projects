package com.example.intentsexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edt;
    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = findViewById(R.id.edttxt);
        go = findViewById(R.id.intentButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edt.getText().toString();
                Intent intent = new Intent(MainActivity.this, SecondActivity2.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}