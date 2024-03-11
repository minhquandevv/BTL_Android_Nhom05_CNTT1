package com.tlu.btlandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Activity_home extends AppCompatActivity {

    private ViewPager2 mviewPager2;
    private BottomNavigationView mbottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mviewPager2 = findViewById(R.id.viewpg2);
        mbottomNavigationView = findViewById(R.id.btnv);




    }
}