package com.example.shalini.mvvmdatabindingdemo.data.Source.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.shalini.mvvmdatabindingdemo.data.User;

/**
 * Created by Shalini Prajesh on 2/1/18.
 */
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();

    private static final Object sLock = new Object();

    public static AppDatabase  getInstance(Context context){
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, "User.db")
                        .build();
            }
            return INSTANCE;
        }
    }
}
