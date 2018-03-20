package com.example.tt.showcase.ui.users;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.tt.showcase.data.network.RequestState;
import com.example.tt.showcase.data.repository.UsersRepository;
import com.example.tt.showcase.mapper.UsersMapper;
import com.example.tt.showcase.ui.base.BaseViewModel;
import com.example.tt.showcase.ui.users.models.UserItem;
import com.example.tt.showcase.utils.LogUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public class UsersViewModel extends BaseViewModel {

    private final UsersRepository usersRepository;

    private final MutableLiveData<RequestState> requestStateData = new MutableLiveData<>();
    private final MutableLiveData<List<UserItem>> usersData = new MutableLiveData<>();
    private final MutableLiveData<RequestState> loadMoreRequestStateData = new MutableLiveData<>();

    @Inject
    UsersViewModel(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        requestStateData.setValue(RequestState.NONE);
        loadMoreRequestStateData.setValue(RequestState.NONE);
        getUsers();
    }

    LiveData<List<UserItem>> observeUsers() {
        return usersData;
    }

    LiveData<RequestState> observeRequestState() {
        return requestStateData;
    }

    LiveData<RequestState> observeLoadMoreRequestState() {
        return loadMoreRequestStateData;
    }

    void getUsers() {
        if (requestStateData.getValue() != RequestState.LOADING) {
            requestStateData.setValue(RequestState.LOADING);
            addSubscription(usersRepository.getFirstPage()
                    .map(UsersMapper::fromUsersDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(users -> {
                        requestStateData.setValue(RequestState.COMPLETE);
                        usersData.setValue(users);
                    }, throwable -> {
                        LogUtils.error(throwable);
                        RequestState errorState = RequestState.ERROR;
                        errorState.error = throwable;
                        requestStateData.setValue(errorState);
                    }));
        }
    }

    void getNextPage() {
        if (loadMoreRequestStateData.getValue() != RequestState.LOADING && usersRepository.canLoadMore()) {
            loadMoreRequestStateData.setValue(RequestState.LOADING);

            addSubscription(usersRepository.getNextPage()
                    .zipWith(Single.timer(400, TimeUnit.MILLISECONDS), (userDTOS, aLong) -> userDTOS)
                    .map(UsersMapper::fromUsersDTO)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(users -> {
                        loadMoreRequestStateData.setValue(RequestState.COMPLETE);
                        List<UserItem> prevUsers = usersData.getValue();
                        prevUsers.addAll(users);
                        usersData.setValue(prevUsers);
                    }, throwable -> {
                        LogUtils.error(throwable);
                        RequestState errorState = RequestState.ERROR;
                        errorState.error = throwable;
                        loadMoreRequestStateData.setValue(errorState);
                    }));
        }
    }
}
