package com.saulmm.material.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import com.saulmm.material.R;
import com.saulmm.material.utils.AnimatorAdapter;

public class ColorActivity extends Activity {
    public final static float SCALE_FACTOR          = 20f;
    public final static int ANIMATION_DURATION      = 300;

    public final static int SECOND_ACTIVITY_REQUEST = 1;
    public final static int SECOND_ACTIVITY_END     = 1;
    private FloatingActionButton mFab;
    private boolean mReturning;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        mFab = (FloatingActionButton) findViewById(R.id.activity_color_fab);
    }

    public void onFabCLick(View view) {
        mReturning = false;

        mFab.animate()
            .scaleXBy(SCALE_FACTOR)
            .scaleYBy(SCALE_FACTOR)
            .setListener(fabAnimAdapter)
            .setDuration(ANIMATION_DURATION)
            .start();
    }

    AnimatorAdapter fabAnimAdapter = new AnimatorAdapter() {
        @Override public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);

            if (!mReturning) {

                Intent secondActivityIntent = new Intent(ColorActivity.this, ColorActivity2.class);
                secondActivityIntent .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(secondActivityIntent, SECOND_ACTIVITY_REQUEST);
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST && resultCode == SECOND_ACTIVITY_END) {
            mReturning = true;

            mFab.animate()
                .scaleXBy(SCALE_FACTOR).scaleYBy(SCALE_FACTOR)
                .scaleX(1f).scaleY(1f)
                .setDuration(ANIMATION_DURATION)
                .start();
        }
    }
}
