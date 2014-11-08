

package com.saulmm.material.activities;

import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;

import com.saulmm.material.R;
import com.saulmm.material.utils.GUIUtils;
import com.saulmm.material.views.adapters.SamplePagerAdapter;
import com.saulmm.material.views.widgets.SlidingTabLayout;

public class DialerSampleActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {

    private int screenWidth;
    private ImageButton fabButton;
    private FrameLayout dialerKeysContainer;
    private float absolutefabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialer);

        configureToolbar();
        configurePager();
        configureFab();
        configureDialer();

        dialerKeysContainer = (FrameLayout) findViewById(R.id.activity_dialer_frame_container);

        // Get the screen with
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }

    private void configureFab() {

        fabButton = (ImageButton) findViewById(R.id.view_fab_button);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            absolutefabPosition = v.getX();
            GUIUtils.hideViewByScale(fabButton);
            dialerKeysContainer.setVisibility(View.VISIBLE);

             Animation showDialerContainerAnimation = AnimationUtils.loadAnimation(DialerSampleActivity.this, R.anim.translate_down_on);
             showDialerContainerAnimation.setAnimationListener(new Animation.AnimationListener() {
                 @Override
                 public void onAnimationStart(Animation animation) {}

                 @Override
                 public void onAnimationEnd(Animation animation) {

                     fabButton.setBackgroundResource(R.drawable.ripple_dialer_call);
                     fabButton.setX(screenWidth / 2 - fabButton.getWidth() / 2);
                     GUIUtils.showViewByScale(fabButton);
                 }

                 @Override
                 public void onAnimationRepeat(Animation animation) {}
             });

            dialerKeysContainer.startAnimation(showDialerContainerAnimation);
            }
        });
    }

    private void configureToolbar() {

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.activity_dialer_toolbar);
        mainToolbar.setTitleTextColor(getResources().getColor(R.color.theme_dialer_accent));
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setTitle("Dialer");
    }

    private void configurePager() {

        ViewPager tabsViewPAger = (ViewPager) findViewById(R.id.activity_dialer_pager);
        tabsViewPAger.setAdapter(new SamplePagerAdapter(this));
        tabsViewPAger.setCurrentItem(1);

        SlidingTabLayout mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.activity_dialer_tabs);
        mSlidingTabLayout.addPagerListener(this);
        mSlidingTabLayout.setViewPager(tabsViewPAger);
    }

    private void configureDialer() {

        GridView dialerGrid = (GridView) findViewById(R.id.activity_dialer_pad);
        dialerGrid.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item_dialer,
                getResources().getStringArray(R.array.dialer_numbers)));
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        float fabButtonPosition = (screenWidth / 2 - fabButton.getWidth()) * positionOffset;


        if (fabButtonPosition != 0 && position != 1) {

            absolutefabPosition = fabButtonPosition + (screenWidth/2 - fabButton.getWidth()/2);
            fabButton.setX(absolutefabPosition);
        }
    }

    @Override
    public void onBackPressed() {

        if (dialerKeysContainer.getVisibility() == View.VISIBLE) {

            Animation hideAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_down_off);
            hideAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                    GUIUtils.hideViewByScale(fabButton);
                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    dialerKeysContainer.setVisibility(View.INVISIBLE);
                    fabButton.setBackgroundResource(R.drawable.ripple_dialer_idle);
                    GUIUtils.showViewByScale(fabButton);

                    fabButton.setX(absolutefabPosition);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });

            dialerKeysContainer.startAnimation(hideAnimation);



        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}
}
