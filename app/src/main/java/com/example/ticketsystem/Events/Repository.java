package com.example.ticketsystem.Events;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
public class Repository {
    private Event_dao event_dao;
    private LiveData<List<Events>> allData,searched_data;

    public Repository(Application application)
    {
        Event_database database=Event_database.getDatabase(application);
        event_dao = database.vet_dao();
        allData=event_dao.getAlldata();
    }


    public void insert(Events e)
    {
        Event_database.databaseWriteExecutor.execute(() ->{
            event_dao.insert(e);
        });
    }

    public void update(Events e)
    {
        Event_database.databaseWriteExecutor.execute(() ->{
            event_dao.update(e);
        });

    }

    public void delete(Events e)
    {
        Event_database.databaseWriteExecutor.execute(() ->{
            event_dao.delete(e);
        });
    }

    public void deleteall()
    {
        Event_database.databaseWriteExecutor.execute(() ->{
            event_dao.deleteAll();
        });
    }

    public  LiveData<List<Events>> getAllData()
    {
        return allData;
    }
    public  LiveData<List<Events>> getSearched_data(String search)
    {
        return event_dao.getSearcheddata(search);
    }

}
