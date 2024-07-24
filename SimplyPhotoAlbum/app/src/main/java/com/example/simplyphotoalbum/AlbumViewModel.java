package com.example.simplyphotoalbum;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AlbumViewModel extends AndroidViewModel {

    LiveData<List<ImagesEntity>> albumImages;
    AlbumRepository albumRepository;

    public AlbumViewModel(@NonNull Application application) {
        super(application);
        albumRepository = new AlbumRepository(application);
        albumImages = albumRepository.getAllImages();

    }

    public void insert(ImagesEntity image){
        albumRepository.insert(image);
    }

    public void update(ImagesEntity image){
        albumRepository.update(image);
    }

    public void delete(ImagesEntity image){
        albumRepository.delete(image);
    }

    public LiveData<List<ImagesEntity>> getAllImages(){
        return albumImages;
    }
}
