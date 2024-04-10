package com.tlu.btlandroid;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Activity_detail extends AppCompatActivity {
    TextView informationMuscle;
    TextView infoMuscle;
    ImageView imageViewMuscle;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        informationMuscle =findViewById(R.id.tv_if_muscle);
        infoMuscle =findViewById(R.id.informationMuscle);
        imageViewMuscle = findViewById(R.id.imageView_muscle);
        back = findViewById(R.id.back_to_ac);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String muscleName = extras.getString("name");
            int imageId = extras.getInt("imageId", 0);
            String infoMuscles = extras.getString("info");

            informationMuscle.setText(muscleName);
            infoMuscle.setText(infoMuscles);
            if (imageId != 0) {
                imageViewMuscle.setImageResource(imageId);
            } else {
                imageViewMuscle.setImageResource(R.drawable.human_background);
            }
        }

        getOnBackPressedDispatcher().addCallback(Activity_detail.this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}