package com.example.testfirst;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    EditText edt;
    Button btn;
    TextView text;
    TextView dessert;
    TextView txv7;
    String userName;
    Button chimg;
    Button choose;
    Button forMsg;
    Button choose2;
    CheckBox chb001;
    CheckBox chb002;
    CheckBox chb003;
    CheckBox chb004;
    RadioGroup rdg;
    RadioButton rdb1;
    RadioButton rdb2;
    RadioButton rdb3;
    RadioButton rdb4;
    ToggleButton tgb;
    Spinner spinner;
    ArrayAdapter adapter;

    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.textView);
        dessert = findViewById(R.id.dessert);
        btn = findViewById(R.id.button2);
        chimg = findViewById(R.id.changeImg);
        choose = findViewById(R.id.button4);
        choose2 = findViewById(R.id.button);
        forMsg = findViewById(R.id.button3);
        edt = findViewById(R.id.editTextText);
        img = findViewById(R.id.imageView2);
        chb001 = findViewById(R.id.chb01);
        chb002 = findViewById(R.id.chb02);
        chb003 = findViewById(R.id.chb03);
        chb004 = findViewById(R.id.chb04);
        rdg = findViewById(R.id.radioGroup);
        rdb1 = findViewById(R.id.radioButton2);
        rdb2 = findViewById(R.id.radioButton3);
        rdb3 = findViewById(R.id.radioButton4);
        rdb4 = findViewById(R.id.radioButton5);
        tgb = findViewById(R.id.toggleButton);
        spinner = findViewById(R.id.spinner);



        adapter = ArrayAdapter.createFromResource(this, R.array.mealPlans, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            TextView txv8 = findViewById(R.id.textView11);
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String mewlPlans = parent.getItemAtPosition(position).toString();
                txv8.setText(mewlPlans);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                txv8.setText("Please select the meal Plan");
            }
        });

        tgb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            final ImageView imagine = findViewById(R.id.imageView);
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                imagine.setVisibility(View.VISIBLE);
                if(isChecked){
                    imagine.setImageResource(R.drawable.meme2);
                }else{
                    imagine.setImageResource(R.drawable.meme4);
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                userName = edt.getText().toString();
                text.setVisibility(View.VISIBLE);
                text.setText(userName);
                text.setBackgroundColor(Color.BLUE);
                text.setTextColor(Color.MAGENTA);

            }
        });

        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dessert.setVisibility(View.VISIBLE);
                String des = "You have chosen : \n";
                if (chb001.isChecked()){
                    des = des + "Gulaab Jaamun \n";
                }
                if (chb002.isChecked()){
                    des = des + "Rabdi \n";
                }
                if (chb003.isChecked()){
                    des = des + "Jalebi \n";
                }
                if (chb004.isChecked()){
                    des = des + "Laddoo \n";
                }
                dessert.setText(des);
            }
        });

        choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txv7 = findViewById(R.id.textView7);
                String text = "You have chosen : \n";
                if(rdb1.isChecked()){
                    text = text + "Veg Half Thaali";
                }else if(rdb2.isChecked()){
                    text = text + "Veg Full Thaali";
                }else if(rdb3.isChecked()){
                    text = text + "Non-Veg Half Thaali";
                }else if(rdb4.isChecked()){
                    text = text + "Non-Veg Full Thaali";
                }

                txv7.setVisibility(View.VISIBLE);
                txv7.setText(text);
            }
        });

        chimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img.setImageResource(R.drawable.meme1);
            }
        });

        layout = findViewById(R.id.jjk);

        forMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "This is a demo test Notification", Toast.LENGTH_LONG).show();

//                Snackbar.make(layout, "This is a snackbar message", Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener(){
//                    @Override
//                    public void onClick(View v){
//
//                    }
//                }).show();

                showDialogMessage();
            }
        });
    }
    private void showDialogMessage(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Demo Msg").setMessage("This is a demo message for the alert dialog.")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                }).setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    final ImageView imagine = findViewById(R.id.imageView);
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        imagine.setVisibility(View.VISIBLE);
                        imagine.setImageResource(R.drawable.meme5);
                    }
                }).show();
        alert.create();
    }
}