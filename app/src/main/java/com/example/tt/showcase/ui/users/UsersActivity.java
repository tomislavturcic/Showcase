package com.example.tt.showcase.ui.users;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.tt.showcase.R;
import com.example.tt.showcase.data.network.RequestState;
import com.example.tt.showcase.di.Injection;
import com.example.tt.showcase.ui.base.BaseActivity;
import com.example.tt.showcase.ui.base.ItemClickListener;
import com.example.tt.showcase.ui.base.RootLayout;
import com.example.tt.showcase.ui.user_details.UserDetailsActivity;
import com.example.tt.showcase.ui.user_details.models.UserDetailsActivityParams;
import com.example.tt.showcase.ui.users.adapter.UsersAdapter;
import com.example.tt.showcase.ui.users.models.UserItem;
import com.example.tt.showcase.widgets.RetryView;
import com.example.tt.showcase.widgets.ScrollToBottomListener;

import butterknife.BindView;

@RootLayout(R.layout.activity_users)
public class UsersActivity extends BaseActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.usersSwipeRefresh) SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.usersRecyclerView) RecyclerView recyclerView;
    @BindView(R.id.usersRetryView) RetryView retryView;

    private UsersAdapter adapter;
    private ViewModelFactory viewModelFactory;
    private UsersViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        retryView.getBtnRetry().setOnClickListener(v -> viewModel.getUsers());
        setupSwipeRefresh();
        setupRecyclerView();
        bindViewModel();
    }

    private void setupSwipeRefresh(){
        swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorAccent));
        swipeRefreshLayout.setOnRefreshListener(() -> viewModel.getUsers());
    }

    private void setupRecyclerView(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addOnScrollListener(new ScrollToBottomListener() {
            @Override
            public void onScrolledToBottom() {
                viewModel.getNextPage();
            }
        });

        ItemClickListener<UserItem> itemClickListener = item -> {
            startActivity(UserDetailsActivity.createUserDetailsIntent(this, new UserDetailsActivityParams(item.getId(), item.getLoginName())));
        };
        adapter = new UsersAdapter(itemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void bindViewModel(){
        viewModelFactory = Injection.provideViewModelFactory();
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UsersViewModel.class);

        viewModel.observeRequestState().observe(this, this::handleRequestState);
        viewModel.observeUsers().observe(this, userItems -> adapter.setUsers(userItems));
        viewModel.observeLoadMoreRequestState().observe(this, this::handleLoadMoreRequestState);
    }

    private void handleRequestState(RequestState state){
        switch (state){
            case LOADING:
                swipeRefreshLayout.setRefreshing(true);
                retryView.setState(RetryView.IDLE);
                break;
            case ERROR:
                swipeRefreshLayout.setRefreshing(false);
                if(adapter.getItemCount() == 0){
                    // If no content is displayed, show error using retry view
                    retryView.setError(getString(R.string.error_server));
                } else {
                    // Else show toast
                    showToast(getString(R.string.error_server));
                }
                break;
            case COMPLETE:
                swipeRefreshLayout.setRefreshing(false);
                retryView.setState(RetryView.IDLE);
                break;
        }
    }

    private void handleLoadMoreRequestState(RequestState state){
        switch (state){
            case ERROR:
                adapter.hideLoadingMore();
                break;
            case LOADING:
                adapter.showLoadingMore();
                break;
            case COMPLETE:
                // Handled by adapter when items are set
                break;
        }
    }
}
