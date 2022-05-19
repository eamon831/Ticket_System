package com.example.ticketsystem.Events;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Events.class},version = 1,exportSchema = false)
public abstract class Event_database extends RoomDatabase {

    public abstract Event_dao vet_dao();

    private static volatile Event_database INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static Event_database getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (Event_database.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            Event_database.class, "word_database")
                            .createFromAsset("sqfile/data.sqlite")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static Callback sRoomDatabaseCallback = new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                Event_dao dao = INSTANCE.vet_dao();
                dao.deleteAll();

                Events vd = new Events(10000,"baal","baal","baal");
                dao.insert(vd);

            });
        }
    };


}
