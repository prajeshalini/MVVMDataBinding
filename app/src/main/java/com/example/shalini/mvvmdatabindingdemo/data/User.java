package com.example.shalini.mvvmdatabindingdemo.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Strings;

import java.util.UUID;

/**
 * Created by Shalini Prajesh on 28/12/17.
 */

@Entity(tableName = "User")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "entryId")
    private String mUId;

    @Nullable
    @ColumnInfo(name = "first_name")
    private String mFirstName;

    @Nullable
    @ColumnInfo(name = "last_name")
    private String mLastName;

    @Nullable
    @ColumnInfo(name = "dob")
    private String mDateOfBirth;

    /**
     * Use this constructor to create a new User.
     *
     * @param firstName       firstname of the user
     * @param lastName lastname of the user
     * @param dateOfBirth date of birth of the user
     */
    @Ignore
    public User(@Nullable String firstName, @Nullable String lastName,@Nullable String dateOfBirth) {
        this(firstName, lastName, UUID.randomUUID().toString(), dateOfBirth);
    }

    /**
     * Use this constructor to edit the already created User.
     * @param firstName  firstName of the user
     * @param lastName lastname of the user
     * @param uId entryId of the user
     * @param dateOfBirth date of the User
     */
    public User(String firstName, String lastName, String uId, String dateOfBirth) {
        mFirstName = firstName;
        mLastName = lastName;
        mUId = uId;
        mDateOfBirth = dateOfBirth;

    }

    @NonNull
    public String getUId() {
        return mUId;
    }

    @Nullable
    public String getFirstName() {
        return mFirstName;
    }

    @Nullable
    public String getLastName() {
        return mLastName;
    }

    @Nullable
    public String getDateOfBirth() {
        return mDateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(mUId,user.mUId) &&
                Objects.equal(mFirstName,user.mFirstName) &&
                Objects.equal(mLastName , user.mLastName) &&
                Objects.equal(mDateOfBirth , user.mDateOfBirth);
  }

    @Override
    public int hashCode() {
        return Objects.hashCode(mUId, mFirstName, mLastName);
    }

    @Override
    public String toString() {
        return "User with name "+ mFirstName + " " + mLastName;
    }

    public boolean isEmpty() {
        return Strings.isNullOrEmpty(mFirstName) &&
                Strings.isNullOrEmpty(mLastName) &&
                Strings.isNullOrEmpty(mDateOfBirth);
    }
}
