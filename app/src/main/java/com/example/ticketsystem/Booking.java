package com.example.ticketsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;


import com.example.ticketsystem.Events.Events;

import java.util.List;


public class Booking extends AppCompatActivity {
    private ViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        viewModel=new ViewModelProvider(this).get(ViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.eventlist);


        final EventAdapter adapter = new EventAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        viewModel.getAllData().observe(this, new Observer<List<Events>>() {
            @Override
            public void onChanged(List<Events> veterinary_dataModels) {
                adapter.setWords(veterinary_dataModels);
            }
        });
    }
}