package com.saulmm.material.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;

import com.saulmm.material.R;

public class TransitionFirstActivity extends Activity {

    private View mFabButton;
    private View mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_first);

        mFabButton = findViewById(R.id.activity_color_fab);
        mHeader = findViewById(R.id.activity_transition_header);

        Slide slideExitTransition = new Slide(Gravity.BOTTOM);
        slideExitTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        slideExitTransition.excludeTarget(android.R.id.statusBarBackground, true);
        slideExitTransition.excludeTarget(R.id.activity_transition_header, true);
        getWindow().setExitTransition(slideExitTransition);
    }

    public void onFabPressed(View view) {

        Intent i  = new Intent (TransitionFirstActivity.this,
            TransitionSecondActivity.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
            TransitionFirstActivity.this,Pair.create(mFabButton, "fab"), Pair.create(mHeader, "holder1"));

        startActivity(i, transitionActivityOptions.toBundle());
    }
}
