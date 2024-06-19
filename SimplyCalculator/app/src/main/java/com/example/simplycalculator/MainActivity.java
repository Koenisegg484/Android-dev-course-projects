package com.example.simplycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button allClear, clear, sqroot, divide;
    private Button n7, n8, n9, multiply;
    private Button n4, n5, n6, subtract;
    private Button n1, n2, n3, add;
    private Button percentage, n0, decimal, equals;

    TextView historytv;
    TextView answer;
    String number = null;
    String status = null;
    String history;
    String currentResult;
    boolean operator = false;
    double firstNumber = 0, lastNumber = 0;
    boolean dot = true;
    DecimalFormat myformat = new DecimalFormat("######.######");
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

//        getSupportActionBar();

        // Reference Buttons by their IDs

        answer = findViewById(R.id.exprResult);
        historytv = findViewById(R.id.exprHistory);
        allClear = findViewById(R.id.allclear);
        clear = findViewById(R.id.clear);
        sqroot = findViewById(R.id.sqroot);
        divide = findViewById(R.id.divide);

        n7 = findViewById(R.id.n7);
        n8 = findViewById(R.id.n8);
        n9 = findViewById(R.id.n9);
        multiply = findViewById(R.id.multiply);

        n4 = findViewById(R.id.n4);
        n5 = findViewById(R.id.n5);
        n6 = findViewById(R.id.n6);
        subtract = findViewById(R.id.subtract);

        n1 = findViewById(R.id.n1);
        n2 = findViewById(R.id.n2);
        n3 = findViewById(R.id.n3);
        add = findViewById(R.id.add);

        percentage = findViewById(R.id.percentage);
        n0 = findViewById(R.id.n0);
        decimal = findViewById(R.id.decimal);
        equals = findViewById(R.id.equals);

        firstNumber = Double.parseDouble(answer.getText().toString());

        n0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("0");
            }
        });
        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("1");
            }
        });
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("2");
            }
        });
        n3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("3");
            }
        });
        n4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("4");
            }
        });
        n5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("5");
            }
        });
        n6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("6");
            }
        });
        n7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("7");
            }
        });
        n8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("8");
            }
        });
        n9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberClick("9");
            }
        });

        allClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = null;
                firstNumber = 0;
                lastNumber = 0;
                operator = false;
                status = null;
                historytv.setText("");
                answer.setText(myformat.format(firstNumber));
                dot = true;
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number == null) return;

                if(number.charAt(number.length()-1) == '.'){
                    dot = true;
                }
                number = number.substring(0, number.length()-1);
                answer.setText(number);
            }
        });

        sqroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(sqroot, "This function is under progress.", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = historytv.getText().toString();
                currentResult = answer.getText().toString();
                historytv.setText(String.format("%s%s÷", history, currentResult));
                if(operator){
                    if (status == "m"){
                        multiplication();
                    }else if (status == "a"){
                        addition();
                    }else if (status == "s"){
                        subtraction();
                    }else {
                        division();
                    }
                }
                status = "d";
                operator = false;
                number = null;
                dot = false;
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = historytv.getText().toString();
                currentResult = answer.getText().toString();
                historytv.setText(String.format("%s%sx", history, currentResult));
                if(operator){
                    if (status == "a"){
                        addition();
                    }else if (status == "d"){
                        division();
                    }else if (status == "s"){
                        subtraction();
                    }else {
                        multiplication();
                    }
                }
                status = "m";
                operator = false;
                number = null;
                dot = false;
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = historytv.getText().toString();
                currentResult = answer.getText().toString();
                historytv.setText(String.format("%s%s+", history, currentResult));
                if(operator){
                    if (status == "m"){
                        multiplication();
                    }else if (status == "d"){
                        division();
                    }else if (status == "s"){
                        subtraction();
                    }else {
                        addition();
                    }
                }
                status = "a";
                operator = false;
                number = null;
                dot = false;

            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = historytv.getText().toString();
                currentResult = answer.getText().toString();
                historytv.setText(String.format("%s%s-", history, currentResult));
                if(operator){
                    if (status == "m"){
                        multiplication();
                    }else if (status == "d"){
                        division();
                    }else if (status == "a"){
                        addition();
                    }else {
                        subtraction();
                    }
                }
                status = "s";
                operator = false;
                number = null;
                dot = false;

            }
        });

        decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dot){
                    if(number == null){
                        number = "0.";
                    }else{
                        number = number + ".";
                    }
                    answer.setText(number);
                    dot = false;
                }


            }
        });

        percentage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(sqroot, "This function is under progress.", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();

            }
        });

        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history = historytv.getText().toString();
                if(history.isEmpty()){
                    return;
                }
                if(history.charAt(history.length()-1) == '=' || history.charAt(history.length()-2) == '='){
                    return;
                }
                currentResult = answer.getText().toString();
                historytv.setText(String.format("%s%s=\n", history, currentResult));
                if(operator){
                    if (status == "m"){
                        multiplication();
                    }else if (status == "d"){
                        division();
                    }else if (status == "s"){
                        subtraction();
                    }else if (status == "a"){
                        addition();
                    }else {
                        firstNumber = Double.parseDouble(answer.getText().toString());
                    }
                }
                operator = false;
                number = null;
                lastNumber = 0;
            }
        });
    }

    private void numberClick(String num){
        if(number == null){
            number = num;
        }else{
            number = number+num;
        }

        answer.setText(number);
        operator = true;
    }

    private void addition(){
        lastNumber = Double.parseDouble(answer.getText().toString());
        firstNumber = firstNumber + lastNumber;
        answer.setText(myformat.format(firstNumber));
    }

    private void subtraction(){
        if(firstNumber == 0){
            firstNumber = Double.parseDouble(answer.getText().toString());
        }else{
            lastNumber = Double.parseDouble((answer.getText().toString()));
            firstNumber = firstNumber - lastNumber;
        }

        answer.setText(myformat.format(firstNumber));
    }

    private void multiplication(){
        if(firstNumber == 0){
            firstNumber = 1;
        }
        lastNumber = Double.parseDouble(answer.getText().toString());
        firstNumber = firstNumber * lastNumber;

        answer.setText(myformat.format(firstNumber));
    }

    private void division(){
        lastNumber = Double.parseDouble(answer.getText().toString());
        if(lastNumber == 0){
            answer.setText(R.string.cannot_divide_by_0);
        }else{
            firstNumber = firstNumber / lastNumber;
        }

        answer.setText(myformat.format(firstNumber));
    }
}