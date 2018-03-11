package com.example.tt.showcase.ui.users.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tt.showcase.R;
import com.example.tt.showcase.ui.base.BaseViewHolder;
import com.example.tt.showcase.ui.base.ItemClickListener;
import com.example.tt.showcase.ui.users.models.UserItem;

import butterknife.BindView;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public class UserViewHolder extends BaseViewHolder<UserItem> {

    @BindView(R.id.imgUser) ImageView imgUser;
    @BindView(R.id.txtUserName) TextView txtUserName;

    public UserViewHolder(View itemView, @Nullable ItemClickListener<UserItem> listener) {
        super(itemView, listener);
    }

    @Override
    public void bind(UserItem item) {
        super.bind(item);
        imgUser.setVisibility(TextUtils.isEmpty(item.getAvatarUrl()) ? View.INVISIBLE : View.VISIBLE);
        Glide.with(imgUser).load(item.getAvatarUrl()).into(imgUser);

        txtUserName.setText(item.getLoginName());
    }
}
