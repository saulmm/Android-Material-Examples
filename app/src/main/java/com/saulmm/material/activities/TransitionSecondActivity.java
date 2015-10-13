package com.saulmm.material.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.Gravity;
import android.view.View;
import com.saulmm.material.R;

public class TransitionSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_second);

        Toolbar toolbar = (Toolbar) findViewById(R.id.example_transition_header);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                onBackPressed();
            }
        });

        initEnterTransition();
    }

    private void initEnterTransition() {
        String selectedTransition = getIntent().getExtras().getString(
            TransitionFirstActivity.EXTRA_TRANSITION,
            TransitionFirstActivity.TRANS_SLIDE);

        Transition enterTransition = null;

        if (selectedTransition.equals(TransitionFirstActivity.TRANS_SLIDE))
            enterTransition = new Slide(Gravity.BOTTOM);

        else if (selectedTransition.equals(TransitionFirstActivity.TRANS_FADE))
            enterTransition = new Fade();

        else if (selectedTransition.equals(TransitionFirstActivity.TRANS_EXPLODE))
            enterTransition = new Explode();

        enterTransition.excludeTarget(android.R.id.navigationBarBackground, true);
        enterTransition.excludeTarget(android.R.id.statusBarBackground, true);
        getWindow().setEnterTransition(enterTransition);
    }

    public void onFabCLick(View view) {
        onBackPressed();
    }
}
