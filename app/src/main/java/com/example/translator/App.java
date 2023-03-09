package com.example.translator;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.translator.db.AppDatabase;

public class App extends Application {
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();

        appDatabase = Room
                .databaseBuilder(this,AppDatabase.class,"app-database")
                .build();





    }
    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
