package com.saulmm.material;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.transition.CircularPropagation;
import android.transition.Explode;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.util.Pair;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.saulmm.material.R;


public class MyActivity extends Activity {

    private FrameLayout frameContainer;
    private View holderView;
    private View fabButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Explode ex = new Explode();
        ex.setPropagation(new CircularPropagation());

        getWindow().setExitTransition(ex);
        getWindow().setEnterTransition(ex);

        // Fab Button
        int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline fabOutLine = new Outline();
        fabOutLine.setOval(0, 0, fabSize, fabSize);

        // Fragment container
        frameContainer = (FrameLayout) findViewById(R.id.container);

        holderView = findViewById(R.id.holder_view);

        // FabView
        fabButton = findViewById(R.id.fab_button);
        fabButton.setOnClickListener(fabClickListener);
        fabButton.setOutline(fabOutLine);
    }


    View.OnClickListener fabClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MyActivity.this,
                    Pair.create(holderView, "holder2"),
                    Pair.create(fabButton, "fab"));


            Intent i  = new Intent (MyActivity.this, MyActivity2.class);
            startActivity(i);
        }
    };
}
