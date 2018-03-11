package com.example.tt.showcase.ui.users;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.tt.showcase.data.network.ApiService;
import com.example.tt.showcase.data.repository.UsersRepository;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final UsersRepository usersRepository;

    public ViewModelFactory(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UsersViewModel.class)) {
            return (T) new UsersViewModel(usersRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
