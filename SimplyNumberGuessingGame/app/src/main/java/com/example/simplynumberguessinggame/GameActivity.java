package com.example.simplynumberguessinggame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView livesleft, prevguess, hinter, tvLives, tvPG;
    EditText userInput;
    Button confirm;
    int numberChoice;
    int lives = 10;
    Random rnd = new Random();
    int answer = 0, userAnswer;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        livesleft = findViewById(R.id.livesleft);
        prevguess = findViewById(R.id.preGuess);
        hinter = findViewById(R.id.hinter);
        confirm = findViewById(R.id.btnSend);
        userInput = findViewById(R.id.userInput);
        tvLives = findViewById(R.id.textView3);
        tvPG = findViewById(R.id.textView4);

        numberChoice = getIntent().getIntExtra("num", 2);

        answer = rnd.nextInt((int) Math.pow(10,numberChoice)-10)+10;

        hinter.setText(R.string.enter_your_guess_below);

        confirm.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if (userInput.getText().toString().isEmpty()){
                    hinter.setText("Please Guess below.");
                    return;
                }
                userAnswer = Integer.parseInt(userInput.getText().toString());

                if(lives > 0) {

                    if (answer == userAnswer) {
                        Snackbar.make(v, "Congratulations you won.", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        }).show();
                        startActivity(new Intent(GameActivity.this, MainActivity.class));

                    } else if (userAnswer > answer) {
                        hinter.setText("Guess Something Lower.");
                    } else {
                        hinter.setText("Guess Something greater.");
                    }

                    lives--;
                    livesleft.setText(Integer.toString(lives));
                    prevguess.setText(Integer.toString(userAnswer));
                    userInput.setText("");
                    if(lives==9){
                        hinter.setVisibility(View.VISIBLE);
                        tvPG.setVisibility(View.VISIBLE);
                        tvLives.setVisibility(View.VISIBLE);
                        prevguess.setVisibility(View.VISIBLE);
                        livesleft.setVisibility(View.VISIBLE);
                    }

                }else {
                    Snackbar.make(v, "UH.OHH, you lost all your lives.", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                    startActivity(new Intent(GameActivity.this, MainActivity.class));
                }

            }
        });





    }
}