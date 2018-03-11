package com.example.tt.showcase.ui.user_details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.example.tt.showcase.R;
import com.example.tt.showcase.data.network.models.UserDetailsDTO;
import com.example.tt.showcase.di.Injection;
import com.example.tt.showcase.ui.base.BaseActivity;
import com.example.tt.showcase.ui.base.RootLayout;
import com.example.tt.showcase.ui.user_details.models.UserDetailsActivityParams;
import com.example.tt.showcase.utils.LogUtils;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TT on 11.3.2018..
 * Showcase
 */
@RootLayout(R.layout.activity_user_details)
public class UserDetailsActivity extends BaseActivity {

    private static final String EXTRA_PARAMS = "params";

    @BindView(R.id.toolbar) Toolbar toolbar;

    private UserDetailsActivityParams params;

    public static Intent createUserDetailsIntent(Context context, @NonNull UserDetailsActivityParams params) {
        Intent intent = new Intent(context, UserDetailsActivity.class);
        intent.putExtra(EXTRA_PARAMS, params);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        params = getIntent().getParcelableExtra(EXTRA_PARAMS);
        if (params == null) {
            throw new IllegalStateException("This activity must be started with UserDetailsActivityParams.");
        }

        setupToolbar();
        bindViewModel();
    }

    private void setupToolbar() {
        toolbar.setNavigationOnClickListener(v -> finish());
        toolbar.setTitle(params.getName());
    }

    private void bindViewModel() {
        showToast("Load user details for " + params.getName() + "...");
        Injection.provideApiService().getSingleUser(params.getName())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserDetailsDTO>() {
                    @Override
                    public void accept(UserDetailsDTO userDTO) throws Exception {
                        LogUtils.log(userDTO.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        LogUtils.log(throwable.getLocalizedMessage());
                    }
                });
    }
}
