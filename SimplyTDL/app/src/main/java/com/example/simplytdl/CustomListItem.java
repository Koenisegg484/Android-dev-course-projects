package com.example.simplytdl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class CustomListItem extends BaseAdapter {

    ArrayList<String> items;
    Context context;
    LayoutInflater inflater;

    CustomListItem(Context ctx, ArrayList<String> tasks){
        this.context = ctx;
        this.items = tasks;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams", "SetTextI18n"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_custom_list_view, null);
        TextView txt = (TextView) convertView.findViewById(R.id.text1);
        txt.setText(items.get(position));
        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
        TextView serNum = convertView.findViewById(R.id.serialNo);
        serNum.setText(Integer.toString(position+1)+".");
        img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle(R.string.deletetitle);
                alertDialog.setMessage(R.string.deletemessage);
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        items.remove(position);
                        CustomListItem.super.notifyDataSetChanged();
                        fileHelper.writeData(items, context.getApplicationContext());
                    }
                });

                AlertDialog alert = alertDialog.create();
                alert.show();

            }
        });

        return convertView;
    }
}
