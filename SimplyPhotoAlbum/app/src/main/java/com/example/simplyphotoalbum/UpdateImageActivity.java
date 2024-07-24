package com.example.simplyphotoalbum;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class UpdateImageActivity extends AppCompatActivity {

    private static final int MAX_IMAGE_SIZE = 300;
    private EditText updateTitle;
    private EditText updateDesc;
    private Button updateImg;
    private ImageView img;
    private Bitmap bitmapImage;
    private int id;
    private ActivityResultLauncher<Intent> activityResultLauncherForUpdateImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Image");
        setContentView(R.layout.activity_update_image);

        initViews();
        registerActivityForUpdateImage();
        populateDataFromIntent();

        img.setOnClickListener(v -> requestImagePermission());

        updateImg.setOnClickListener(v -> updateImage());
    }

    private void initViews() {
        updateTitle = findViewById(R.id.updateImageTitle);
        updateDesc = findViewById(R.id.updateImageDescription);
        img = findViewById(R.id.updImagview);
        updateImg = findViewById(R.id.updateImage);
    }

    private void requestImagePermission() {
        String permission = Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
                ? Manifest.permission.READ_MEDIA_IMAGES
                : Manifest.permission.READ_EXTERNAL_STORAGE;

        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permission}, 1);
        } else {
            selectImageFromGallery();
        }
    }

    private void selectImageFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activityResultLauncherForUpdateImage.launch(intent);
    }

    private void updateImage() {
        if (id == -1) {
            Toast.makeText(this, "Some Error Occurred.", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = updateTitle.getText().toString();
        String desc = updateDesc.getText().toString();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("id", id);
        intent.putExtra("updatetitle", title);
        intent.putExtra("updatedesc", desc);

        if (bitmapImage != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Bitmap scaledImage = makeSmall(bitmapImage, MAX_IMAGE_SIZE);
            scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
            byte[] image = outputStream.toByteArray();
            intent.putExtra("image", image);
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    private Bitmap makeSmall(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();
        float ratio = (float) width / height;

        if (ratio > maxSize) {
            width = maxSize;
            height = (int) (width / ratio);
        } else {
            height = maxSize;
            width = (int) (height * ratio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    private void registerActivityForUpdateImage() {
        activityResultLauncherForUpdateImage = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        try {
                            bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), result.getData().getData());
                            img.setImageBitmap(bitmapImage);
                        } catch (IOException e) {
                            Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void populateDataFromIntent() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);
        updateTitle.setText(intent.getStringExtra("title"));
        updateDesc.setText(intent.getStringExtra("desc"));

        byte[] imageBytes = intent.getByteArrayExtra("imgg");
        if (imageBytes != null) {
            bitmapImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            img.setImageBitmap(bitmapImage);
        }
    }
}




//package com.example.simplyphotoalbum;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.content.pm.ProviderInfo;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.os.Build;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.EdgeToEdge;
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//
//public class UpdateImageActivity extends AppCompatActivity {
//
//    TextView updateImage;
//    EditText updateTitle;
//    EditText updateDesc;
//    Button updateImg;
//    ImageView img;
//    Bitmap BMimg;
//    private int id;
//    private Bitmap scaledImage;
//    private ActivityResultLauncher activityResultLauncherForUpdateImage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getSupportActionBar().setTitle("Update Image");
//        setContentView(R.layout.activity_update_image);
//
//        updateImage = findViewById(R.id.updateImg);
//        updateTitle = findViewById(R.id.updateImageTitle);
//        updateDesc = findViewById(R.id.updateImageDescription);
//        img = findViewById(R.id.updImagview);
//        updateImg = findViewById(R.id.updateImage);
//
//        updater();
//        registerActivityforUpdateImage();
//
////        ImageView
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String permission;
//
//                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ){
//                    permission = android.Manifest.permission.READ_MEDIA_IMAGES;
//                }else {
//                    permission = Manifest.permission.READ_EXTERNAL_STORAGE;
//                }
//
//                if(ContextCompat.checkSelfPermission(UpdateImageActivity.this, permission) != PackageManager.PERMISSION_GRANTED){
//                    ActivityCompat.requestPermissions(UpdateImageActivity.this, new String[]{permission}, 1);
//                }else {
//                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    activityResultLauncherForUpdateImage.launch(intent);
//                }
//            }
//        });
//
//        updateImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                if(id == -1){
//                    Toast.makeText(UpdateImageActivity.this, "Some Error Ocurred.", Toast.LENGTH_SHORT).show();
//                }else{
//                    String title = updateTitle.getText().toString();
//                    String desc = updateDesc.getText().toString();
//                    Intent intent = new Intent(UpdateImageActivity.this, MainActivity.class);
//                    intent.putExtra("id", id);
//                    intent.putExtra("updatetitle", title);
//                    intent.putExtra("updatedesc", desc);
//                    if(BMimg == null){
//                        intent.putExtra("image", BMimg);
//
//                    }else {
//                        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//                        scaledImage = makeSmall(BMimg, 300);
//                        scaledImage.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
//                        byte[] image = outputStream.toByteArray();
//                        intent.putExtra("image", image);
//                    }
//                    setResult(RESULT_OK, intent);
//                    finish();
//                }
//            }
//        });
//    }
//
//    private Bitmap makeSmall(Bitmap image, int maxSize){
//        int c_width = image.getWidth();
//        int c_height = image.getHeight();
//
//        float ratio = (float) c_width/ (float) c_height;
//
//        if(ratio > maxSize){
//            c_width = maxSize;
//            c_height = (int) (c_width/ratio);
//
//        }else {
//            c_height = maxSize;
//            c_width = (int) (c_height * ratio);
//        }
//
//        return  Bitmap.createScaledBitmap(image, c_width, c_height, true);
//    }
//
//    private void registerActivityforUpdateImage(){
//        activityResultLauncherForUpdateImage =registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//            @Override
//            public void onActivityResult(ActivityResult o) {
//
//                int resultCode = o.getResultCode();
//                Intent data = o.getData();
//
//                if (resultCode == RESULT_OK && data != null) try {
//                    BMimg = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
//                    img.setImageBitmap(BMimg);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//    }
//
//    private void updater(){
//        Intent intent = getIntent();
//        id = intent.getIntExtra("id", -1);
//        updateTitle.setText(intent.getStringExtra("title"));
//        updateDesc.setText(intent.getStringExtra("desc"));
//        byte[] imagg;
//        imagg = intent.getByteArrayExtra("imgg");
//        BMimg = BitmapFactory.decodeByteArray(imagg, 0, imagg.length);
//        img.setImageBitmap(BMimg);
//    }
//}