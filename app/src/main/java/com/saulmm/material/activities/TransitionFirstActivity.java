package com.saulmm.material.activities;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

import com.saulmm.material.R;
import com.saulmm.material.Utils;

public class TransitionFirstActivity extends Activity {

    private View fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_first);

        // Set explode animation when enter and exit the activity
        Utils.configureWindowEnterExitExplodeTransition(getWindow());

        // Fab Button
        fabButton = findViewById(R.id.fab_button);
        fabButton.setOnClickListener(fabClickListener);
        Utils.configureFab(fabButton);
    }


    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        Intent i  = new Intent (TransitionFirstActivity.this, TransitionSecondActivity.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(TransitionFirstActivity.this,
                Pair.create(fabButton, "fab"));

        startActivity(i, transitionActivityOptions.toBundle());
        }
    };
}
