package com.example.tt.showcase.di.contributors;

import com.example.tt.showcase.ui.test.TestActivity;
import com.example.tt.showcase.ui.user_details.UserDetailsActivity;
import com.example.tt.showcase.ui.users.UsersActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */
@Module
public abstract class ActivityContributorModule {

    @ContributesAndroidInjector()
    abstract UsersActivity usersActivity();

    @ContributesAndroidInjector()
    abstract UserDetailsActivity userDetailsActivity();

    @ContributesAndroidInjector(modules = FragmentContributorModule.class)
    abstract TestActivity testActivity();

}
