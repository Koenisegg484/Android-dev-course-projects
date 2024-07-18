package com.example.simplyinfobook.adaptersonly;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplyinfobook.ModelClass;
import com.example.simplyinfobook.R;
import com.example.simplyinfobook.activitiesonly.countriesActivity;
import com.example.simplyinfobook.activitiesonly.leadersActivity;
import com.example.simplyinfobook.activitiesonly.museumsActivity;
import com.example.simplyinfobook.activitiesonly.wondersActivity;

import java.util.ArrayList;

public class AdapterClass extends RecyclerView.Adapter <AdapterClass.CardViewHolder>{

    private ArrayList<ModelClass> modelList;
    private Context context;
    public AdapterClass(ArrayList<ModelClass> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ciew = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardsdesign, parent, false);
        return new CardViewHolder(ciew);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ModelClass model = modelList.get(position);
        holder.txtview.setText(model.getCategoryName());
        holder.imgview.setImageResource(context.getResources().getIdentifier(model.getImageName(), "drawable", context.getPackageName()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    Intent intent = new Intent(context, countriesActivity.class);
                    context.startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(context, leadersActivity.class);
                    context.startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(context, museumsActivity.class);
                    context.startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(context, wondersActivity.class);
                    context.startActivity(intent);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgview;
        private TextView txtview;
        private CardView cardView;
        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            imgview = itemView.findViewById(R.id.cardimage);
            txtview = itemView.findViewById(R.id.cardtext);
            cardView = itemView.findViewById(R.id.cardview);


        }


    }
}
