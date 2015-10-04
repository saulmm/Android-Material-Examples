package com.saulmm.material.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.saulmm.material.R;

public class CardsActivity extends AppCompatActivity implements View.OnClickListener {
	private View mActionViewPrimary;
	private View mActionViewSecondary;
	private View mContentView;
	private int mContentViewHeight;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_cards);

		mContentView = findViewById(R.id.view_card_raw_content);
		mActionViewPrimary = findViewById(R.id.view_card_raw_action_primary);
		mActionViewSecondary = findViewById(R.id.view_card_raw_action_secondary);
		mActionViewSecondary.setOnClickListener(this);
		mActionViewPrimary.setOnClickListener(this);

		mContentView.getViewTreeObserver().addOnPreDrawListener(
			new ViewTreeObserver.OnPreDrawListener() {
				@Override public boolean onPreDraw() {
					mContentView.getViewTreeObserver().removeOnPreDrawListener(this);

					final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
					final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);

					mContentView.measure(widthSpec, heightSpec);
					mContentViewHeight = mContentView.getHeight();
					mContentView.setVisibility(View.GONE);

					return true;
				}
			});
	}

	private ValueAnimator slideAnimator(int start, int end) {
		ValueAnimator animator = ValueAnimator.ofInt(start, end);

		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override public void onAnimationUpdate(ValueAnimator valueAnimator) {
				int value = (Integer) valueAnimator.getAnimatedValue();

				ViewGroup.LayoutParams layoutParams = mContentView.getLayoutParams();
				layoutParams.height = value;
				mContentView.setLayoutParams(layoutParams);
			}
		});
		return animator;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.view_card_raw_action_primary) {
			if (mContentView.getVisibility() == View.GONE) expandContentView();
			else collapseContentView();

		} else if (v.getId() == R.id.view_card_raw_action_secondary) {
			mContentView.setVisibility(View.GONE);
		}
	}

	private void collapseContentView() {
		ValueAnimator valueHeightAnimator = ValueAnimator.ofInt(mContentViewHeight, 0);
		valueHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override public void onAnimationUpdate(ValueAnimator animation) {
				ViewGroup.LayoutParams lp = mContentView.getLayoutParams();
				lp.height = (Integer) animation.getAnimatedValue();
				mContentView.setLayoutParams(lp);
			}
		});

		valueHeightAnimator.start();
		valueHeightAnimator.addListener(new AnimatorListenerAdapter() {
			@Override public void onAnimationEnd(Animator animation) {
				super.onAnimationEnd(animation);
				mContentView.setVisibility(View.GONE);
			}
		});
	}

	private void expandContentView() {
		mContentView.setVisibility(View.VISIBLE);
		ValueAnimator valueHeightAnimator = ValueAnimator.ofInt(0, mContentViewHeight);
		valueHeightAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			@Override public void onAnimationUpdate(ValueAnimator animation) {
				ViewGroup.LayoutParams lp = mContentView.getLayoutParams();
				lp.height = (Integer) animation.getAnimatedValue();
				mContentView.setLayoutParams(lp);
			}
		});

		valueHeightAnimator.start();
	}
}
