package com.github.hbtruong2017.oft.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.github.hbtruong2017.oft.MainActivity;
import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.StoreActivity;
import com.github.hbtruong2017.oft.ui.appointment.MapActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;

public class PromotionRatings extends AppCompatActivity {

    private Bitmap image;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion_ratings);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        mToolbar.setTitle("Gallery");
        setSupportActionBar(mToolbar);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(PromotionRatings.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

    }

}
