package com.example.db_hadas;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {Contact.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract ContactDAO contactDAO();

    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    Database.class,
                    "contact_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return  instance;
    }
}
