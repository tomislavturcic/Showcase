package com.example.tt.showcase.widgets;

import android.support.v7.widget.RecyclerView;

/**
 * Created by TT on 16.11.2017..
 *
 * Notifies using abstract method that we have reached bottom of the recycler view.
 */

public abstract class ScrollToBottomListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        int scrollDownDirection = 1;
        if (!recyclerView.canScrollVertically(scrollDownDirection)) {
            onScrolledToBottom();
        }
    }

    public abstract void onScrolledToBottom();
}
