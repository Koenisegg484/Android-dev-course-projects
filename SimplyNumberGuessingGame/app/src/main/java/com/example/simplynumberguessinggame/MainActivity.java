package com.example.simplynumberguessinggame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    RadioButton twonum, thrnum, frnum;

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

        startButton = findViewById(R.id.button);
        twonum = findViewById(R.id.twonums);
        thrnum = findViewById(R.id.thrnums);
        frnum = findViewById(R.id.frnums);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                if(!twonum.isChecked() && !thrnum.isChecked() && !frnum.isChecked()){
                    Snackbar.make(v, "Please choose an option", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                    return;
                }
                if(twonum.isChecked()){
                    intent.putExtra("num", 2);
                } else if (thrnum.isChecked()) {
                    intent.putExtra("num", 3);
                } else if (frnum.isChecked()) {
                    intent.putExtra("num", 4);
                }

                startActivity(intent);
            }
        });




    }
}