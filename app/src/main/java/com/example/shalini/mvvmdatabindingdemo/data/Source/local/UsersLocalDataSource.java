package com.example.shalini.mvvmdatabindingdemo.data.Source.local;

import android.support.annotation.NonNull;

import com.example.shalini.mvvmdatabindingdemo.data.Source.UserDataSource;
import com.example.shalini.mvvmdatabindingdemo.data.User;
import com.example.shalini.mvvmdatabindingdemo.util.AppExecutors;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Shalini Prajesh on 28/12/17.
 */

public class UsersLocalDataSource implements UserDataSource {
    private static volatile UsersLocalDataSource INSTANCE;

    private UserDao mUserDao;

    private AppExecutors mAppExecutors;

    // Prevent direct instantiation.
    private UsersLocalDataSource(@NonNull UserDao userDao,@NonNull AppExecutors appExecutors) {
        mUserDao = userDao;
        mAppExecutors = appExecutors;
    }

    public static UsersLocalDataSource getInstance(@NonNull UserDao userDao,@NonNull AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (UsersLocalDataSource.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UsersLocalDataSource(userDao,appExecutors);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void getUsers(@NonNull final LoadUsersCallback loadUsersCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final List<User> users = mUserDao.getAllUser();
                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (users.isEmpty()) {
                            // This will be called if the table is new or just empty.
                            loadUsersCallback.onDataNotAvailable();
                        } else {
                            loadUsersCallback.onUserLoaded(users);
                        }
                    }
                });
            }
        };
        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void getUser(@NonNull final String uId, @NonNull final GetUserCallback getUserCallback) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                final User user = mUserDao.loadUserById(uId);

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        if (user != null) {
                            getUserCallback.onUserLoaded(user);
                        } else {
                            getUserCallback.onDataNotAvailable();
                        }
                    }
                });
            }
        };

        mAppExecutors.diskIO().execute(runnable);
    }

    @Override
    public void addUser(@NonNull final User user) {
        checkNotNull(user);
        Runnable saveRunnable = new Runnable() {
            @Override
            public void run() {
                mUserDao.insertUser(user);
            }
        };
        mAppExecutors.diskIO().execute(saveRunnable);
    }

    @Override
    public void updateUser(@NonNull User user) {

    }

    @Override
    public void deleteUser(@NonNull String uId) {

    }

    @Override
    public void deleteUser(@NonNull User user) {

    }
}
