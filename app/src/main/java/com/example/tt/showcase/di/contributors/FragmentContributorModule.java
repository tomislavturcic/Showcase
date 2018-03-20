package com.example.tt.showcase.di.contributors;

import com.example.tt.showcase.ui.test.TestFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */
@Module
public abstract class FragmentContributorModule {

    @ContributesAndroidInjector(modules = ChildFragmentContributorModule.class)
    abstract TestFragment testFragment();

}
