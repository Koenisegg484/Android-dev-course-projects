package com.example.topbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {
    MaterialToolbar matool;
    TextView txt;
    ImageView imgs;
    CheckBox chk1;
    Spinner spnr;
    ArrayAdapter adptr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        matool = findViewById(R.id.mattool);
        txt = findViewById(R.id.txt10);
        imgs = findViewById(R.id.imv);
        spnr = findViewById(R.id.spinner);

        adptr = ArrayAdapter.createFromResource(this, R.array.countries, android.R.layout.simple_spinner_item);
        adptr.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnr.setAdapter(adptr);

        imgs.setImageResource(R.drawable.allimgs2);

        final boolean[] checks = {false};

        imgs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checks[0]) {
                    imgs.setImageResource(R.drawable.allimgs2);
                    checks[0] = true;
                } else {
                    imgs.setImageResource(R.drawable.all3);
                    checks[0] = false;
                }
            }
        });

        matool.setOverflowIcon(AppCompatResources.getDrawable(this, R.drawable.baseline_expand_more_24));

        matool.setNavigationOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getApplicationContext(), "Navigation Icon was clicked", Toast.LENGTH_SHORT).show();
                txt.setText(R.string.navigation_icon_was_changed);
            }
        });



        matool.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item){
                if(item.getItemId() == R.id.pkb){
//                    Toast.makeText(MainActivity.this, "Share icon was clicked", Toast.LENGTH_SHORT).show();
                    Toast.makeText(MainActivity.this, R.string.share_icon_was_clicked , Toast.LENGTH_SHORT).show();
                    txt.setText(R.string.share_icon_was_clicked);
                } else if (item.getItemId() == R.id.saw) {
                    Toast.makeText(getApplicationContext(), "Edit icon was clicked", Toast.LENGTH_SHORT).show();
                    txt.setText(R.string.edit_icon_was_clicked);
                }else if (item.getItemId() == R.id.op1) {
                    Toast.makeText(getApplicationContext(), "Setting icon was clicked", Toast.LENGTH_LONG).show();
                    txt.setText(R.string.setting_icon_was_clicked);
                }else if (item.getItemId() == R.id.op2) {
                    Toast.makeText(getApplicationContext(), "Sign-out icon was clicked", Toast.LENGTH_SHORT).show();
                    txt.setText(R.string.sign_out_icon_was_clicked);
                }
                return true;
            }
        });
    }
}