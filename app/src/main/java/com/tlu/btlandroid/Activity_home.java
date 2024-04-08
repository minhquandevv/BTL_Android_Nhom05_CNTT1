package com.tlu.btlandroid;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.tlu.btlandroid.BroadcastReceiver.Internet;

import adapter.Pageadapter;


public class Activity_home extends AppCompatActivity {
    private Internet internetBroadcastReceiver;
    private ViewPager2 mviewPager2;
    private BottomNavigationView mbottomNavigationView;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        internetBroadcastReceiver = new Internet();
        mviewPager2 = findViewById(R.id.viewpg2);
        mbottomNavigationView = findViewById(R.id.btnv);

        Pageadapter pageadapter = new Pageadapter(this);

        mviewPager2.setAdapter(pageadapter);


        
        mbottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(id==R.id.bottom_workouts){
                    mviewPager2.setCurrentItem(0);
                }else if(id==R.id.bottom_custom){
                    mviewPager2.setCurrentItem(1);
                }else if(id==R.id.bottom_achievements){
                    mviewPager2.setCurrentItem(2);
                }else if(id==R.id.bottom_music){
                    mviewPager2.setCurrentItem(3);
                }else if(id==R.id.bottom_information){
                    mviewPager2.setCurrentItem(4);
                }
                return true;
            }
        });

        mviewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {

                switch (position){
                    case 0:mbottomNavigationView.getMenu().findItem(R.id.bottom_workouts).setChecked(true);
                        break;
                }
                switch (position){
                    case 1:mbottomNavigationView.getMenu().findItem(R.id.bottom_custom).setChecked(true);
                        break;
                }
                switch (position){
                    case 2:mbottomNavigationView.getMenu().findItem(R.id.bottom_achievements).setChecked(true);
                        break;
                }
                switch (position){
                    case 3:mbottomNavigationView.getMenu().findItem(R.id.bottom_music).setChecked(true);
                        break;
                }
                switch (position){
                    case 4:mbottomNavigationView.getMenu().findItem(R.id.bottom_information).setChecked(true);
                        break;
                }
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(internetBroadcastReceiver, intentFilter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(internetBroadcastReceiver);

    }
}