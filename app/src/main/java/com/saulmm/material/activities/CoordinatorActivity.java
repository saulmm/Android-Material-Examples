package com.saulmm.material.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.saulmm.material.R;
import com.saulmm.material.utils.Utils;

import java.util.List;

public class CoordinatorActivity extends AppCompatActivity {

    public final static int FAKE_DATA_COUNT = 100;

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator);

        initToolbar();
        initRecycler();
    }

    private void initRecycler() {

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_coordinator_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new RecyclerSimpleAdapter(Utils.createFakeData(FAKE_DATA_COUNT)));
    }

    private void initToolbar() {

        mToolbar = (Toolbar) findViewById(R.id.activity_coordinator_toolbar);
        setSupportActionBar(mToolbar);
    }
}

class RecyclerSimpleAdapter extends RecyclerView.Adapter<RecyclerSimpleViewholder> {

    private List<String> mData;

    public RecyclerSimpleAdapter(List<String> mData) {

        this.mData = mData;
    }

    @Override
    public RecyclerSimpleViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(
            R.layout.item_simple_viewholder, parent, false);

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

class RecyclerSimpleViewholder extends RecyclerView.ViewHolder {

    private final TextView mFakeDataTextView;

    public RecyclerSimpleViewholder(View itemView) {

        super(itemView);

        mFakeDataTextView = (TextView) itemView.findViewById(R.id.item_simple_text);
    }

    public void bind(String data) {

        mFakeDataTextView.setText(data);
    }
}