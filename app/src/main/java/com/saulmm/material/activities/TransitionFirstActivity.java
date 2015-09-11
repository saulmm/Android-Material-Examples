package com.saulmm.material.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.saulmm.material.R;

public class TransitionFirstActivity extends Activity
    implements AdapterView.OnItemSelectedListener {

    private View mFabButton;
    private View mToolbar;

    private final static int [] EXCLUDED_VIEWS = new int[] {
        android.R.id.navigationBarBackground,
        android.R.id.statusBarBackground,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_first);

        mFabButton = findViewById(R.id.example_transition_fab);
        mToolbar = findViewById(R.id.example_transition_header);

        ((Toolbar) mToolbar).setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        Spinner mTransitionSpinner = (Spinner) findViewById(R.id.example_transition_spinner);
        mTransitionSpinner.setOnItemSelectedListener(this);
    }

    @SuppressWarnings("unchecked")
    public void onFabCLick(View view) {
        Intent i  = new Intent (TransitionFirstActivity.this,
            TransitionSecondActivity.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(
            TransitionFirstActivity.this, Pair.create(mFabButton, "fab"),
            Pair.create(mToolbar, "holder1"));

        startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Transition exitTransition = null;

        switch (position) {
            case 0:
                exitTransition = createSlideTransition(Gravity.BOTTOM, EXCLUDED_VIEWS);
                break;
            case 1:
                exitTransition = createFadeTransition(EXCLUDED_VIEWS);
                break;
            case 2:
                exitTransition = createExplodeTransition(EXCLUDED_VIEWS);
                break;
        }

        getWindow().setExitTransition(exitTransition);
    }

    public Transition createSlideTransition(int gravity, int [] excludeIds) {
        Slide slideTransition = new Slide(gravity);

        for (int excludeViewId : excludeIds)
            slideTransition.excludeTarget(excludeViewId, true);

        return slideTransition;
    }

    public Transition createFadeTransition(int [] excludeIds) {
        Fade fadeTransition = new Fade();

        for (int excludeViewId : excludeIds)
            fadeTransition.excludeTarget(excludeViewId, true);

        return fadeTransition;
    }

    public Transition createExplodeTransition(int [] excludeIds) {
        Explode explodeTransition = new Explode();

        for (int excludeViewId : excludeIds)
            explodeTransition.excludeTarget(excludeViewId, true);

        return explodeTransition;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Unused
    }


}
