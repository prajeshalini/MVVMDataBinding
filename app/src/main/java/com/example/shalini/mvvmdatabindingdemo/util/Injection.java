package com.example.shalini.mvvmdatabindingdemo.util;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.shalini.mvvmdatabindingdemo.data.Source.local.AppDatabase;
import com.example.shalini.mvvmdatabindingdemo.data.Source.local.UsersLocalDataSource;

/**
 * Created by Shalini Prajesh on 8/1/18.
 */

public class Injection {

    public static UsersLocalDataSource provideDataSource(@NonNull Context context){
        AppDatabase appDatabase = AppDatabase.getInstance(context);
        UsersLocalDataSource usersLocalDataSource = UsersLocalDataSource.getInstance(appDatabase.userDao(),new AppExecutors());
        return usersLocalDataSource;
    }
}
