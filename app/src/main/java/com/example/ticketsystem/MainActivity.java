package com.example.ticketsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.LinearLayout;
public class MainActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        runJobWithDelay(SPLASH_DISPLAY_LENGTH);
    }
    private void runJobWithDelay(int delayTimeMillis){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                //todo: you can call your method what you want.

                startActivity(new Intent(MainActivity.this,Main_Menu.class));
                finish();

            }
        }, delayTimeMillis);

    }
}