package com.example.simplyphotoalbum;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;

public class AddImageActivity extends AppCompatActivity {
    EditText setTitle;
    EditText setDesc;
    Button saveImg;
    ImageView img;
    private Bitmap BMimg;
    private Bitmap scaledImage;
    ActivityResultLauncher<Intent> activityResultLauncherForSaveImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Add Image");
        setContentView(R.layout.activity_add_image);

//        Register activity
        registerActivityforSaveImage();
        setTitle = findViewById(R.id.addImageTitle);
        setDesc = findViewById(R.id.addImageDescription);
        saveImg = findViewById(R.id.addImage);
        img = findViewById(R.id.imageView2);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String permission;

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ){
                    permission = Manifest.permission.READ_MEDIA_IMAGES;
                }else {
                    permission = Manifest.permission.READ_EXTERNAL_STORAGE;
                }

                if(ContextCompat.checkSelfPermission(AddImageActivity.this, permission) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(AddImageActivity.this, new String[]{permission}, 1);
                }else {
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncherForSaveImage.launch(intent);
                }

            }
        });

        saveImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(BMimg == null){
                    Toast.makeText(AddImageActivity.this, "PLEASE SELECT AN IMAGE", Toast.LENGTH_SHORT).show();
                }else {
                    String title = setTitle.getText().toString();
                    String desc = setDesc.getText().toString();
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    scaledImage = makeSmall(BMimg, 300);
                    scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
                    byte[] image = outputStream.toByteArray();

                    Intent intent = new Intent(AddImageActivity.this, MainActivity.class);
                    intent.putExtra("title", title);
                    intent.putExtra("desc", desc);
                    intent.putExtra("image", image);
                    setResult(RESULT_OK, intent);
                    finish();
                }
             }
        });
    }

    private Bitmap makeSmall(Bitmap image, int maxSize){
        int c_width = image.getWidth();
        int c_height = image.getHeight();

        float ratio = (float) c_width/ (float) c_height;

        if(ratio > maxSize){
            c_width = maxSize;
            c_height = (int) (c_width/ratio);

        }else {
            c_height = maxSize;
            c_width = (int) (c_height * ratio);
        }

        return  Bitmap.createScaledBitmap(image, c_width, c_height, true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncherForSaveImage.launch(intent);
        }
    }

    private void registerActivityforSaveImage(){
        activityResultLauncherForSaveImage =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {

                int resultCode = o.getResultCode();
                Intent data = o.getData();

                if (resultCode == RESULT_OK && data != null) try {
                    BMimg = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    img.setImageBitmap(BMimg);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}