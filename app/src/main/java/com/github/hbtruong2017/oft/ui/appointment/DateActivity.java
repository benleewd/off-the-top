package com.github.hbtruong2017.oft.ui.appointment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.github.hbtruong2017.oft.R;
import com.github.hbtruong2017.oft.api.model.Appointment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

public class DateActivity extends AppCompatActivity {

    private TextView mSelecedDateText;
    static Appointment appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);

        // Bottom bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.next:
                        Intent intent = new Intent(DateActivity.this, BarberActivity.class);

                        // Get old Intent data
                        Intent oldIntent = getIntent();
                        appointment = (Appointment) oldIntent.getSerializableExtra("appointment");
                        appointment.setDate(mSelecedDateText.getText().toString());

                        EditText timeEditText = findViewById(R.id.time);
                        appointment.setTime(timeEditText.getText().toString());

                        intent.putExtra("appointment", appointment);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });


        mSelecedDateText = findViewById(R.id.selected_date);

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("SELECT A DATE");
        MaterialDatePicker materialDatePicker = builder.build();

        // No need to use button
        materialDatePicker.show(getSupportFragmentManager(),"DATE_PICKER");

        // When click a date on datepicker

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object selection) {
                mSelecedDateText.setText(materialDatePicker.getHeaderText());
            }
        });
    }

}
