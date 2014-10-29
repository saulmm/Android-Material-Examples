package com.saulmm.material.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.transition.Slide;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.saulmm.material.R;
import com.saulmm.material.Utils;

public class ColorActivity extends Activity {

    private SharedPreferences sharedpreferences;
    private View revealView;
    private View circleHolder;
    private View fabButton;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set the saved theme
        sharedpreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
//        sharedpreferences.edit().clear().apply();
        setTheme(sharedpreferences.getInt("theme", R.style.AppTheme));

        setContentView(R.layout.activity_color);

        // Views
        Switch themeSwitch = (Switch) findViewById(R.id.theme_switch);
        themeSwitch.setChecked(sharedpreferences.getBoolean("switch", false));
        revealView = findViewById(R.id.reveal_view);
        fabButton = findViewById(R.id.fab_button);
        circleHolder = findViewById(R.id.circle_holder);

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            // Save the state of the switch
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putInt("theme", (isChecked) ? R.style.Base_Theme_AppCompat : R.style.Base_Theme_AppCompat_Light);
            ed.putBoolean("switch", isChecked);
            ed.apply();
            }
        });

        // Show the unreveal effect
        final int cx = sharedpreferences.getInt("x", 0);
        final int cy = sharedpreferences.getInt("y", 0);

        if (cx != 0 && cy != 0) {

            // Show the unreveal effect when the view is attached to the window
            revealView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {

                    // Get the accent color
                    TypedValue outValue = new TypedValue();
                    getTheme().resolveAttribute(android.R.attr.colorPrimary, outValue, true);
                    revealView.setBackgroundColor(outValue.data);

                    hideRevealEffect(cx, cy);
                }

                @Override
                public void onViewDetachedFromWindow(View v) {}
            });
        }

    }

    public void showRevealEffect(View v, int primaryColor) {
        revealView.setVisibility(View.VISIBLE);

        int [] location = new int[2];
        revealView.setBackgroundColor(primaryColor);
        v.getLocationOnScreen(location);

        int cx = (location[0] + (v.getWidth() / 2));
        int cy = location[1] + (Utils.getStatusBarHeight(this) / 2);


        SharedPreferences.Editor ed = sharedpreferences.edit();
        ed.putInt("x", cx);
        ed.putInt("y", cy);
        ed.apply();

        int height = revealView.getHeight();

        Animator anim = ViewAnimationUtils.createCircularReveal(
                revealView, cx, cy, 0, height);


        anim.addListener(revealAnimationListener);
                anim.start();

        hideNavigationStatus();
    }


    public void hideRevealEffect (int x, int y) {

        revealView.setVisibility(View.VISIBLE);
        int initialRadius = 1920;

        // create the animation (the final radius is zero)
        Animator anim = ViewAnimationUtils.createCircularReveal(
                revealView, x, y, 1080, 0);

        Log.d("WTF", "X: "+x+" Y: "+y+" - "+initialRadius);

        // make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            revealView.setVisibility(View.INVISIBLE);
            }
        });

        anim.start();
    }

    private void hideNavigationStatus() {
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

    Animator.AnimatorListener revealAnimationListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {

            Intent i = new Intent(ColorActivity.this, ColorActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            overridePendingTransition(0, 0);
            finish();

        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {


        }
    };


    public void view(View view) {
        int selectedTheme = 0;
        int primaryColor = 0;

        switch (view.getId()) {

            case R.id.circle1:
                selectedTheme = R.style.theme1;
                primaryColor = getResources().getColor(R.color.color_set_1_primary);
                break;

            case R.id.circle2:
                selectedTheme = R.style.theme2;
                primaryColor = getResources().getColor(R.color.color_set_2_primary);
                break;

            case R.id.circle3:
                selectedTheme = R.style.theme3;
                primaryColor = getResources().getColor(R.color.color_set_3_primary);
                break;

            case R.id.circle4:
                selectedTheme = R.style.theme4;
                primaryColor = getResources().getColor(R.color.color_set_4_primary);
                break;
        }


        showRevealEffect(view, primaryColor);

        SharedPreferences.Editor ed = sharedpreferences.edit();
        ed.putInt("theme", selectedTheme);
        ed.apply();
//
//
    }
}
