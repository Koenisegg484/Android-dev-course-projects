package com.example.simplyphotoalbum;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ImageHolder> {

    List<ImagesEntity> albumImages = new ArrayList<>();
    private OnClickInterface listener;

    public void setListener(OnClickInterface listener) {
        this.listener = listener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setAlbumImages(List<ImagesEntity> albumImages) {
        this.albumImages = albumImages;
        notifyDataSetChanged();
    }

    public  ImagesEntity getPosImage(int position){
        return albumImages.get(position);
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_card, parent, false);

        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

        ImagesEntity imgs = albumImages.get(position);
        holder.setTitle.setText(imgs.getImageTitle());
        holder.setDesc.setText(imgs.getImageDesc());
        holder.setImage.setImageBitmap(BitmapFactory.decodeByteArray(imgs.getImage(), 0,imgs.getImage().length));


    }

    @Override
    public int getItemCount() {
        return albumImages.size();
    }

    public interface OnClickInterface {
        void onImageClickListener(ImagesEntity images);
    }

    public class ImageHolder extends RecyclerView.ViewHolder{
        ImageView setImage;
        TextView setTitle, setDesc;

        public ImageHolder(@NonNull View itemView) {
            super(itemView);

            setImage = itemView.findViewById(R.id.imageView);
            setTitle = itemView.findViewById(R.id.imgTitle);
            setDesc = itemView.findViewById(R.id.imgDesc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onImageClickListener(albumImages.get(position));
                    }
                }
            });

        }

    }

}
