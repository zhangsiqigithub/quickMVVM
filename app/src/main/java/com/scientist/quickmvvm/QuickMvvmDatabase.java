package com.scientist.quickmvvm;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = Product.class, version = 1, exportSchema = false)
public abstract class QuickMvvmDatabase extends RoomDatabase {


    public abstract ProductDao productDao();
}
