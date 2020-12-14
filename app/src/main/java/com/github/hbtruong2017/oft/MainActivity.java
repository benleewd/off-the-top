package com.github.hbtruong2017.oft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.hbtruong2017.oft.ui.BarberListActivity;
import com.github.hbtruong2017.oft.ui.Promotion;
import com.github.hbtruong2017.oft.ui.PromotionRatings;
import com.github.hbtruong2017.oft.ui.ServiceListActivity;
import com.github.hbtruong2017.oft.ui.appointment.BarberActivity;
import com.github.hbtruong2017.oft.ui.appointment.MapActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mToolbar = findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.appointment:
                    Intent intent_barber = new Intent(MainActivity.this, MapActivity.class);
                    startActivity(intent_barber);
                    break;
                case R.id.store:
                    Intent intent_store = new Intent(MainActivity.this, StoreActivity.class);
                    startActivity(intent_store);
                    break;
            }
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goToAdvisor(View view) {
        Intent intent = new Intent(this, AdvisorActivity.class);
        startActivity(intent);
    }

    public void goToPromotion(View view) {
        Intent intent = new Intent(this, PromotionRatings.class);
        startActivity(intent);
    }

    public void goToBarberList(View view) {
        Intent intent = new Intent(this, BarberListActivity.class);
        startActivity(intent);
    }

    public void goToServiceList(View view) {
        Intent intent = new Intent(this, ServiceListActivity.class);
        startActivity(intent);
    }

    public void goToHistory(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void callOffTheTop(View view) {
        Intent call = new Intent(Intent.ACTION_DIAL);
        call.setData(Uri.fromParts("tel", "+6591242335", null));
        startActivity(call);
    }
}
