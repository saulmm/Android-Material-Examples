package com.saulmm.material.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.saulmm.material.R;
import com.saulmm.material.utils.Utils;

public class ColorActivity extends Activity {

    private SharedPreferences sharedpreferences;
    private View revealView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set the saved theme
        sharedpreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        setTheme(sharedpreferences.getInt("theme", R.style.AppTheme));

        setContentView(R.layout.activity_color);

        // Views
        Switch themeSwitch = (Switch) findViewById(R.id.theme_switch);
        themeSwitch.setChecked(sharedpreferences.getBoolean(themeSwitch.getId()+"", false));
        themeSwitch.setOnCheckedChangeListener(checkedListener);

        CheckBox themeCheck = (CheckBox) findViewById(R.id.theme_check);
        themeCheck.setChecked(sharedpreferences.getBoolean(themeCheck.getId()+"", false));
        themeCheck.setOnCheckedChangeListener(checkedListener);

        revealView = findViewById(R.id.reveal_view);

        // Show the unReveal effect
        final int cx = sharedpreferences.getInt("x", 0);
        final int cy = sharedpreferences.getInt("y", 0);

        startHideRevealEffect(cx, cy);
    }

    private void startHideRevealEffect(final int cx, final int cy) {

        if (cx != 0 && cy != 0) {
            // Show the unReveal effect when the view is attached to the window
            revealView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                @Override
                public void onViewAttachedToWindow(View v) {

                    // Get the accent color
                    TypedValue outValue = new TypedValue();
                    getTheme().resolveAttribute(android.R.attr.colorPrimary, outValue, true);
                    revealView.setBackgroundColor(outValue.data);

                    Utils.hideRevealEffect(revealView, cx, cy, 1920);
                }

                @Override
                public void onViewDetachedFromWindow(View v) {}
            });
        }
    }


    private void hideNavigationStatus() {

        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }


    Animator.AnimatorListener revealAnimationListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animation) {}

        @Override
        public void onAnimationEnd(Animator animation) {

            Intent i = new Intent(ColorActivity.this, ColorActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            overridePendingTransition(0, 0);
            finish();
        }

        @Override
        public void onAnimationCancel(Animator animation) {}

        @Override
        public void onAnimationRepeat(Animator animation) {}
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

        int [] location = new int[2];
        revealView.setBackgroundColor(primaryColor);
        view.getLocationOnScreen(location);

        int cx = (location[0] + (view.getWidth() / 2));
        int cy = location[1] + (Utils.getStatusBarHeight(this) / 2);

        SharedPreferences.Editor ed = sharedpreferences.edit();
        ed.putInt("x", cx);
        ed.putInt("y", cy);
        ed.putInt("theme", selectedTheme);
        ed.apply();

        hideNavigationStatus();
        Utils.showRevealEFfect(revealView, cx, cy, revealAnimationListener);
    }


    CompoundButton.OnCheckedChangeListener checkedListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putInt("theme", (isChecked) ? R.style.Base_Theme_AppCompat : R.style.Base_Theme_AppCompat_Light);
            ed.putBoolean(buttonView.getId()+"", isChecked);
            ed.apply();
        }
    };
}
