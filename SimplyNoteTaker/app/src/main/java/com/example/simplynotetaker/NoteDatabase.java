package com.example.simplynotetaker;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static  RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

//            new PopulateDBAsyncTask(instance).execute();

            NoteDAO noteDAO = instance.noteDAO();

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    noteDAO.insert(new Note("Title 001", "This is test text..."));
                    noteDAO.insert(new Note("Title 002", "This is test text..."));
                    noteDAO.insert(new Note("Title 003", "This is test text..."));
                }
            });
        }



//        executor.execute(new Runnable(){
//
//            public void run(){
//                noteDAO.insert(new Note("Title 001", "This is test text..."));
//                noteDAO.insert(new Note("Title 002", "This is test text..."));
//                noteDAO.insert(new Note("Title 003", "This is test text..."));
//            }
//
//        });


//        new PopulateDBAsyncTask(instance).execute();
    };


    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void, Void>{
        private  NoteDAO noteDAO;

        public PopulateDBAsyncTask(NoteDatabase noteDatabase) {
            noteDAO = noteDatabase.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("Title 001", "This is test text..."));
            noteDAO.insert(new Note("Title 002", "This is test text..."));
            noteDAO.insert(new Note("Title 003", "This is test text..."));
            return null;
        }
    }
}
