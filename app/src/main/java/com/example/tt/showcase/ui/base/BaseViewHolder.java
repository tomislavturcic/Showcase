package com.example.tt.showcase.ui.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * @author tturcic
 *         \date 18.9.2017.
 *
 *         A base class for simple viewholders. Used by BaseAdapter.
 */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected T item;

    public BaseViewHolder(View itemView, @Nullable ItemClickListener<T> listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        if(listener != null) {
            itemView.setOnClickListener(v -> {
                if (item != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                    listener.onItemClicked(item);
                }
            });
        }
    }

    /**
     * Override this method when extending this class, but make sure to call super.bind();
     */
    public void bind(T item){
        this.item = item;
    }

    protected Context getContext(){
        return itemView.getContext();
    }

    protected String getString(@StringRes int stringRes){
        return itemView.getContext().getString(stringRes);
    }
}
