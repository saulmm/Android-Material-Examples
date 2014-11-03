package com.saulmm.material.views.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saulmm.material.R;

@SuppressWarnings("FieldCanBeLocal")
public class DialerPagerAdapter extends PagerAdapter {

    private final String [] TITLES = {"SPEED DIAL", "RECENTS", "CONTACTS"};
    private Context context;

    public DialerPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return TITLES.length;
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