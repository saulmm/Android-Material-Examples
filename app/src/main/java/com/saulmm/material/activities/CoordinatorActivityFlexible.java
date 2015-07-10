package com.saulmm.material.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.saulmm.material.R;
import com.saulmm.material.adapters.RecyclerSimpleAdapter;
import com.saulmm.material.utils.Utils;

public class CoordinatorActivityFlexible extends AppCompatActivity {

    public final static int FAKE_DATA_COUNT = 100;

    private RecyclerView mRecyclerView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinator_flexible);

        initToolbar();
        //initRecycler();
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



