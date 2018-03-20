package com.example.tt.showcase.di.viewmodel;

import android.arch.lifecycle.ViewModel;

import com.example.tt.showcase.ui.users.UsersViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel.class)
    abstract ViewModel bindUsersViewModel(UsersViewModel viewModel);
}
