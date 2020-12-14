package com.github.hbtruong2017.oft.ui.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.github.hbtruong2017.oft.MainActivity;
import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.AppointmentService;
import com.github.hbtruong2017.oft.api.ServiceCallback;
import com.github.hbtruong2017.oft.api.ServiceProvider;
import com.github.hbtruong2017.oft.api.UserService;
import com.github.hbtruong2017.oft.api.model.Appointment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ConfirmationActivity extends AppCompatActivity {
    static Appointment appointment;

    private final AppointmentService service;

    public ConfirmationActivity() {
        this.service = ServiceProvider.getService(AppointmentService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(ConfirmationActivity.this);
                        String userId = sharedPref.getString("user.id", "1");
                        service.post(userId, appointment).enqueue(ServiceCallback.of(ConfirmationActivity.this, (appointmentResponse, appointment1) -> {
                            Intent intent = new Intent(ConfirmationActivity.this, MainActivity.class);
                            startActivity(intent);
                        }));
                        break;
                }
                return true;
            }
        });

        // Get Intent
        Intent intent = getIntent();
        appointment = (Appointment) intent.getSerializableExtra("appointment");

        TextView selectedDate = findViewById(R.id.selected_date);
        selectedDate.setText(appointment.getDate());

        TextView selectedTime = findViewById(R.id.selected_time);
        selectedTime.setText(appointment.getTime());

        TextView selectedLocation = findViewById(R.id.selected_location);
        selectedLocation.setText(appointment.getAddress());

        TextView selectedBarber = findViewById(R.id.selected_barber);
        selectedBarber.setText(appointment.getBarberName());

        TextView selectedService = findViewById(R.id.selected_service);
        selectedService.setText(appointment.getServiceName());

        TextView selectedServiceDetails = findViewById(R.id.selected_service_description);
        selectedServiceDetails.setText(appointment.getServiceDescription());

    }

    public void confirmAppointment(View view) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        service.post(sharedPref.getString("user.id", "1"), appointment).enqueue(ServiceCallback.of(this, (res, body) -> {
            Log.i("Status; ", "Success");
        }));
    }
}
