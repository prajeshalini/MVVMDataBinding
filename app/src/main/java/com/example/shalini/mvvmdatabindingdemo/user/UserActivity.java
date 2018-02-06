package com.example.shalini.mvvmdatabindingdemo.user;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.shalini.mvvmdatabindingdemo.R;
import com.example.shalini.mvvmdatabindingdemo.ViewModelHolder;
import com.example.shalini.mvvmdatabindingdemo.adduser.AddUserActivity;
import com.example.shalini.mvvmdatabindingdemo.data.User;
import com.example.shalini.mvvmdatabindingdemo.databinding.ActivityUserBinding;
import com.example.shalini.mvvmdatabindingdemo.util.ActivityUtils;
import com.example.shalini.mvvmdatabindingdemo.util.Injection;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements UserNavigator {
    private static final String TAG = "UserActivity";
    private static final String USER_VIEWMODEL_TAG = "USER_VIEWMODEL_TAG";
    private static final String ACTION_ADD_EDIT = "ACTION_ADD_EDIT";
    private UserViewModel mUserViewModel;
    private ActivityUserBinding activityUserBinding;

    @Override
    protected void onResume() {
        super.onResume();
        mUserViewModel.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUserBinding = DataBindingUtil.setContentView(this, R.layout.activity_user);
        mUserViewModel = findOrCreateViewModel();
        activityUserBinding.setViewmodel(mUserViewModel);
        mUserViewModel.setUserNavigator(this);
        setUpFAB();

        setListData();
    }

    private void setListData() {
        RecyclerView recyclerView = activityUserBinding.userListView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserListAdapter userListAdapter = new UserListAdapter(new ArrayList<User>(0));
        recyclerView.setAdapter(userListAdapter);
    }

    private void setUpFAB() {
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButtonAddUser);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserViewModel.addNewUser();
            }
        });
    }

    private UserViewModel findOrCreateViewModel() {
        // In a configuration change we might have a ViewModel present. It's retained using the
        // Fragment Manager.
        @SuppressWarnings("unchecked")
        ViewModelHolder<UserViewModel> retainedViewModel =
                (ViewModelHolder<UserViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(USER_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewmodel();
        } else {
            // There is no ViewModel yet, create it.
            UserViewModel viewModel = new UserViewModel(Injection.provideDataSource(getApplicationContext()), this);

            // and bind it to this Activity's lifecycle using the Fragment Manager.
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    USER_VIEWMODEL_TAG);
            return viewModel;
        }
    }
    @Override
    public void addNewUser() {
        //Start New Activity here
        Intent intent = new Intent(UserActivity.this, AddUserActivity.class);
        intent.putExtra(ACTION_ADD_EDIT,"ADD");
        startActivity(intent);
        
    }

    @Override
    protected void onDestroy() {
        mUserViewModel.onActivityDestroyed();
        super.onDestroy();
    }
}
