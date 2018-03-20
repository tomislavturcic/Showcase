package com.example.tt.showcase.di.modules;

import com.example.tt.showcase.data.repository.UsersRepository;
import com.example.tt.showcase.data.repository.UsersRepositoryImpl;

import dagger.Binds;
import dagger.Module;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */
@Module
public abstract class RepositoryModule {

    @Binds
    abstract UsersRepository provideUsersRepository(UsersRepositoryImpl usersRepository);

}
