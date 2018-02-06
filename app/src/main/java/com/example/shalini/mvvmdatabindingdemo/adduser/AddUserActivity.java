package com.example.shalini.mvvmdatabindingdemo.adduser;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.shalini.mvvmdatabindingdemo.R;
import com.example.shalini.mvvmdatabindingdemo.ViewModelHolder;
import com.example.shalini.mvvmdatabindingdemo.databinding.ActivityAddUserBinding;
import com.example.shalini.mvvmdatabindingdemo.util.ActivityUtils;
import com.example.shalini.mvvmdatabindingdemo.util.Injection;

public class AddUserActivity extends AppCompatActivity implements AddUserNavigator {

    private static final String ADD_USER_VIEWMODEL_TAG = "ADD_USER_VIEWMODEL_TAG";
    private static final String ADD_EDIT_USERID_EXTRA = "ADD_EDIT_USERID_EXTRA";
    private AddUserViewModel mAddUserViewModel;
    private String mUserId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddUserBinding activityAddUserBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_user);
        mAddUserViewModel = findOrCreateViewModel();
        activityAddUserBinding.setAddUserViewModel(mAddUserViewModel);
        mAddUserViewModel.setAddUserNavigator(this);
        mUserId = getIntent().getStringExtra(ADD_EDIT_USERID_EXTRA);
        mAddUserViewModel.start(mUserId);
    }

    @Override
    public void onUserSaved() {
        finish();
    }

    private AddUserViewModel findOrCreateViewModel() {
        // In a configuration change we might have a ViewModel present. It's retained using the
        // Fragment Manager.
       // @SuppressWarnings("unchecked")
        ViewModelHolder<AddUserViewModel> retainedViewModel =
                (ViewModelHolder<AddUserViewModel>) getSupportFragmentManager()
                        .findFragmentByTag(ADD_USER_VIEWMODEL_TAG);

        if (retainedViewModel != null && retainedViewModel.getViewmodel() != null) {
            // If the model was retained, return it.
            return retainedViewModel.getViewmodel();
        } else {
            // There is no ViewModel yet, create it.
            AddUserViewModel viewModel = new AddUserViewModel(
                    Injection.provideDataSource(getApplicationContext()), this);

            // and bind it to this Activity's lifecycle using the Fragment Manager.
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(),
                    ViewModelHolder.createContainer(viewModel),
                    ADD_USER_VIEWMODEL_TAG);
            return viewModel;
        }
    }
}
