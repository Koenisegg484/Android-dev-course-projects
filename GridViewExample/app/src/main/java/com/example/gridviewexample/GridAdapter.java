package com.example.gridviewexample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gridviewexample.R.id;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> animalNames = new ArrayList<>();
    ArrayList<Integer> images = new ArrayList<>();

    public GridAdapter(Context context, ArrayList<String> animalNames, ArrayList<Integer> images) {
        this.context = context;
        this.animalNames = animalNames;
        this.images = images;
    }

    @Override
    public int getCount() {
        return animalNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        @SuppressLint("ViewHolder") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_layout, parent, false);

        ImageView imgs = view.findViewById(id.imageview22);
        TextView txtv = view.findViewById(R.id.textview22);

        imgs.setImageResource(images.get(position));
        txtv.setText(animalNames.get(position));

        return  view;
    }
}