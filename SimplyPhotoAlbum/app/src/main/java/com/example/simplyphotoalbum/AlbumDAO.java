package com.example.simplyphotoalbum;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AlbumDAO{


    @Insert
    void insert(ImagesEntity image);

    @Delete
    void delete(ImagesEntity image);


    @Update
    void update(ImagesEntity image);

    @Query("SELECT * FROM album_images ORDER BY imageID ASC")
    LiveData<List<ImagesEntity>> getAllImages();

}
