package com.github.hbtruong2017.oft.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.hbtruong2017.oft.MainActivity;
import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.StoreActivity;
import com.github.hbtruong2017.oft.ui.appointment.MapActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;

public class Promotion extends AppCompatActivity {

    private static int TAKING_PIC=1;
    private static int SELECTING_PIC=2;
    private ImageView pic;
    private Bitmap image;
    private boolean justUploaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        pic = findViewById(R.id.picture);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mToolbar.setTitle("Promotion");
        setSupportActionBar(mToolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent intent = new Intent(Promotion.this, MainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.appointment:
                        Intent intent_barber = new Intent(Promotion.this, MapActivity.class);
                        startActivity(intent_barber);
                        break;
                    case R.id.store:
                        Intent intent_store = new Intent(Promotion.this, StoreActivity.class);
                        startActivity(intent_store);
                        break;
                }
                return true;
            }
        });

    }

    public void takePicture(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePic.resolveActivity(getPackageManager())!=null){
                startActivityForResult(takePic, TAKING_PIC);
            }
        }else{
            String[] array = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(
                    this, array,TAKING_PIC);
        }
    }

    public void SelectPicture(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI
            );
            intent.setType("image/*");
            startActivityForResult(intent, SELECTING_PIC);
        } else {
            String[] array = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(
                    this, array,SELECTING_PIC);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==TAKING_PIC && resultCode ==RESULT_OK && data !=null ){
            Bundle extras = data.getExtras();
            this.image = (Bitmap) extras.get("data");
            pic.setImageBitmap(image);
            justUploaded=true;
            savePhoto();
        } else if(requestCode == SELECTING_PIC && resultCode ==RESULT_OK && data !=null){
            Uri image = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(image,
                    filePath, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePath[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            this.image = BitmapFactory.decodeFile(picturePath, options);
            pic.setImageBitmap(this.image);
            justUploaded=true;
            savePhoto();
        }
    }


    public void goToRatings(View view) {
        if(image!=null){
            Intent intent = new Intent(this, PromotionRatings.class);
            intent.putExtra("bitmap",image);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Please upload your photo first!", Toast.LENGTH_SHORT).show();
        }
    }

    private String convertBitmapToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
        byte [] b=byteArrayOutputStream.toByteArray();
        String temp= Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }

    private Bitmap convertStringToBitmap(String string){
        try {
            byte [] encodeByte=Base64.decode(string,Base64.DEFAULT);
            Bitmap bit= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bit;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    private void savePhoto(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String s = convertBitmapToString(image);
        editor.putString("bitmap", s);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!justUploaded){
            SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
            String s = sharedPreferences.getString("bitmap","");
            if(s!=null && !s.equals("")){
                image = convertStringToBitmap(s);
                pic.setImageBitmap(image);
            }
        }
    }
}
