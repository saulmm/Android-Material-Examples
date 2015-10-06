package com.saulmm.material.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import com.saulmm.material.R;

public class RevealSecondActivity extends AppCompatActivity {

	private static final int FADE_DURATION_MILLIS = 800;
	private static final int FADE_START_OFFSET = 300;
	private TextView mHelpText;
	private Toolbar mToolbar;
	private View mContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reveal_second);

		mContainer = findViewById(R.id.activity_color2_container);
		mToolbar = (Toolbar) findViewById(R.id.activity_reveal_toolbar);
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				onBackPressed();
			}
		});

		mHelpText = (TextView) findViewById(R.id.activity_reveal_help_textview);
		mHelpText.setVisibility(View.VISIBLE);

		Animation translateToolbarAnimation = AnimationUtils.loadAnimation(this, R.anim.translate_up_on);
		mToolbar.setAnimation(translateToolbarAnimation);

		Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
		fadeIn.setStartOffset(FADE_START_OFFSET);
		fadeIn.setDuration(FADE_DURATION_MILLIS);
		mHelpText.setAnimation(fadeIn);

		AnimationSet animationSet = new AnimationSet(true);
		animationSet.addAnimation(translateToolbarAnimation);
		animationSet.addAnimation(fadeIn);
		animationSet.start();
	}

	@Override
	public void onBackPressed() {
		Animation fadeOut = AnimationUtils.loadAnimation(this, R.anim.abc_fade_out);
		fadeOut.setFillAfter(true);
		mContainer.startAnimation(fadeOut);

		fadeOut.setAnimationListener(new Animation.AnimationListener() {
			@Override public void onAnimationStart(Animation animation) {}
			@Override public void onAnimationRepeat(Animation animation) {}
			@Override public void onAnimationEnd(Animation animation) {
				setResult(RevealFirstActivity.SECOND_ACTIVITY_END);
				populateOnBackPressed();
			}
		});
	}

	public void populateOnBackPressed() {
		finish();
		overridePendingTransition(0, 0);
	}
}
