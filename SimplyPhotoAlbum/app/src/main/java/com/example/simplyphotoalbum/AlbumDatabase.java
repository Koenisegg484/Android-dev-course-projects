package com.example.simplyphotoalbum;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = ImagesEntity.class, version = 1)
public abstract class AlbumDatabase extends RoomDatabase {

    private static AlbumDatabase instance;
    public abstract AlbumDAO albumDAO();

    public static  synchronized AlbumDatabase getInstance(Context context){
        if (instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext()
            ,AlbumDatabase.class, "album_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }



}
