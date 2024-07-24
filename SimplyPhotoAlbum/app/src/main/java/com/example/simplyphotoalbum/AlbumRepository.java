package com.example.simplyphotoalbum;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AlbumRepository {

    ExecutorService executor = Executors.newSingleThreadExecutor();
    private AlbumDAO albumDAO;
    private LiveData<List<ImagesEntity>> imagesList;

    public AlbumRepository (Application application){

        AlbumDatabase database = AlbumDatabase.getInstance(application);
        albumDAO = database.albumDAO();
        imagesList = albumDAO.getAllImages();
    }

    public void insert(ImagesEntity images){

//        new InsertImageAsyncTask(albumDAO).execute(images);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                albumDAO.insert(images);
            }
        });

    }

    public void delete(ImagesEntity images){

//        new DeleteImageAsyncTask(albumDAO).execute(images);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                albumDAO.delete(images);
            }
        });
    }

    public void update(ImagesEntity images){

//        new UpdateImageAsyncTask(albumDAO).execute(images);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                albumDAO.update(images);
            }
        });
    }

    public LiveData<List<ImagesEntity>> getAllImages(){
        return imagesList;
    }

//    private static class InsertImageAsyncTask extends AsyncTask<ImagesEntity, Void , Void>{
//
//        AlbumDAO dao;
//        public InsertImageAsyncTask(AlbumDAO dao) {
//            this.dao = dao;
//        }
//        @Override
//        protected Void doInBackground(ImagesEntity... imagesEntities) {
//            dao.insert(imagesEntities[0]);
//
//            return null;
//        }
//    }
//
//    private static class DeleteImageAsyncTask extends AsyncTask<ImagesEntity, Void , Void>{
//
//        AlbumDAO dao;
//        public DeleteImageAsyncTask(AlbumDAO dao) {
//            this.dao = dao;
//        }
//        @Override
//        protected Void doInBackground(ImagesEntity... imagesEntities) {
//            dao.delete(imagesEntities[0]);
//
//            return null;
//        }
//    }
//
//    private static class UpdateImageAsyncTask extends AsyncTask<ImagesEntity, Void , Void>{
//
//        AlbumDAO dao;
//        public UpdateImageAsyncTask(AlbumDAO dao) {
//            this.dao = dao;
//        }
//        @Override
//        protected Void doInBackground(ImagesEntity... imagesEntities) {
//            dao.update(imagesEntities[0]);
//
//            return null;
//        }
//    }
}
