package com.example.shalini.mvvmdatabindingdemo.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.shalini.mvvmdatabindingdemo.data.User;
import com.example.shalini.mvvmdatabindingdemo.databinding.ListItemUserBinding;

import java.util.List;

/**
 * Created by Shalini Prajesh on 31/1/18.
 */

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private List<User> mUserList;

    public UserListAdapter(List<User> mUserList) {
        this.mUserList = mUserList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ListItemUserBinding listItemUserBinding = ListItemUserBinding.inflate(layoutInflater);
        return new UserViewHolder(listItemUserBinding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = getUserForPosition(position);
        holder.bind(user);
    }

    private User getUserForPosition(int position) {
        return mUserList.get(position);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void replaceData(List<User> userList) {
        setList(userList);
    }

    private void setList(List<User> list) {
        mUserList = list;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private final ListItemUserBinding listItemUserBinding;

        public UserViewHolder(ListItemUserBinding listItemUserBinding) {
            super(listItemUserBinding.getRoot());
            this.listItemUserBinding = listItemUserBinding;
        }

        public void bind(User user){
            listItemUserBinding.setUser(user);
            listItemUserBinding.executePendingBindings(); //
        }
    }
}
