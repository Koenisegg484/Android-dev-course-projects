package com.example.simplyphotoalbum;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "album_images")
public class ImagesEntity {

    @PrimaryKey(autoGenerate = true)
    public int imageID;
    public String imageTitle;
    public String imageDesc;
    public byte[] image;

    public ImagesEntity(String imageTitle, String imageDesc, byte[] image) {
        this.imageTitle = imageTitle;
        this.imageDesc = imageDesc;
        this.image = image;
    }

    public int getImageID() {
        return imageID;
    }

    public String getImageTitle() {
        return imageTitle;
    }

    public String getImageDesc() {
        return imageDesc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
