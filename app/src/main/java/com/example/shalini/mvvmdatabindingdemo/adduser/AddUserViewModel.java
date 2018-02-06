package com.example.shalini.mvvmdatabindingdemo.adduser;

import android.content.Context;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.shalini.mvvmdatabindingdemo.R;
import com.example.shalini.mvvmdatabindingdemo.data.Source.UserDataSource;
import com.example.shalini.mvvmdatabindingdemo.data.Source.local.UsersLocalDataSource;
import com.example.shalini.mvvmdatabindingdemo.data.User;

/**
 * Created by Shalini Prajesh on 8/1/18.
 */

public class AddUserViewModel implements UserDataSource.GetUserCallback{

    private final Context mContext;          //To Avoid leaks this must be an application context.
    private final UsersLocalDataSource mUsersLocalDataSource;
    public ObservableField<String> mFirstName = new ObservableField<>();
    public ObservableField<String> mSecondName = new ObservableField<>();
    public ObservableField<String> mDateOfBirth = new ObservableField<>();
    public ObservableField<User> mUser = new ObservableField<>();
    public ObservableField<String> mSnackBarText = new ObservableField<>();
    private String mUserId;
    private boolean mIsNewUser;
    private boolean mIsDataLoaded = false;
    private AddUserNavigator mAddUserNavigator;

    public AddUserViewModel(@NonNull UsersLocalDataSource usersLocalDataSource, @NonNull Context context) {
        mContext = context;
        mUsersLocalDataSource = usersLocalDataSource;
    }

    public void start(String userId){
        mUserId = userId;
        if (mUserId == null){
            mIsNewUser = true;
            return;
        }
        if(mIsDataLoaded){
            return;
        }
        mIsNewUser = false;
        mUsersLocalDataSource.getUser(userId,this);
    }

    public void setAddUserNavigator(AddUserNavigator addUserNavigator){
        mAddUserNavigator = addUserNavigator;
    }

    @Override
    public void onUserLoaded(User user) {
        mFirstName.set(user.getFirstName());
        mSecondName.set(user.getLastName());
        mDateOfBirth.set(user.getDateOfBirth());
        mUser.set(user);
        mIsDataLoaded = true;
    }

    @Override
    public void onDataNotAvailable() {
        //hide loader
    }

    private boolean isNewUser(){
        return mIsNewUser;
    }

    public void saveTask() {
        if (isNewUser()) {
            createUser(mFirstName.get(),mSecondName.get(),mDateOfBirth.get());
        } else {
            updateUser(mFirstName.get(),mSecondName.get(),mDateOfBirth.get());
        }
    }

    private void createUser(String firstName, String lastName, String dateOfBirth) {
        User newUser = new User(firstName,lastName,dateOfBirth);

        if (newUser.isEmpty()){
            mSnackBarText.set(mContext.getString(R.string.empty_task_message));
        }else{
            mUsersLocalDataSource.addUser(newUser);
            navigationOnUserSaved();
        }
    }

    private void updateUser(String firstName, String lastName,String dateOfBirth) {
        if (isNewUser()) {
            throw new RuntimeException("updateUser() was called but user is new.");
        }
        mUsersLocalDataSource.addUser(new User(firstName, lastName,mUserId, dateOfBirth));
        navigationOnUserSaved(); // After an edit, go back to the list.
    }

    private void navigationOnUserSaved() {
        if (mAddUserNavigator!= null) {
            mAddUserNavigator.onUserSaved();
        }
    }
}
