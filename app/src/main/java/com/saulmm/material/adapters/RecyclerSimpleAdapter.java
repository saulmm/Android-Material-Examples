package com.saulmm.material.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class RecyclerSimpleAdapter extends RecyclerView.Adapter<RecyclerSimpleViewholder> {

    private final int mItemLayout;
    private List<String> mData;

    public RecyclerSimpleAdapter(List<String> data, int itemLayout) {

        mData = data;
        mItemLayout = itemLayout;
    }

    @Override
    public RecyclerSimpleViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(
                mItemLayout, parent, false);

        return new RecyclerSimpleViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerSimpleViewholder holder, int position) {

        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {

        return mData.size();
    }
}
