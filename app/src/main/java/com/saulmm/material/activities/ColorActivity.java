package com.saulmm.material.activities;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.saulmm.material.R;

public class ColorActivity extends Activity {

    private SharedPreferences sharedpreferences;
    private View revealView;
    private View circleHolder;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        sharedpreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        setTheme(sharedpreferences.getInt("theme", R.style.AppTheme));

        setContentView(R.layout.activity_color);

        Switch themeSwitch = (Switch) findViewById(R.id.theme_switch);
        themeSwitch.setChecked(sharedpreferences.getBoolean("switch", false));
        revealView = findViewById(R.id.reveal_view);

        circleHolder = findViewById(R.id.circle_holder);

        themeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            SharedPreferences.Editor ed = sharedpreferences.edit();
            ed.putInt("theme", (isChecked) ? R.style.Base_Theme_AppCompat : R.style.Base_Theme_AppCompat_Light);

            ed.putBoolean("switch", isChecked);
            ed.apply();
            }
        });


        super.onCreate(savedInstanceState);

    }

    public void showRevealEffect(View v, int primaryColor) {
        revealView.setVisibility(View.VISIBLE);


        int [] location = new int[2];
        revealView.setBackgroundColor(primaryColor);
        v.getLocationOnScreen(location);

        int cx = (location[0] + (v.getWidth() / 2));
        int cy = location[1];

        int height = revealView.getHeight();


        Animator anim = ViewAnimationUtils.createCircularReveal(
                revealView, cx, cy, 0, height);


        anim.addListener(revealAnimationListener);
                anim.start();
    }

    Animator.AnimatorListener revealAnimationListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            revealView.setVisibility(View.INVISIBLE);

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
