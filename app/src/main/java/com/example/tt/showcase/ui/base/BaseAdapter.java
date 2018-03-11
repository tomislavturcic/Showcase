package com.example.tt.showcase.ui.base;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tturcic
 *         \date 18.9.2017.
 *         <p>
 *         Base adapter class for lists which contain single item type and want a simple item click listener.
 *         <p>
 *         Create your own adapter and extend this class or simply create a new BaseAdapter in activity / fragment and pass it the constructor to your viewholder class.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    protected List<T> items = new ArrayList<>();

    private final int layoutResId;
    private final ItemClickListener<T> listener;

    public BaseAdapter(int layoutResId, @Nullable ItemClickListener<T> listener) {
        this.layoutResId = layoutResId;
        this.listener = listener;
    }

    public void setItems(List<T> newItems) {
        int oldCount = this.items.size();
        this.items.clear();
        this.items.addAll(newItems);

        int newCount = newItems.size();
        if (oldCount == 0 && newCount > 0) {
            this.notifyItemRangeInserted(0, newCount);
        } else if (newCount == 0 && oldCount > 0) {
            this.notifyItemRangeRemoved(0, oldCount);
        } else {
            this.notifyDataSetChanged();
        }
    }

    public void setItems(List<T> newItems, DiffUtil.DiffResult diffResult) {
        diffResult.dispatchUpdatesTo(this);
        this.items.clear();
        this.items.addAll(newItems);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        holder.bind(items.get(position));
    }

    public abstract BaseViewHolder<T> onCreateViewHolder(View view, ItemClickListener<T> listener);

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(layoutResId, parent, false);
        return onCreateViewHolder(v, listener);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
