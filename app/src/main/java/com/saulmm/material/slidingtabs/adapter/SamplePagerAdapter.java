package com.saulmm.material.slidingtabs.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saulmm.material.R;

@SuppressWarnings("FieldCanBeLocal")
public class SamplePagerAdapter extends PagerAdapter {

    private final String [] TITLES = {"CATEGORIES", "HOME", "TOP SELLING", "TOP GAMES", "TOP GROSSING"};
    private final int FRAGMENT_COUNT = 5;
    private Context context;

    public SamplePagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }


    @Override
    public boolean isViewFromObject(View view, Object o) {
        return o == view;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_sliding_pager,
                container, false);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}