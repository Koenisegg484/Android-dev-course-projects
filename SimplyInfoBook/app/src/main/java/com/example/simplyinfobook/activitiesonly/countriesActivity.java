package com.example.simplyinfobook.activitiesonly;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.simplyinfobook.R;
import com.example.simplyinfobook.adaptersonly.ViewPagerAdapterCountries;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class countriesActivity extends AppCompatActivity {

    TabLayout countries_tab;
    ViewPager2 countryViewPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countries);

        countries_tab = findViewById(R.id.countries_tab_layout);
        countryViewPage = findViewById(R.id.viewPager);

        ViewPagerAdapterCountries adapter = new ViewPagerAdapterCountries(getSupportFragmentManager(), getLifecycle());

        countryViewPage.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(countries_tab, countryViewPage, true, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("United Kingdom");
                        break;
                }
            }
        });

        tabLayoutMediator.attach();
    }
}