package com.example.tt.showcase.utils;

import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * @author tturcic
 *         \date 19.10.2017.
 */
public class RxUtils {

    public static void dispose(@Nullable Disposable d) {
        if (d != null && !d.isDisposed()) {
            d.dispose();
        }
    }

    /**
     * Optionally, could use RxBindings library.
     */
    public static Observable<String> searchViewTextChanges(SearchView searchView) {
        return Observable.create(emitter -> searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                emitter.onNext(newText);
                return false;
            }
        }));
    }

}
