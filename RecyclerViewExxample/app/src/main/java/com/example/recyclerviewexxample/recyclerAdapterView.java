package com.example.recyclerviewexxample;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

public class recyclerAdapterView  extends RecyclerView.Adapter<recyclerAdapterView.countryViewHolder>{
    private ArrayList<String> countryNameList;
    private ArrayList<String> detailsList;
    private ArrayList<Integer> imageList;
    private Context context;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public recyclerAdapterView(ArrayList<String> countryNameList, ArrayList<String> detailsList, ArrayList<Integer> imageList, Context context) {
        this.countryNameList = countryNameList;
        this.detailsList = detailsList;
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public countryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design, parent, false);

        return new countryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull countryViewHolder holder, int position) {
        holder.tvCountryName.setText(countryNameList.get(position));
        holder.tvViewDetails.setText(detailsList.get(position));
        holder.flag.setImageResource(imageList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.countryNameList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String itemText);
    }

    public class countryViewHolder extends RecyclerView.ViewHolder{
        private TextView tvCountryName, tvViewDetails;
        private ImageView flag;
        private CardView cardView;

        public countryViewHolder(@NonNull View itemView){
            super(itemView);

            tvCountryName = itemView.findViewById(R.id.countryName);
            tvViewDetails = itemView.findViewById(R.id.countryDetails);
            flag = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
//            cardView.setOnClickListener(view -> Toast.makeText(context, "You clicked on " + tvCountryName.getText().toString(), Toast.LENGTH_SHORT).show());
            cardView.setOnClickListener(view -> Snackbar.make(cardView,"You clicked on "+tvCountryName.getText().toString(), Snackbar.LENGTH_SHORT).setAction("x", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            }).show());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.onItemClick(tvCountryName.getText().toString());
                    }
                }
            });
        }
    }
}