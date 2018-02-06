package com.example.shalini.mvvmdatabindingdemo.user;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.util.Log;

import com.example.shalini.mvvmdatabindingdemo.data.Source.UserDataSource;
import com.example.shalini.mvvmdatabindingdemo.data.Source.local.UsersLocalDataSource;
import com.example.shalini.mvvmdatabindingdemo.data.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shalini Prajesh on 2/1/18.
 */

public class UserViewModel extends BaseObservable {
    private static final String TAG = "UserViewModel";
    //These label will automatically update the view
    public ObservableArrayList<User> userList = new ObservableArrayList<>();
    public ObservableField<String> noUserLabel = new ObservableField<>();
    public ObservableField<String> snackbarText = new ObservableField<>();
    private UsersLocalDataSource mUsersLocalDataSource;
    private Context mContext; //To Avoid leaks this must be an application context.
    private UserNavigator mUserNavigator;

    public UserViewModel(
            UsersLocalDataSource usersLocalDataSource,
            Context context) {
        mContext = context.getApplicationContext(); // Force use of Application Context.
        mUsersLocalDataSource = usersLocalDataSource;
    }

    public void start() {
        noUserLabel.set("No Users Available");
        loadUsers();
    }

    public String getSnackbarText(){
        return snackbarText.get();
    }

    public void setUserNavigator(UserNavigator userNavigator){
        mUserNavigator = userNavigator;
    }

    public void onActivityDestroyed(){
        mUserNavigator = null;
    }
    @Bindable
    public boolean isEmpty() {
        return userList.isEmpty();
    }

    private void loadUsers() {
        mUsersLocalDataSource.getUsers(new UserDataSource.LoadUsersCallback() {
            @Override
            public void onUserLoaded(List<User> users) {
                List<User> userDisplayList = new ArrayList<>();

                for (User user : users) {
                    userDisplayList.add(user);
                    Log.e(TAG, "onUserLoaded: " + user.getFirstName() );
                }
                userList.addAll(userDisplayList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    public void addNewUser() {
        if(mUserNavigator != null){
            mUserNavigator.addNewUser();
        }
    }
}
