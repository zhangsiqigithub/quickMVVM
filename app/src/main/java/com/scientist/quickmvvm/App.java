package com.scientist.quickmvvm;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.facebook.stetho.Stetho;

public class App extends Application {

    private static App sInstance;

    private QuickMvvmDatabase mDatabase;

    public static App getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        Stetho.initializeWithDefaults(this);
    }

    public QuickMvvmDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = Room.databaseBuilder(sInstance, QuickMvvmDatabase.class, "quick_mvvm_database.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mDatabase;

    }
}
