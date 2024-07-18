package com.example.simplyinfobook.fragmentsonly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplyinfobook.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Fragment_Country extends Fragment {


    public static Fragment_Country newInstance(){
        return new Fragment_Country();
    }
    ImageView country_flag_image;
    ProgressBar prgressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_united_kingdom, container, false);

        country_flag_image = view.findViewById(R.id.countryImage);
        prgressBar = view.findViewById(R.id.progressbarcountry);

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Flag_of_the_United_Kingdom_%281-2%29.svg/" +
                "125px-Flag_of_the_United_Kingdom_%281-2%29.svg.png").into(country_flag_image, new Callback() {
            @Override
            public void onSuccess() {
                prgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                prgressBar.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }
}
