package com.example.simplyphotoalbum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private FloatingActionButton fab;
    private ActivityResultLauncher<Intent> activityResultLauncherforAddImage;
    private ActivityResultLauncher<Intent> activityResultLauncherforUpdateImage;

    AlbumViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //      Register Activity
        registerActivityforSaveImage();
        registerActivityFofUpdateImage();

        recView = findViewById(R.id.rv);
        fab = findViewById(R.id.fab);

        recView.setLayoutManager(new LinearLayoutManager(this));
        RVAdapter rvAdapter = new RVAdapter();
        recView.setAdapter(rvAdapter);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(AlbumViewModel.class);

        viewModel.getAllImages().observe(MainActivity.this, new Observer<List<ImagesEntity>>() {
            @Override
            public void onChanged(List<ImagesEntity> imagesEntities) {
                rvAdapter.setAlbumImages(imagesEntities);
            }
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddImageActivity.class);
                activityResultLauncherforAddImage.launch(i);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.delete(rvAdapter.getPosImage(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Image Delelted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recView);

        rvAdapter.setListener(new RVAdapter.OnClickInterface() {
            @Override
            public void onImageClickListener(ImagesEntity images) {
                Intent i = new Intent(MainActivity.this, UpdateImageActivity.class);
                i.putExtra("id", images.getImageID());
                i.putExtra("title", images.getImageTitle());
                i.putExtra("desc", images.getImageDesc());
                i.putExtra("imgg", images.getImage());
                activityResultLauncherforUpdateImage.launch(i);

            }
        });
    }


    public void registerActivityforSaveImage(){
        activityResultLauncherforAddImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        int resultcode = o.getResultCode();
                        Intent data = o.getData();
                        if(resultcode == RESULT_OK && data != null){
                            String t = data.getStringExtra("title");
                            String d = data.getStringExtra("desc");
                            byte[] i = data.getByteArrayExtra("image");
                            ImagesEntity img = new ImagesEntity(t,d,i);
                            viewModel.insert(img);
                        }

                    }
                });
    }

    public void registerActivityFofUpdateImage(){
        activityResultLauncherforUpdateImage = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        int resultcode = o.getResultCode();
                        Intent data = o.getData();
                        if(resultcode == RESULT_OK && data != null){
                            int id = data.getIntExtra("id", -1);
                            String t = data.getStringExtra("updatetitle");
                            String d = data.getStringExtra("updatedesc");
                            byte[] i = data.getByteArrayExtra("image");
                            ImagesEntity img = new ImagesEntity(t,d,i);
                            img.setImageID(id);
                            viewModel.update(img);
                        }
                    }
                });
    }
}