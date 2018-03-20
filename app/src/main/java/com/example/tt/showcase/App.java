package com.example.tt.showcase;

import android.app.Activity;
import android.app.Application;

import com.example.tt.showcase.data.network.ApiUrls;
import com.example.tt.showcase.di.DaggerAppComponent;
import com.example.tt.showcase.di.modules.ApiModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by TT on 8.3.2018..
 * Showcase
 */

public class App extends Application implements HasActivityInjector {

    @Inject DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .apiModule(new ApiModule(ApiUrls.BASE_URL))
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
