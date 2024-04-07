package com.tlu.btlandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import untity.Profile;

public class ActivityMetrics extends AppCompatActivity {
    Profile profile;
    private TextView textView_bmi, textView_calo, textView_weight,
            textView_bmi_number, textView_calo_number, textView_iweight_number;
    private RadioButton rad_male, rad_female;
    private Button btn_calculate;
    private EditText editText_height, editText_weight, editText_age;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);
        profile = new Profile();
        initUi();
        initListener();
    }

    private void initListener() {
        textView_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMetrics.this, ActivityBmi.class);
                startActivity(intent);
            }
        });
        textView_calo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMetrics.this, ActivityCalories.class);
                startActivity(intent);
            }
        });
        textView_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMetrics.this, ActivityWeight.class);
                startActivity(intent);
            }
        });
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUser(profile);
                textView_bmi_number.setText(profile.calculateBMI(profile.getWeight(), profile.getHeight()) + " kg/m2");
                textView_calo_number.setText(profile.calculateMyCalories(profile.isGender(), profile.getWeight(), profile.getHeight(), profile.getAge())+ " calo/day");
                textView_iweight_number.setText(profile.ideaWeight(profile.getHeight(), profile.isGender())+" kg");
            }
        });
    }
    private Profile getUser(Profile profile){
        String heightStr = editText_height.getText().toString();
        String weightStr = editText_weight.getText().toString();
        String ageStr = editText_age.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty() || ageStr.isEmpty()) {
            Toast.makeText(ActivityMetrics.this, "Hãy điền thông tin vào ô trống", Toast.LENGTH_SHORT).show();
            return null; // Or handle the empty values as needed
        }
        float myheight = Float.parseFloat(editText_height.getText().toString());
        float myweight = Float.parseFloat(editText_weight.getText().toString());
        float myage = Float.parseFloat(editText_age.getText().toString());

        if (rad_male.getId() == R.id.profile_man_radio_button) {
            profile.setGender(true);
        } else if (rad_female.getId() == R.id.profile_woman_radio_button) {
            profile.setGender(false);
        }
        profile.setHeight(myheight);
        profile.setWeight(myweight);
        profile.setAge(myage);
        return profile;

    }
    private void initUi() {
        textView_bmi = findViewById(R.id.profile_bmi);
        textView_calo = findViewById(R.id.profile_calo);
        textView_weight = findViewById(R.id.profile_iweight);
        textView_bmi_number = findViewById(R.id.profile_bmi_number);
        textView_calo_number = findViewById(R.id.profile_calo_number);
        textView_iweight_number = findViewById(R.id.profile_iweight_number);
        editText_weight = findViewById(R.id.profile_weight);
        editText_age = findViewById(R.id.profile_age);
        editText_height = findViewById(R.id.profile_height);
        rad_male = findViewById(R.id.profile_man_radio_button);
        rad_female = findViewById(R.id.profile_woman_radio_button);
        btn_calculate = findViewById(R.id.calculate_status);
    }
}
