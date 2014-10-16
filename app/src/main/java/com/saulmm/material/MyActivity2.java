package com.saulmm.material;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Outline;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.CircularPropagation;
import android.transition.Explode;
import android.transition.Scene;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.saulmm.material.R;

public class MyActivity2 extends Activity {

    private FrameLayout frameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my2);

        // Fab Button
        int fabSize = getResources().getDimensionPixelSize(R.dimen.fab_size);
        Outline fabOutLine = new Outline();

        Explode ex = new Explode();
        ex.setPropagation(new CircularPropagation());

        getWindow().setExitTransition(ex);
        getWindow().setEnterTransition(ex);
        fabOutLine.setOval(0, 0, fabSize, fabSize);
    }

    @Override
    protected void onResume() {

        super.onResume();

        frameContainer = (FrameLayout) findViewById(R.id.container);

        Scene scene1 = Scene.getSceneForLayout(frameContainer, R.layout.activity_my_activity2, this);
        TransitionManager.go(scene1, new Explode());
    }
}
