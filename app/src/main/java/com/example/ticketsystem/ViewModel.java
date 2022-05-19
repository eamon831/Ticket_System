package com.example.ticketsystem;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ticketsystem.Events.Events;
import com.example.ticketsystem.Events.Repository;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Events>> allData,searched_data;
    public ViewModel(@NonNull Application application) {
        super(application);
        repository=new Repository(application);
        allData=repository.getAllData();
    }

    LiveData<List<Events>> getAllData()
    {
        return allData;
    }
    LiveData<List<Events>> getSearched_data(String search)
    { return repository.getSearched_data(search); }
    public void update(Events e)
    {
        repository.update(e);
    }

    public void delete(Events e)
    {
        repository.delete(e);
    }

    public void insert(Events e)
    {
        repository.insert(e);
    }

    public void deleteall()
    {
        repository.deleteall();
    }
}