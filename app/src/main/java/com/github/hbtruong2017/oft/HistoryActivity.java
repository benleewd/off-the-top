package com.github.hbtruong2017.oft;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.hbtruong2017.oft.api.AppointmentService;
import com.github.hbtruong2017.oft.api.ServiceCallback;
import com.github.hbtruong2017.oft.api.ServiceProvider;
import com.github.hbtruong2017.oft.api.model.Appointment;
import com.github.hbtruong2017.oft.api.model.Barber;
import com.github.hbtruong2017.oft.ui.appointment.BarberActivity;
import com.github.hbtruong2017.oft.ui.appointment.ConfirmationActivity;
import com.github.hbtruong2017.oft.ui.appointment.ServiceActivity;
import com.github.hbtruong2017.oft.ui.appointment.ServiceMainAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class HistoryActivity extends AppCompatActivity {
    private List<Appointment> appointmentList;

    private final AppointmentService service;

    public HistoryActivity() {
        this.service = ServiceProvider.getService(AppointmentService.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_view_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new HistoryMainAdapter(this, this.appointmentList));

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        service.get(sharedPref.getString("user.id", "1")).enqueue(ServiceCallback.of(this, (listResponse, appointments) -> {
            // TODO: you get appoint here via async
            Log.i("HistoryActivity", "Size: " + appointments.size());
            this.appointmentList = appointments;
            recyclerView.setAdapter(new HistoryMainAdapter(this, this.appointmentList));
        }));
    }


}
