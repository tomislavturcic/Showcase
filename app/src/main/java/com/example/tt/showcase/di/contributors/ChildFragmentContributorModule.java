package com.example.tt.showcase.di.contributors;

import com.example.tt.showcase.ui.test.Test2Fragment;
import com.example.tt.showcase.ui.test.TestFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */
@Module
public abstract class ChildFragmentContributorModule {

    @ContributesAndroidInjector
    abstract Test2Fragment test2Fragment();
}
