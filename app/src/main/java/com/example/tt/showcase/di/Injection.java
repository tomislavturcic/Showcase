package com.example.tt.showcase.di;

import com.example.tt.showcase.data.network.ApiModule;
import com.example.tt.showcase.data.network.ApiService;
import com.example.tt.showcase.data.repository.UsersRepository;
import com.example.tt.showcase.data.repository.UsersRepositoryImpl;
import com.example.tt.showcase.ui.users.ViewModelFactory;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public class Injection {

    public static ApiService provideApiService(){
        return ApiModule.getApiService();
    }

    public static UsersRepository provideUsersRepository(){
        return new UsersRepositoryImpl(provideApiService());
    }

    public static ViewModelFactory provideViewModelFactory() {
        return new ViewModelFactory(provideUsersRepository());
    }

}
