package com.example.tt.showcase.di;

import com.example.tt.showcase.App;
import com.example.tt.showcase.di.contributors.ActivityContributorModule;
import com.example.tt.showcase.di.modules.ApiModule;
import com.example.tt.showcase.di.modules.RepositoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by TT on 20.3.2018..
 * Showcase
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class,
        AppModule.class,
        RepositoryModule.class,
        ActivityContributorModule.class,
        ApiModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        // Automatically provides App (context)
        @BindsInstance
        Builder application(App app);

        // We want to build api module instance ourselves since it uses a non-default constructor
        Builder apiModule(ApiModule apiModule);

        AppComponent build();
    }

    void inject(App app);

}
