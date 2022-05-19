package com.example.ticketsystem.Users;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;



@Database(entities = {Model.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}