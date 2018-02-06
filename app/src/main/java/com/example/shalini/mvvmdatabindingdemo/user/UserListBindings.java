package com.example.shalini.mvvmdatabindingdemo.user;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.shalini.mvvmdatabindingdemo.data.User;

import java.util.List;

/**
 * Created by Shalini Prajesh on 31/1/18.
 */

public class UserListBindings {
    private static final String TAG = "UserListBindings";
    @BindingAdapter("android:items")
    public static void setUsers(RecyclerView recyclerView, List<User> userList){
        Log.e(TAG, "setUsers: ");
        Log.e(TAG, "" + userList.size());
        UserListAdapter userListAdapter = (UserListAdapter) recyclerView.getAdapter();
        if (userListAdapter != null)
        {
            userListAdapter.replaceData(userList);
        }
    }
}
