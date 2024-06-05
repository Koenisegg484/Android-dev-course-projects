package com.example.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName;
    EditText userMesage;
    Button btn;
    CheckBox chkbx;

    String name;
    String message;
    Boolean checked;
    int count = 0;

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.edt1);
        userMesage = findViewById(R.id.edt2);
        btn = findViewById(R.id.btn);
        chkbx = findViewById(R.id.chk1);

        chkbx.setText("Remember Me !");
        btn.setText("Check SharedPreferences.");

        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                count = count + 1;
                btn.setText(""+count);
            }
        });

        retrieveData();
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveData();
    }

    public void saveData(){
        sp = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        name = userName.getText().toString();
        message = userMesage.getText().toString();
        checked = chkbx.isChecked();

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", name);
        editor.putString("message", message);
        editor.putInt("count", count);
        editor.putBoolean("checked", checked);
        editor.apply();
        Toast.makeText(this, "The data has been saved in the shared preferences.", Toast.LENGTH_SHORT).show();
    }


    public void retrieveData(){
        sp = getSharedPreferences("saveData", Context.MODE_PRIVATE);
        name = sp.getString("name", null);
        message = sp.getString("message", null);
        checked = sp.getBoolean("checked", false);
        count = sp.getInt("count", 0);

        userName.setText(name);
        userMesage.setText(message);
        chkbx.setChecked(checked);
        btn.setText(count);
//        Toast.makeText(this, "The values have been retrieved", Toast.LENGTH_SHORT).show();
    }
}