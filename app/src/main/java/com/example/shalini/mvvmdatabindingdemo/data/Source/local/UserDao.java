package com.example.shalini.mvvmdatabindingdemo.data.Source.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.shalini.mvvmdatabindingdemo.data.User;

import java.util.List;

/**
 * Created by Shalini Prajesh on 2/1/18.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAllUser();

    @Query("SELECT * FROM user WHERE entryId = :userId")
    User loadUserById(String userId);

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND "
            + "last_name LIKE :last LIMIT 1")
    User findByName(String first, String last);
//
//    @Insert
//    void insertAll(User... users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Query("UPDATE User SET first_name = :first And last_name = :last WHERE entryId = :uId")
    void updateName(String uId, String first,String last);

    @Update
    int updateUser(User user);

    @Query("DELETE FROM User WHERE entryId = :uId")
    int deleteUserById(String uId);

    @Delete
    void delete(User user);
}
