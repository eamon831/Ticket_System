package com.example.ticketsystem.Events;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface Event_dao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Events e);

    @Update
    void update(Events e);

    @Delete
    void delete(Events e);

    @Query("DELETE FROM Events")
    void deleteAll();

    @Query("SELECT * FROM Events ORDER BY id DESC")
    LiveData<List<Events>> getAlldata();

    @Query("SELECT * FROM Events WHERE time_venue LIKE :search OR team1 LIKE :search")
    LiveData<List<Events>> getSearcheddata(String search);
}
