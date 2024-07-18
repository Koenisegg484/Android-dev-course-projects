package com.example.simplynotetaker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewModel viewModel;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<Intent> activityResultLauncherforUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerNote();
        registerNoteforUpdate();
        RecyclerView allNotes = findViewById(R.id.allNotes);
        allNotes.setLayoutManager(new LinearLayoutManager(this));

        NoteAdapter adapter = new NoteAdapter();


        allNotes.setAdapter(adapter);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(ViewModel.class);

        viewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
//                update Recycler View
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));
                Toast.makeText(getApplicationContext(), "Note Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(allNotes);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Note note) {
                Intent intent = new Intent(MainActivity.this, UpdateNoteActivity.class);
                intent.putExtra("id", note.getId());
                intent.putExtra("title", note.getTitle());
                intent.putExtra("content", note.getDescription());

                activityResultLauncherforUpdate.launch(intent);
            }
        });
    }

    public void registerNoteforUpdate(){
//        activityResultLauncherforUpdate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
//                , new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult o) {
//                        int resultCode = o.getResultCode();
//                        Intent data = o.getData();
//                        if (resultCode == RESULT_OK && data != null){
//                            String titl = data.getStringExtra("titleLast");
//                            String cont = data.getStringExtra("contentLast");
//                            int id = data.getIntExtra("id", -1);
//
//                            Note noe = new Note(titl, cont);
//                            noe.setId(id);
//                            viewModel.update(noe);
//                        }
//                    }
//                });

        activityResultLauncherforUpdate = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == RESULT_OK) {
                            Intent data = result.getData();
                            if (data != null) {
                                String titleLast = data.getStringExtra("titleLast");
                                String contentLast = data.getStringExtra("contentLast");
                                int id = data.getIntExtra("id", -1);

                                // Create a new Note object with updated data
                                Note updatedNote = new Note(titleLast, contentLast);
                                updatedNote.setId(id);

                                // Update the note in ViewModel
                                viewModel.update(updatedNote);
                            }
                        }
                    }
                });

    }

    public void registerNote(){
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
                , new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        int resultCode = o.getResultCode();
                        Intent data = o.getData();

                        if(resultCode == RESULT_OK && data != null){
                            String title = data.getStringExtra("noteTitle");
                            String content = data.getStringExtra("noteContent");
                            Note note = new Note(title, content);
                            System.out.println(note);
                            Log.d("inMA", note.title);

                            viewModel.insert(note);
                        }
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menufile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuId = R.id.mainMenu;

        if (item.getItemId() == menuId) {
            Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
//            startActivityForResult(intent, 1);
            activityResultLauncher.launch(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 1 && resultCode == RESULT_OK && data != null){
//            String title = data.getStringExtra("noteTitle");
//            String content = data.getStringExtra("noteContent");
//
//            Note note = new Note(title, content);
//
//            viewModel.insert(note);
//        }
//    }
}