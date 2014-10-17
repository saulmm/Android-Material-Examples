package com.saulmm.material;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

public class MyActivity extends Activity {

    private View fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        // Set explode animation when enter and exit the activity
        Utils.configureWindowEnterExitTransition(getWindow());

        // Fab Button
        fabButton = findViewById(R.id.fab_button);
        fabButton.setOnClickListener(fabClickListener);
        Utils.configureFab(fabButton);
    }


    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        Intent i  = new Intent (MyActivity.this, MyActivity2.class);

        ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MyActivity.this,
                Pair.create(fabButton, "fab"));

        startActivity(i, transitionActivityOptions.toBundle());
        }
    };
}
