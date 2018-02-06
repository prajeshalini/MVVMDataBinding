package com.example.shalini.mvvmdatabindingdemo;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.example.shalini.mvvmdatabindingdemo.data.Source.local.UsersLocalDataSource;

/**
 * Created by Shalini Prajesh on 31/1/18.
 */

public class UserViewModel extends BaseObservable {

    public final ObservableField<String> mFirstName = new ObservableField<>();
    public final ObservableField<String> mLastName = new ObservableField<>();
    public final ObservableField<String> mDateOfBirth = new ObservableField<>();
    private final Context mContext;
    private final UsersLocalDataSource mUsersLocalDataSource;

    public UserViewModel(Context mContext, UsersLocalDataSource mUsersLocalDataSource) {
        this.mContext = mContext;
        this.mUsersLocalDataSource = mUsersLocalDataSource;
    }


}
