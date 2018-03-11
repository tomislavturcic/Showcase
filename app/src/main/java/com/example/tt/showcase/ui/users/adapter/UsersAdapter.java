package com.example.tt.showcase.ui.users.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tt.showcase.R;
import com.example.tt.showcase.ui.base.ItemClickListener;
import com.example.tt.showcase.ui.users.models.UserItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TT on 11.3.2018..
 * Showcase
 */

public class UsersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_USER = 0;
    private static final int TYPE_LOADING_MORE = 1;

    private List<UserItem> users = new ArrayList<>();
    private ItemClickListener<UserItem> userItemClickListener;
    private boolean isLoadingMore;

    public UsersAdapter(ItemClickListener<UserItem> userItemClickListener) {
        this.userItemClickListener = userItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_LOADING_MORE){
            View v = inflater.inflate(R.layout.item_loading_more, parent, false);
            return new RecyclerView.ViewHolder(v) {};
        } else {
            View v = inflater.inflate(R.layout.item_user, parent, false);
            return new UserViewHolder(v, userItemClickListener);
        }
    }

    public void setUsers(List<UserItem> users){
        this.users.clear();
        this.users.addAll(users);
        isLoadingMore = false;
        this.notifyDataSetChanged();
    }

    public void showLoadingMore(){
        if(!isLoadingMore){
            isLoadingMore = true;
            notifyItemInserted(getItemCount());
        }
    }

    public void hideLoadingMore(){
        if(isLoadingMore){
            isLoadingMore = false;
            notifyItemRemoved(getItemCount() - 1);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == TYPE_USER) {
            ((UserViewHolder) holder).bind(users.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(isLoadingMore && position == getItemCount() - 1){
            return TYPE_LOADING_MORE;
        }
        return TYPE_USER;
    }

    @Override
    public int getItemCount() {
        return users.size() + (isLoadingMore ? 1 : 0);
    }
}
