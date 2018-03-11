package com.example.tt.showcase.ui.base;

import android.arch.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by TT on 20.10.2017..
 *
 */

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable subscriptions;

    @Override
    protected void onCleared() {
        super.onCleared();
        if(subscriptions != null){
            subscriptions.clear();
        }
    }

    protected void addSubscription(Disposable d){
        if(subscriptions == null){
            subscriptions = new CompositeDisposable();
        }
        subscriptions.add(d);
    }
}
