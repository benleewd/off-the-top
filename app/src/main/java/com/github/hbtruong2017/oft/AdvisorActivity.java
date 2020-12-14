package com.github.hbtruong2017.oft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AdvisorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advisor);

    }

    public void doAr(View view) {
        RadioGroup radioGroup = findViewById(R.id.hairstyle);
        if (radioGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Please select 1 hairstyle to try", Toast.LENGTH_SHORT).show();
        } else {
            int id = radioGroup.getCheckedRadioButtonId();
            String selected = "";
            switch (radioGroup.getCheckedRadioButtonId()) {
                case R.id.h1:
                    selected = "h1";
                    break;
                case R.id.h2:
                    selected = "h2";
                    break;
                case R.id.h3:
                    selected = "h3";
                    break;
            }
            Intent intent = new Intent(this, AugmentedFacesActivity.class);
            intent.putExtra("hairstyle", selected);

            startActivity(intent);

        }


    }

    public void enlarge(View view) {
        Toast.makeText(this, "Third hair style is selected. Try it on now!", Toast.LENGTH_LONG).show();
        RadioGroup radioGroup = findViewById(R.id.hairstyle);
        int id = radioGroup.getCheckedRadioButtonId();
        int[] selection = {R.id.h1, R.id.h2, R.id.h3};
        int selected = 0;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.h1:
                selected = 1;
                break;
            case R.id.h2:
                selected = 2;
                break;
            case R.id.h3:
                selected = 3;
                break;
        }
        for (int i=0;i<3;i++){
            if(i==selected){
                RadioButton currButton = findViewById(selection[i]);
                currButton.setWidth(150);
                currButton.setHeight(150);
            } else {
                RadioButton currButton = findViewById(selection[i]);
                currButton.setWidth(100);
                currButton.setHeight(100);
            }
        }
    }


    public void firstSelected(View view) {
        Toast.makeText(this, "First hair style is selected. Try it on now!", Toast.LENGTH_LONG).show();
    }

    public void secondSelected(View view) {
        Toast.makeText(this, "Second hair style is selected. Try it on now!", Toast.LENGTH_LONG).show();
    }
}
