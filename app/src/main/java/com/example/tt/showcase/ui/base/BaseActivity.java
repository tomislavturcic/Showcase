package com.example.tt.showcase.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author tturcic
 *         \date 30.8.2017.
 *
 *         Base class for activities. It saves a few lines of code by eliminating the need to bind ButterKnife in each activity and contains a number of oftenly used methods.
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private CompositeDisposable subscriptions = new CompositeDisposable();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflateLayout();
    }

    private void inflateLayout(){
        RootLayout rootLayout = getClass().getAnnotation(RootLayout.class);
        if(rootLayout != null && rootLayout.value() != 0){
            setContentView(rootLayout.value());
            ButterKnife.bind(this);
        }
    }

    @Override
    protected void onDestroy() {
        subscriptions.clear();
        super.onDestroy();
    }

    protected void addSubscription(Disposable d){
        subscriptions.add(d);
    }

    protected void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
