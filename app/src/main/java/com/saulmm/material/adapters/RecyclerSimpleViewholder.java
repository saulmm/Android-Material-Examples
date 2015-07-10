package com.saulmm.material.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.saulmm.material.R;

public class RecyclerSimpleViewholder extends RecyclerView.ViewHolder {

    private final TextView mFakeDataTextView;

    public RecyclerSimpleViewholder(View itemView) {

        super(itemView);

        mFakeDataTextView = (TextView) itemView.findViewById(R.id.item_simple_text);
    }

    public void bind(String data) {

        mFakeDataTextView.setText(data);
    }
}