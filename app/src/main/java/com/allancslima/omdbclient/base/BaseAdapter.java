package com.allancslima.omdbclient.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Allan Lima on 13/01/2018.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    private List<T> mDataset;

    protected BaseAdapter(Context context, List<T> dataset) {
        mContext = context;
        mDataset = dataset;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        onBindData(holder, mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public T getItem(int position) {
        return mDataset.get(position);
    }

    protected abstract RecyclerView.ViewHolder getViewHolder(ViewGroup parent);

    protected abstract void onBindData(RecyclerView.ViewHolder holder, T object);
}