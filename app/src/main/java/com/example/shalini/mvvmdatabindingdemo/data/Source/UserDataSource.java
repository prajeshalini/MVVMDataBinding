package com.example.shalini.mvvmdatabindingdemo.data.Source;

import android.support.annotation.NonNull;

import com.example.shalini.mvvmdatabindingdemo.data.User;

import java.util.List;

/**
 * Created by Shalini Prajesh on 2/1/18.
 */

public interface UserDataSource {

    interface LoadUsersCallback{
        void onUserLoaded(List<User> users);
        void onDataNotAvailable();
    }

    interface GetUserCallback{
        void onUserLoaded(User user);
        void onDataNotAvailable();
    }

    void getUsers(@NonNull LoadUsersCallback loadUsersCallback);

    void getUser(@NonNull String uId,@NonNull GetUserCallback getUserCallback);

    void addUser(@NonNull User user);

    void updateUser(@NonNull User user);

    void deleteUser(@NonNull String uId);

    void deleteUser(@NonNull User user);

}
