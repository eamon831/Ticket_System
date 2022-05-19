package com.example.ticketsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Gallery;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main_Menu extends AppCompatActivity {
    TextView regi,main,booked,about,web,gallery;
    TextView fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        regi=findViewById(R.id.regi);
        main=findViewById(R.id.main);
        booked=findViewById(R.id.booked);
        about=findViewById(R.id.about);
        web=findViewById(R.id.web);
        //gallery=findViewById(R.id.gallery);
        fab=findViewById(R.id.fab);


        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Registration.class));
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Booking.class));
            }
        });
        booked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,Single_User.class));

            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,ContactUsActivity.class));
            }
        });
        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Main_Menu.this,web.class));
            }
        });
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("My Notification ","My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager=getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Note();

                startActivity(new Intent(Main_Menu.this,TabActivity.class));
            }
        });

    }
    void Note(){
        //Creating a notification channel
        NotificationChannel channel= null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("channel1",
                    "hello",
                    NotificationManager.IMPORTANCE_HIGH);
        }
        NotificationManager manager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(channel);
        }

        //Creating the notification object
        NotificationCompat.Builder notification=new NotificationCompat.Builder(this,"channel1");
        //notification.setAutoCancel(true);
        notification.setContentTitle("Hi this is a notification");
        notification.setContentText("Hello you");
        notification.setSmallIcon(R.drawable.ic_launcher_background);

        //make the notification manager to issue a notification on the notification's channel
        manager.notify(121,notification.build());
    }

}