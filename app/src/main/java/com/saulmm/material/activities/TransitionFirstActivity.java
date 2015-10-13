package com.saulmm.material.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class TransitionFirstActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_TRANSITION = "selected_transition";
    public static final String TRANS_SLIDE = "slide";
    public static final String TRANS_FADE = "fade";
    public static final String TRANS_EXPLODE= "explode";
    private View mFabButton;
    private View mToolbar;

    public final static int [] EXCLUDED_VIEWS = new int[] {
        android.R.id.navigationBarBackground,
        android.R.id.statusBarBackground,
    };
    private String mSelectedTransition = TRANS_SLIDE;

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

        i.putExtra(EXTRA_TRANSITION, mSelectedTransition);

        startActivity(i, transitionActivityOptions.toBundle());
    }

    @Override @SuppressWarnings("ConstantConditions")
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Transition exitTransition = null;

        switch (position) {
            case 0:
                exitTransition = new Slide(Gravity.BOTTOM);
                mSelectedTransition = TRANS_SLIDE;
                break;
            case 1:
                exitTransition = new Fade();
                mSelectedTransition = TRANS_FADE;
                break;
            case 2:
                exitTransition = new Explode();
                mSelectedTransition = TRANS_EXPLODE;
                break;
        }
        for (int excludeViewId : EXCLUDED_VIEWS)
            exitTransition.excludeTarget(excludeViewId, true);

        getWindow().setExitTransition(exitTransition);
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Unused
    }
}
