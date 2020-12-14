package com.github.hbtruong2017.oft.ui.appointment;

import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.model.Appointment;
import com.github.hbtruong2017.oft.api.model.Service;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServiceActivity extends AppCompatActivity {

    private List<Service> serviceList;
    static Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        Intent oldIntent = getIntent();

        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(ServiceActivity.this, ConfirmationActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
        serviceList = getBarberList();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_view_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ServiceMainAdapter(this, serviceList));
    }

    private List<Service> getBarberList() {
        List<Service> serviceList = new ArrayList<>();
        serviceList.add(new Service("Adult Haircut", "1 hour - $35", R.drawable.service1));
        serviceList.add(new Service("Teenage Haircut", "1 hour - $30", R.drawable.service2));
        serviceList.add(new Service("Kids Haircut", "1 hour - $20", R.drawable.service3));
        serviceList.add(new Service("Beard Trim", "30 mins - $21", R.drawable.service4));
        serviceList.add(new Service("Hot Towel Shave", "1 hour - $29", R.drawable.service5));
        serviceList.add(new Service("Wash and Style", "30 mins - $21", R.drawable.service6));
        return serviceList;
    }


}
