package com.saulmm.material.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.saulmm.material.R;
import com.saulmm.material.utils.AnimatorAdapter;

public class ColorActivity extends Activity {

    public final static float SCALE_FACTOR = 20f;
    public final static int ANIMATION_DURATION  = 300;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
    }

    public void start(View view) {

        view.setClickable(false);

        view.animate()
            .scaleXBy(SCALE_FACTOR)
            .scaleYBy(SCALE_FACTOR)
            .setListener(fabAnimAdapter)
            .setDuration(ANIMATION_DURATION)
            .start();
    }

    AnimatorAdapter fabAnimAdapter = new AnimatorAdapter() {
        @Override public void onAnimationEnd(Animator animation) {

            super.onAnimationEnd(animation);
            startActivity(new Intent(ColorActivity.this, ColorActivity2.class));
            overridePendingTransition(0, 0);
        }
    };
}
