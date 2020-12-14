package com.github.hbtruong2017.oft.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hbtruong2017.oft.MainActivity;
import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.model.Barber;
import com.github.hbtruong2017.oft.ui.appointment.ServiceActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BarberListActivity extends AppCompatActivity {

    private List<Barber> barberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barber_list);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);


        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(BarberListActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        barberList = getBarberList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_view_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new BarberListMainAdapter(this,barberList));
    }

    private List<Barber> getBarberList() {
        List<Barber> barberList = new ArrayList<>();
        barberList.add(new Barber("Anthony Love", "I style, you smile", R.drawable.barber1));
        barberList.add(new Barber("Joshua James", "As urban as you can get in the suburbs.", R.drawable.barber2));
        barberList.add(new Barber("King Bome", "Clean cuts. Close shaves.", R.drawable.barber3));

        return barberList;
    }

    public void goToHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
